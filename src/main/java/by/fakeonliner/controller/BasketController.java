package by.fakeonliner.controller;

import by.fakeonliner.dto.ProductBasketDto;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.BasketService;
import by.fakeonliner.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;
    private final ProductService productService;

    public BasketController(BasketService basketService, ProductService productService) {
        this.basketService = basketService;
        this.productService = productService;
    }

    @PostMapping("/save")
    public String save(Model model, long id) {
        Product product = productService.getById(id);
        ProductBasketDto productBasket = new ProductBasketDto(product);
        basketService.save(productBasket);
        model.addAttribute("productId", id);
        return "redirect: /";
    }

    @GetMapping()
    public String showBasket(Model model) {
        List<ProductBasketDto> products = basketService.getProducts();
        float totalPrice = getTotalPrice(products);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("basketProducts", products);
        return "/basket";
    }

    @PostMapping("/changeCount")
    public String changeProductCount(long id, long count, Model model) {
        ProductBasketDto product = basketService.getProductById(id);
        basketService.changeProductCount(product, count);
        return "redirect: /basket";
    }

    @PostMapping("/delete")
    public String deleteProduct(long id) {
        basketService.deleteProduct(id);
        return "redirect: /basket";
    }


    private float getTotalPrice(List<ProductBasketDto> products) {
        float totalPrice = 0;
        for(ProductBasketDto product : products) {
            totalPrice += product.getProduct().getPrice() * product.getCount();
        }
        return totalPrice;
    }
}
