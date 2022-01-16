package by.fakeonliner.controller;


import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.CategoryService;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
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
    public String index(Model model, HttpSession httpSession) {
        List<Category> categoryList = categoryService.getCategory();
        List<Product> productList = productService.getAllProducts();
        httpSession.setAttribute("categoryList", categoryList);
        model.addAttribute("user", new User());
        model.addAttribute("productList", productList);
        return "home";
    }

}
