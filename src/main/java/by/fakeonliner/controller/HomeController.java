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

import java.util.List;

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
        List<Category> category = categoryService.getCategory();
        List<Product> productList = productService.getAllProducts();
        Category category1 = new Category();
        model.addAttribute("user", new User());
        model.addAttribute("categoryList", category);
        model.addAttribute("category", category1);
        model.addAttribute("productList", productList);
        model.addAttribute("descFeature", new DescriptionFeature());
        model.addAttribute("descFeatureVal", new DescriptionFeatureValue());
        return "home";
    }

    @PostMapping
    public String index(Model model,User user,Category category,DescriptionFeature descriptionFeature,DescriptionFeatureValue descriptionFeatureValue){
        categoryService.saveCategory(category);
        return "redirect:/";
    }
}
