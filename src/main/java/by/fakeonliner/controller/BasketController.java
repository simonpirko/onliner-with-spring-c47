package by.fakeonliner.controller;

import by.fakeonliner.dto.basket.OrderDto;
import by.fakeonliner.dto.basket.ProductBasketDto;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.BasketService;
import by.fakeonliner.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public String save(Model model, long id, HttpSession session) {
        Product product = productService.getById(id);
        ProductBasketDto productBasket = new ProductBasketDto(product);
        basketService.save(productBasket);
        long size = basketService.getBasketSize();
        session.setAttribute("basketSize", size);
        return "redirect: /";
    }

    @GetMapping()
    public String showBasket(Model model) {
        List<ProductBasketDto> products = basketService.getProducts();
        float totalPrice = getTotalPrice(products);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("basketProducts", products);
        return "/basket/basket";
    }

    @GetMapping("/order")
    public String orderProducts(Model model, HttpSession session) {
        User user = (User) session.getAttribute("authUser");
        OrderDto orderDto = new OrderDto();
        if(user != null) {
            setOrderDtoFields(orderDto, user);
        }
        long size = basketService.getBasketSize();
        session.setAttribute("basketSize", size);
        model.addAttribute("order", orderDto);
        return "/basket/order";
    }

    @PostMapping("/order")
    public String orderProducts(@Valid @ModelAttribute("order") OrderDto orderDto,
                                BindingResult result, Model model, HttpSession session) {
        try {
            if (result.hasErrors()) {
                return "basket/order";
            }
            List<ProductBasketDto> products = basketService.getProducts();
            basketService.deleteAllProducts();
            long size = basketService.getBasketSize();
            session.setAttribute("basketSize", size);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect: /basket";
    }

    @PostMapping("/changeCount")
    public String changeProductCount(long id, long count, Model model, HttpSession session) {
        ProductBasketDto product = basketService.getProductById(id);
        basketService.changeProductCount(product, count);
        long size = basketService.getBasketSize();
        session.setAttribute("basketSize", size);
        return "redirect: /basket";
    }

    @PostMapping("/delete")
    public String deleteProduct(long id, HttpSession session) {
        basketService.deleteProduct(id);
        long size = basketService.getBasketSize();
        session.setAttribute("basketSize", size);
        return "redirect: /basket";
    }


    private float getTotalPrice(List<ProductBasketDto> products) {
        float totalPrice = 0;
        for (ProductBasketDto product : products) {
            totalPrice += product.getProduct().getPrice() * product.getCount();
        }
        return totalPrice;
    }

    private void setOrderDtoFields(OrderDto orderDto, User user) {
        orderDto.setFirstName(user.getFirstName());
        orderDto.setLastName(user.getLastName());
        orderDto.setEmail(user.getEmail());
        orderDto.setPhoneNumber(user.getPhoneNumber());
    }
}
