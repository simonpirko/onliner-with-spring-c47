package by.fakeonliner.controller;


import by.fakeonliner.dao.DescriptionFeatureValueDao;
import by.fakeonliner.dao.hibernate.HibernateProductDao;
import by.fakeonliner.dao.hibernate.HibernateUserDao;
import by.fakeonliner.entity.product.Category;
import by.fakeonliner.entity.product.DescriptionFeature;
import by.fakeonliner.entity.product.DescriptionFeatureValue;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HibernateUserDao hibernateUserDao;

    @Autowired
    private HibernateProductDao hibernateProductDao;

    @Autowired
    private DescriptionFeatureValueDao descriptionFeatureValueDao;

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
    public String authorization(@ModelAttribute("admin") User user, Model model, BindingResult result, HttpSession httpSession) {
        User byUser = hibernateUserDao.findByEmail(user.getEmail());
        try {
            if (result.hasErrors()) {
                return "/admin/authorizationAdmin";
            }
            if (byUser != null) {
                if (byUser.getRoleUser().equals(RoleUser.ADMIN)) {
                    if (byUser.getPassword().equals(user.getPassword())) {
                        httpSession.setAttribute("admin", byUser);
                        return "redirect:/admin/profileAdmin";
                    } else {
                        model.addAttribute("messagePassword", true);
                    }
                } else {
                    return "redirect:/";
                }
            } else {
                model.addAttribute("messageEmail", true);
            }

        } catch (Exception e) {
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

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        List<Category> categoryList = categoryService.getCategory();
        model.addAttribute("categoryList", categoryList);
        return "/admin/add_product";
    }

    @PostMapping("/add_product")
    public String addProduct(@ModelAttribute("newProduct") Product product, BindingResult bindingResult, Model model) {
        if (product != null) {
            if (productService.existByModel(product.getModel())) {
                model.addAttribute("existByModel");
                return "/admin/add_product";
            } else {
                productService.save(product);
            }
        }
        List<DescriptionFeature> descriptionFeatureList = categoryService.getDescriptionFeature(product.getCategoryId());
        for (DescriptionFeature d : descriptionFeatureList) {
            d.setDescriptionFeatureValues(descriptionFeatureValueDao.getByDescriptionFeatureId(d.getId()));
        }
        List<DescriptionFeatureValue> descriptionFeatureValueList = new ArrayList<>();
        model.addAttribute("descriptionFeatureList", descriptionFeatureList);
        model.addAttribute("descriptionFeatureValueList", descriptionFeatureValueList);
        model.addAttribute("savedProduct", product);
        return "/admin/add_product";
    }

    @GetMapping("/add_product_description")
    public String addProductDescription(Model model) {
        return "/admin/add_product";
    }

    @PostMapping("/add_product_description")
    public String addProductDescription(HttpServletRequest reg, Model model) {
        String[] ids = reg.getParameterValues("idArray");
        long[] arrays = Arrays.asList(ids).stream().mapToLong(Long::parseLong).toArray();
        String productId = reg.getParameter("productId");
        long productIdL = Long.parseLong(productId);
        productService.saveDescriptionValues(arrays, productIdL);
        return "redirect:/admin/add_product";
    }

    @GetMapping("/chose_category")
    public String choseCategory(Model model) {
        List<Category> categoryList = categoryService.getCategory();
        model.addAttribute("categoryList", categoryList);
        return "/admin/add_product";
    }

    @PostMapping("/chose_category")
    public String choseCategory(Model model, long id) {
        Product product = new Product();
        product.setCategoryId(id);
        model.addAttribute("newProduct", product);
        model.addAttribute("id", id);
        model.addAttribute("newCategory", new Category());
        return "/admin/add_product";
    }


    @GetMapping("/deleteProduct")
    public String deletedProduct(User user, Model model) {
        User byUser = hibernateUserDao.findById(user.getId());
        model.addAttribute("admin", byUser);
        List<Category> categoryList = categoryService.getCategory();
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("productList", productList);
        return "/admin/deleteProduct";
    }

    @PostMapping("/deleteProduct")
    public String deletedProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/deleteProduct";
            }
            hibernateProductDao.delete(product.getId());
            return "redirect:/admin/profileAdmin";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/deleteProduct";
    }

    @GetMapping("/editProduct")
    public String editProduct(@RequestParam("admin") long id, Model model) {
        User user = hibernateUserDao.findById(id);
        model.addAttribute("admin", user);
        return "/admin/editproduct";
    }

    @PostMapping("/editProduct")
    public String editProduct(@Valid @ModelAttribute("shop") Product product, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "/admin/editproduct";
            }
            hibernateProductDao.edit(product);

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
        }
        return "/admin/editproduct";
    }

}
