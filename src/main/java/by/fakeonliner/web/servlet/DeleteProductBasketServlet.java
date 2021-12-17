package by.fakeonliner.web.servlet;

import by.fakeonliner.dto.ProductDto;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.BasketService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/deleteItem", name = "DeleteProductBasketServlet")
public class DeleteProductBasketServlet extends HttpServlet {

    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        basketService = BasketService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(ConstantPath.BASKET_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numberProduct = req.getParameter("productBasketNumber");
        List<ProductDto> list = (List<ProductDto>) req.getSession().getAttribute("basketList");

        ProductDto product = list.get(Integer.parseInt(numberProduct));

        if (req.getSession().getAttribute("guest") == null) {
            User user = (User) req.getSession().getAttribute("user");
            basketService.deleteProductFromBd(product.getId(), user.getId());
        } else {
            basketService.deleteProductFromMemory(product.getId());
        }

        resp.sendRedirect("/basket");
    }
}
