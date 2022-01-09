package by.fakeonliner.controller;


import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.user.RoleUser;
import by.fakeonliner.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("newUser", new User());
        return "/user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("newUser") User user, Model model, BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "/user/registration";
            }

            if (hibernateUserDao.existByEmail(user.getEmail())) {
                user.setRoleUser(RoleUser.USER);
                hibernateUserDao.save(user);
                return "redirect:/user/authorization";
            } else {
//                model.addAttribute("message", "Email already exist");
                model.addAttribute("message", true);
                return "/user/registration";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/user/registration";
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("authUser", new User());
        return "/user/authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("authUser") User user, Model model, HttpSession httpSession) {
        User byUser = hibernateUserDao.findByEmail(user.getEmail());
        try {
            if (byUser != null) {
                if (byUser.getPassword().equals(user.getPassword())) {
                    httpSession.setAttribute("authUser", byUser);
                    return "redirect:/";
                } else {
                    model.addAttribute("messagePassword", true);
                }
            } else {
                model.addAttribute("messageUser", true);
            }

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/user/authorization";
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession, Model model) {
        httpSession.invalidate();
        model.addAttribute("newUser", new User());
        return "redirect:/";
    }

}
