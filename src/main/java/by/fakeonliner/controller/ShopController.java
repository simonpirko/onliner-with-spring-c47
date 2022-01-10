package by.fakeonliner.controller;

import by.fakeonliner.dto.shop.AuthorizationShopDto;
import by.fakeonliner.dto.shop.SaveShopDto;
import by.fakeonliner.dto.shop.UpdateShopDto;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.service.ShopService;
import net.bytebuddy.matcher.StringMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {


    private final ShopService shopService;
    private final ProductService productService;

    @Autowired
    public ShopController(ShopService shopService, ProductService productService) {
        this.shopService = shopService;
        this.productService = productService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("shop", new SaveShopDto());
        return "/shop/registration";
    }

    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("shop") SaveShopDto saveShop, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "shop/registration";
            }
            if (shopService.existByEmail(saveShop.getEmail())) {
                model.addAttribute("message", "Email is already exist");
                return "shop/registration";
            } else {
                Shop shop = getShopFromSaveDto(saveShop);
                shopService.save(shop);
                return "redirect:/shop/authorization";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "shop/registration";
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        model.addAttribute("shop", new AuthorizationShopDto());
        return "shop/authorization";
    }

    @PostMapping("/authorization")
    public String authorization(@Valid @ModelAttribute("shop") AuthorizationShopDto authShop, BindingResult result, Model model, HttpSession httpSession) {
        try {
            if (result.hasErrors()) {
                return "shop/authorization";
            }
            if (shopService.existByEmail(authShop.getEmail())) {
                Shop shop = shopService.getShopByEmail(authShop.getEmail());
                if (shop.getPassword().equals(authShop.getPassword())) {
                    httpSession.setAttribute("shop", shop);
                    return "redirect:/";
                } else {
                    model.addAttribute("message", "Password not equals");
                }
            } else {
                model.addAttribute("message", "User not found");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "shop/authorization";
    }

    @GetMapping("/profile")
    public String profile(@RequestParam("shopId") long id, Model model) {
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "shop/profile";
    }

    @GetMapping("/profileShop")
    public String profileUpdate(Model model, HttpSession session) {
        Shop shop = (Shop) session.getAttribute("shop");
        model.addAttribute("updateShop", shop);
        return "shop/profile";
    }

    @PostMapping("/profileShop")
    public String profileUpdate(@Valid @ModelAttribute("updateShop") UpdateShopDto updateShopDto, BindingResult bindingResult,
                                Model model, HttpSession session) {
        try {
            if (!bindingResult.hasErrors()) {
                Shop shop = getShopFromUpdateDto(updateShopDto);
                shopService.edit(shop);
                Shop shopDb = shopService.getShopByEmail(shop.getEmail());
                session.setAttribute("shop", shopDb);
                model.addAttribute("messageComplete", true);
            }
            return "shop/profile";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "shop/profile";
    }

    @GetMapping("/storeBase")
    public String storeProductDatabaseManagement(@RequestParam("shopId") long id, Model model) {
        Shop shop = shopService.findById(id);
        model.addAttribute("shop", shop);
        return "storeBase";
    }

    @GetMapping("/products")
    public String shopProducts(HttpSession session, Model model) {
        Shop shop = (Shop) session.getAttribute("shop");
        model.addAttribute("products", shop.getProducts());
        return "/shop/products";
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(long id, Model model, HttpSession session) {
//        Shop shop = (Shop) session.getAttribute("shop");
//        Shop shopDb = shopService.getShopByEmail(shop.getEmail());
//        Shop convertedShop = deleteProduct(shopDb, id);
//        shopService.edit(convertedShop);
        Shop shop = (Shop) session.getAttribute("shop");
        shop = shopService.deleteProductFromShop(shop, id);
        session.setAttribute("shop", shop);
        return "redirect: /shop/products";
    }

    @PostMapping ("/changePrice")
    public String changePrice(@NotBlank(message = "Field is empty") String price, long id, HttpSession session, Model model) {
        try {
            float priceProduct = Float.parseFloat(price);
            Shop shop = (Shop) session.getAttribute("shop");
            Shop convertedShop = changeProductPrice(shop, id, priceProduct);
            shopService.edit(convertedShop);
            session.setAttribute("shop", convertedShop);
            return "redirect: /shop/products";
        } catch (Exception e) {
            model.addAttribute("errorId", id);
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "redirect: /shop/products";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }


    private Shop getShopFromUpdateDto(UpdateShopDto updateShopDto) {
        Shop shop = new Shop();
        shop.setName(updateShopDto.getName());
        shop.setEmail(updateShopDto.getEmail());
        shop.setPhoneNumber(updateShopDto.getPhoneNumber());
        shop.setContactAddress(updateShopDto.getContactAddress());
        shop.setPassword(updateShopDto.getPassword());
        shop.setDescription(updateShopDto.getDescription());
        return shop;
    }

    private Shop getShopFromSaveDto(SaveShopDto saveShopDto) {
        Shop shop = new Shop();
        shop.setName(saveShopDto.getName());
        shop.setEmail(saveShopDto.getEmail());
        shop.setPhoneNumber(saveShopDto.getPhoneNumber());
        shop.setContactAddress(saveShopDto.getContactAddress());
        shop.setPassword(saveShopDto.getPassword());
        shop.setDescription(saveShopDto.getDescription());
        shop.setAmountOfMarks(saveShopDto.getAmountOfMarks());
        shop.setNumberOfMarks(saveShopDto.getNumberOfMarks());
        shop.setProducts(new ArrayList<>());
        return shop;
    }

    private Shop deleteProduct(Shop shop, long id) {
        List<Product> products = new ArrayList<>();
        for (Product product : shop.getProducts()) {
            if (product.getId() != id) {
                products.add(product);
            }
        }
        shop.setProducts(products);
        return shop;
    }

    private Shop changeProductPrice(Shop shop, long id, float price) {
        for (int i = 0; i < shop.getProducts().size(); i++) {
            if (shop.getProducts().get(i).getId() == id) {
                shop.getProducts().get(i).setPrice(price);
                break;
            }
        }
        return shop;
    }


}
