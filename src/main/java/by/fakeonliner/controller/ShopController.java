package by.fakeonliner.controller;

import by.fakeonliner.dao.hibernate.HibernateShopDao;
import by.fakeonliner.entity.shop.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/shop")
public class ShopController {


    private final HibernateShopDao hibernateShopDao;

    @Autowired
    public ShopController(HibernateShopDao hibernateShopDao) {
        this.hibernateShopDao = hibernateShopDao;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("shop", new Shop());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("shop") Shop shop, Model model, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "registration";
            }

            if (hibernateShopDao.existByEmail(shop.getEmail())) {
                model.addAttribute("message", "Email already exist");
                return "registration";
            } else {
                hibernateShopDao.save(shop);
                return "redirect:/shop/authorization";
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }

        return "registration";
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("shop", new Shop() {
        });
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("shop") Shop shop, Model model, HttpSession httpSession) {
        Shop byEmail = hibernateShopDao.getShopByEmail(shop.getEmail());
        try {
            if (byEmail != null) {
                if (byEmail.getPassword().equals(shop.getPassword())) {
                    httpSession.setAttribute("shop", byEmail);
                    return "redirect:/";
                } else {
                    model.addAttribute("message","Password not equals");
                }
            } else {
                model.addAttribute("message","email is empty");
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "authorization";

    }

    @GetMapping("/profile")
    public String profile(@RequestParam("shopId") long id, Model model) {
        Shop shop = hibernateShopDao.findById(id);
        model.addAttribute("shop", shop);
        return "profile";
    }

//    @PostMapping("/profile")
//    public String profile(){
//
//    }

    @GetMapping("/profileUpdate")
    public String profileUpdate(@RequestParam("shopId") long id, Model model) {
        Shop shop = hibernateShopDao.findById(id);
        model.addAttribute("shop", shop);
        return "update";
    }

    @PostMapping("/profileUpdate")
    public String profileUpdate(@Valid @ModelAttribute("shop") Shop shop, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "update";
            }
            hibernateShopDao.edit(shop);
            model.addAttribute("message", "Shop data has been update");
            return "redirect:/shop/profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect:/shop/profile";
    }

    @GetMapping("/storeBase")
    public String storeProductDatabaseManagement(@RequestParam("shopId") long id, Model model) {
        Shop shop = hibernateShopDao.findById(id);
        model.addAttribute("shop", shop);
        return "storeBase";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
