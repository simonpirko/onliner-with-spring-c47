package by.fakeonliner.controller;

import by.fakeonliner.entity.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basket")
public class BasketController {

    @GetMapping("/saveInBasket")
    public String save(Model model, long id) {
        System.out.println(id);
        return "redirect:/";
    }
}
