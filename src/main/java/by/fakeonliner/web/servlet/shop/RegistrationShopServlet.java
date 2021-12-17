package by.fakeonliner.web.servlet.shop;

import by.fakeonliner.entity.shop.Shop;
import by.fakeonliner.service.ShopService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/shop/registration")
public class RegistrationShopServlet extends HttpServlet {
    private final ShopService shopService = new ShopService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getServletContext().getRequestDispatcher(ConstantPath.SHOP_REGISTRATION_JSP).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String phoneNumber = req.getParameter("phoneNumber");
        String email = req.getParameter("email");

        if (shopService.save(new Shop(email, password, name, phoneNumber))) {
            resp.sendRedirect(ConstantPath.SHOP_AUTHORIZATION_JSP);
        } else {
            req.setAttribute("alert", "This email exists");
            getServletContext().getRequestDispatcher(ConstantPath.SHOP_REGISTRATION_JSP).forward(req, resp);
        }
    }
}