package by.fakeonliner.controller;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.service.ShopService;
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


    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
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

            if (shopService.existByEmail(shop.getEmail())) {
                model.addAttribute("message", "Email already exist");
                return "registration";
            } else {
                shopService.save(shop);
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
        Shop byEmail = shopService.getShopByEmail(shop.getEmail());
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
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "shop/profile";
    }

    @GetMapping("/profileUpdate")
    public String profileUpdate(Model model, HttpSession session) {
        Shop shop = (Shop) session.getAttribute("shop");
//        Shop shop = new Shop("sanek@gmail.com", "123", "name", "+375336495525", "address", "descriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescriptiondescription");
        model.addAttribute("shop", shop);
        return "shop/update";
    }

    @PostMapping("/profileUpdate")
    public String profileUpdate(@Valid @ModelAttribute("shop") Shop shop, BindingResult bindingResult,
                                Model model, HttpSession session) {
        try {
            if (!bindingResult.hasErrors()) {
                shopService.edit(shop);
                session.setAttribute("shop", shop);
                model.addAttribute("messageComplete", true);
            }
            return "shop/profileUpdate";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "shop/profileUpdate";
    }

    @GetMapping("/storeBase")
    public String storeProductDatabaseManagement(@RequestParam("shopId") long id, Model model) {
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "storeBase";
    }


    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
