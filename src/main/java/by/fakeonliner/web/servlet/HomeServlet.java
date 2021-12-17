package by.fakeonliner.web.servlet;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.BasketService;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private ProductService productService;
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        productService = ProductService.getInstance();
        basketService = BasketService.getInstance();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDto> homeProductList = productService.getProductListHome();
        req.getSession().setAttribute("homeProductList", homeProductList);
        if (req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("guest", 1);
        }
        getServletContext().getRequestDispatcher(ConstantPath.HOME_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productNumber = req.getParameter("productNumber");
        List<ProductDto> products = (List<ProductDto>) req.getSession().getAttribute("homeProductList");

        ProductDto product = products.get(Integer.parseInt(productNumber));

        if(req.getSession().getAttribute("guest") == null) {
            User user = (User) req.getSession().getAttribute("user");
            basketService.addProductDb(user.getId(), product.getId());
        } else {
            basketService.addProduct(product);
        }

        getServletContext().getRequestDispatcher(ConstantPath.HOME_JSP).forward(req, resp);
    }
}
