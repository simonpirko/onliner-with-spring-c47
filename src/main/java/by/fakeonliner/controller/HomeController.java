package by.fakeonliner.controller;


import by.fakeonliner.entity.product.Laptop;
import by.fakeonliner.entity.product.Mobile;
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

        Mobile product = new Mobile();
        product.setBrand("brand");
        product.setPrice(900);
        product.setModel("model");
        product.setMarketLaunchDate(2021);
        product.setAverageRating(20);
        product.setDescription("description");
        product.setUrlImage("url");
        product.setMobileType("Type");
        productService.save(product);
        Class aClass = product.getClass();
        productService.findProduct();
//        userService.save(user);

        Laptop product2 = new Laptop();
        product2.setBrand("brand");
        product2.setPrice(900);
        product2.setModel("model");
        product2.setMarketLaunchDate(2021);
        product2.setAverageRating(20);
        product2.setDescription("description");
        product2.setUrlImage("url");
        product2.setVideoCard("video");
        productService.save(product2);
        productService.findProduct();
        return "redirect:/";
    }
}
