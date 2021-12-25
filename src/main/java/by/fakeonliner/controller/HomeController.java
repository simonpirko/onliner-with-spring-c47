package by.fakeonliner.controller;


import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }
}
