package by.fakeonliner.controller;


import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping
    public String index(Model model,User user){
        System.out.println(user.toString());
        userService.save(user);
        return "redirect:/";
    }
}
