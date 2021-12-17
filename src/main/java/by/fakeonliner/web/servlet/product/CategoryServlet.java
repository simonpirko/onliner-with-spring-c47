package by.fakeonliner.web.servlet.product;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/product/category")
public class CategoryServlet extends HttpServlet {
    private final static String CATEGORY = "mobile";

    private ProductService productService;


    @Override
    public void init() throws ServletException {
        productService = ProductService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDto> mobileProduct = productService.findByAllFromCategory(CATEGORY);
        req.setAttribute("productList", mobileProduct);
        getServletContext().getRequestDispatcher(ConstantPath.CATEGORY_MOBILE_JSP).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}