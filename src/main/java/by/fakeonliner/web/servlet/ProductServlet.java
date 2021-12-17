package by.fakeonliner.web.servlet;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.service.ProductService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/product")
public class ProductServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = ProductService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String category = req.getParameter("category");
//        String numberProduct = req.getParameter("productNumber");
//        List<ProductDto> list = (List<ProductDto>) req.getSession().getAttribute("productList");
//
//        ProductDto productDto = list.get(Integer.parseInt(numberProduct));
//        Object product = productService.getProduct(productDto.getId(), category);
//
//        req.setAttribute("productDto", productDto);
//        req.setAttribute("product", product);

        String id = req.getParameter("id");
        ProductDto productDto = productService.findById(Integer.parseInt(id));
        req.setAttribute("product", productDto);

        getServletContext().getRequestDispatcher(ConstantPath.PRODUCT_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}