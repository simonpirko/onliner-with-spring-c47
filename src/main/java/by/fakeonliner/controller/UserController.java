package by.fakeonliner.controller;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.hibernate.HibernateUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    private final HibernateUserDao hibernateUserDao;

    @Autowired
    public UserController(HibernateUserDao hibernateUserDao) {
        this.hibernateUserDao = hibernateUserDao;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (hibernateUserDao.existByEmail(user.getEmail())) {
            model.addAttribute("message", "Such a user exist");
            return "registration";
        } else {
            hibernateUserDao.save(user);
            return "redirect:/";
        }
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("user", new User());
        return "authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@ModelAttribute("user") User user, Model model,
                                HttpSession session) {
        if (hibernateUserDao.existByEmail(user.getEmail())) {
            User userFromDB = hibernateUserDao.findByEmail(user.getEmail());
            if (userFromDB.getEmail().equals(user.getEmail())) {
                session.setAttribute("authorizedUser", user);
                return "redirect:/";
            } else {
                model.addAttribute("message", "Wrong password");
                return "authorization";
            }
        } else {
            model.addAttribute("message", "User does not exist");
            return "authorization";
        }
    }

    @GetMapping("/profileUpdate")
    public String updateUser(@RequestParam("userId") long id, Model model) {
        User user = hibernateUserDao.findById(id);
        model.addAttribute("user", user);
        return "userInfo";
    }

    @PostMapping("/profileUpdate")
    public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "userInfo";
        }
        hibernateUserDao.save(user);
        model.addAttribute("message", "User data has been updated");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
