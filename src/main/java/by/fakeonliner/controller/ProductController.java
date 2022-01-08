package by.fakeonliner.controller;

import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.service.CategoryService;
import by.fakeonliner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String category(Model model, long id) {
        List<Product> productList = productService.getByCategoryId(id);
        model.addAttribute("productList", productList);
        return "product/category";
    }

    @PostMapping("/category")
    public String category(Model model) {
        return "product/category";
    }

    @GetMapping("/product")
    public String product(Model model, long id) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        return "product/product";
    }

    @PostMapping("/product")
    public String product() {

        return "product/product";
    }
}
