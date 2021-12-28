package by.fakeonliner.controller;


import by.fakeonliner.entity.product.*;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.CategoryService;
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
    private final CategoryService categoryService;

    public HomeController(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("category", new Category());
        model.addAttribute("descFeature", new DescriptionFeature());
        model.addAttribute("descFeatureVal", new DescriptionFeatureValue());
        return "home";
    }

    @PostMapping
    public String index(Model model,User user,Category category,DescriptionFeature descriptionFeature,DescriptionFeatureValue descriptionFeatureValue){
        System.out.println(user.toString());
//        Category category1 = new Category();
//        DescriptionFeature descriptionFeature = new DescriptionFeature();
//        DescriptionFeatureValue descriptionFeatureValue = new DescriptionFeatureValue();
//        category.setName("Mobile");
//        category.setName("Laptop");
//        descriptionFeature.setName("Ram");
//        descriptionFeature.setCategoryId(1);

//productService;
        Product product = new Product();
        product.setBrand("brand");
        product.setPrice(900);
        product.setModel("model");
        product.setMarketLaunchDate(2021);
        product.setAverageRating(20);
        product.setDescription("description");
        product.setUrlImage("url");
        productService.save(product);
        productService.findProduct();
//        userService.save(user);
        categoryService.saveCategory(category);

        Product product2 = new Product();
        product2.setBrand("brand");
        product2.setPrice(900);
        product2.setModel("model");
        product2.setMarketLaunchDate(2021);
        product2.setAverageRating(20);
        product2.setDescription("description");
        product2.setUrlImage("url");
        productService.save(product2);
        productService.findProduct();
        return "redirect:/";
    }
}
