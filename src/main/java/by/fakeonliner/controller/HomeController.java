package by.fakeonliner.controller;


import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;
    private final ProductService productService;

    public HomeController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "home";
    }

    @PostMapping
    public String index(Model model,User user){
        System.out.println(user.toString());

        Product product = new Product();
        product.setBrand("brand");
        product.setPrice(900);
        product.setModel("model");
        product.setMarketLaunchDate(2021);
        product.setAverageRating(20);
        product.setDescription("description");
        product.setUrlImage("url");
        productService.save(product);

        userService.save(user);
        return "redirect:/";
    }
}
