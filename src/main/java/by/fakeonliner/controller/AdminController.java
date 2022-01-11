package by.fakeonliner.controller;

import by.fakeonliner.dao.hibernate.HibernateProductDao;
import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.Product;
import by.fakeonliner.entity.user.RoleUser;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.CategoryService;
import by.fakeonliner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @Autowired
    private HibernateProductDao hibernateProductDao;

    private final ProductService productService;

    private final CategoryService categoryService;

    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/authorizationAdmin")
    public String authorization(Model model) {
        model.addAttribute("admin", new User());
        return "/admin/authorizationAdmin";
    }

    @PostMapping("/authorizationAdmin")
    public String authorization(@ModelAttribute("admin") User user, Model model, BindingResult result, HttpSession httpSession){
        User byUser = hibernateUserDao.findByEmail(user.getEmail());
        try {
            if (result.hasErrors()) {
                return "/admin/authorizationAdmin";
            }
            if(byUser != null){
                if(byUser.getRoleUser().equals(RoleUser.ADMIN)){
                    if (byUser.getPassword().equals(user.getPassword())){
                        httpSession.setAttribute("admin", byUser);
                        return "redirect:/admin/profileAdmin";
                    }else {
                        model.addAttribute("messagePassword",true);
                    }
                }else {
                    return "redirect:/";
                }
            }else {
                model.addAttribute("messageEmail",true);
            }

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/authorizationAdmin";
    }

    @GetMapping("/profileAdmin")
    public String profile(User user, Model model) {
        User byUser = hibernateUserDao.findById(user.getId());
        model.addAttribute("adminProf", byUser);
        return "/admin/profileAdmin";
    }

    @GetMapping("/addProduct")
    public String addProduct(@RequestParam("admin") long id, Model model){
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/addproduct";
            }
            hibernateProductDao.save(product);
            return "redirect:/admin/profile";
        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/addproduct";
    }

    @GetMapping("/showAllProduct")
    public String showAllProduct(Model model, User user){
        User byUser = hibernateUserDao.findById(user.getId());
        model.addAttribute("admin", byUser);

        List<Category> categoryList = categoryService.getCategory();
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "/admin/showAllProduct";
    }

    @GetMapping("/showProduct/{id}")
    public String showProduct(@PathVariable(value = "id") long id, Model model, User user){
        User byUser = hibernateUserDao.findById(user.getId());
        model.addAttribute("admin", byUser);
//        Product productList = hibernateProductDao.findById(id);
//        Optional<Product> productList = Optional.ofNullable(hibernateProductDao.findById(id));
        Product productList = productService.getById(id);
        model.addAttribute("productList", productList);
        return "/admin/showProduct";
    }

//    @GetMapping("/deleteProduct")
//    public String deletedProduct(User user, Model model){
//        User byUser = hibernateUserDao.findById(user.getId());
//        model.addAttribute("admin", byUser);
//
//        List<Category> categoryList = categoryService.getCategory();
//        List<Product> productList = productService.getAllProducts();
//        model.addAttribute("categoryList", categoryList);
//        model.addAttribute("productList", productList);
//        return "/admin/deleteProduct";
//    }

    @PostMapping("/showProduct/{id}/delete")
    public String deletedProduct(@PathVariable(value = "id") long id, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/delete";
            }
            Product product1 = hibernateProductDao.findById(id);
            hibernateProductDao.delete(product1);
            return "/admin/showAllProduct";

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/showProduct";
    }

    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("admin") long id, Model model){
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/editproduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/editproduct";
            }
            hibernateProductDao.edit(product);

        }catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/editproduct";
    }
}
