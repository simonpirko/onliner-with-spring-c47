package by.fakeonliner.controller;

import by.fakeonliner.dao.hibernate.HibernateProductDao;
import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.user.RoleUser;
import by.fakeonliner.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private HibernateUserDao hibernateUserDao;
    private HibernateProductDao hibernateProductDao;

    @GetMapping("/authorizationAdmin")
    public String authorization(Model model) {
        model.addAttribute("admin", new User());
        return "/admin/authorizationAdmin";
    }

    @PostMapping("/authorizationAdmin")
    public String authorization(@ModelAttribute("admin") User user, Model model, BindingResult result, HttpSession httpSession){
        User byUser = hibernateUserDao.findByEmail(user.getEmail());
        try {
            if (result.hasErrors()) {
                return "/admin/authorizationAdmin";
            }
            if(byUser != null){
                if(user.getRoleUser() == RoleUser.ADMIN){
                    if (byUser.getPassword().equals(user.getPassword())){
                        httpSession.setAttribute("admin", byUser);
                        return "redirect:/admin/profileAdmin";
                    }else {
                        model.addAttribute("messagePassword",true);
                    }
                }else {
                    return "redirect:/";
                }
            }else {
                model.addAttribute("messageEmail",true);
            }

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/authorizationAdmin";
    }

    @GetMapping("/profileAdmin")
    public String profile(@RequestParam("admin") long id, Model model) {
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/profileAdmin";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("admin") long id, Model model){
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/addproduct";
            }
            hibernateProductDao.save(product);
            return "redirect:/admin/profile";
        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/addproduct";
    }

    @GetMapping("/deleteProduct")
    public String deletedProduct(@RequestParam("admin") long id, Model model){
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/deleteproduct";
    }

    @PostMapping("/deleteProduct")
    public String deletedProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/deleteproduct";
            }
            hibernateProductDao.delete(product.getId());
            return "redirect:/admin/profile";

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/deleteproduct";
    }

    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("admin") long id, Model model){
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/editproduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/editproduct";
            }
            hibernateProductDao.edit(product);

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/editproduct";
    }
}
