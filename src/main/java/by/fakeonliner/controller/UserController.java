package by.fakeonliner.controller;

import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.entity.user.RoleUser;
import by.fakeonliner.entity.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private HibernateUserDao hibernateUserDao;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("newUser", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("newUser") User user, Model model, BindingResult result){
        try {
            if (result.hasErrors()) {
                return "registration";
            }

            if (hibernateUserDao.existByEmail(user.getEmail())) {
                model.addAttribute("message", "Email already exist");
                return "registration";
            } else {
                user.setRoleUser(RoleUser.USER);
                hibernateUserDao.save(user);
                return "redirect:/user/authorization";
            }
        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "registration";
    }

    @GetMapping("/authorization")
    public String authorization(Model model){
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("user") User user, Model model, HttpSession httpSession){
        User byUser = hibernateUserDao.findByEmail(user.getEmail());
        try {
            if(byUser != null){
                if (byUser.getPassword().equals(user.getPassword())){
                    httpSession.setAttribute("user", byUser);
                    return "redirect:/";
                }else {
                    model.addAttribute("message","Password not equals");
                }
            } else {
                model.addAttribute("message","email is empty");
            }

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "authorization";
    }

}
