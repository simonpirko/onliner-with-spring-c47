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
import java.util.List;

@WebServlet("/shop/authorization")
public class AuthorizationShopServlet extends HttpServlet {
    private ShopService shopService = new ShopService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getServletContext().getRequestDispatcher(ConstantPath.SHOP_AUTHORIZATION_JSP).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Shop shop = shopService.findByEmail(email);
        verificationPassword(req, resp, password, shop);
    }

    private void verificationPassword(HttpServletRequest req, HttpServletResponse resp, String password, Shop shop) throws IOException, ServletException {
        if (shop != null) {
            if (shop.getPassword().equals(password)) {
                List<Shop> shopList = shopService.getShopList();
                req.getSession().setAttribute("shop", shop);
                req.getSession().setAttribute("shopList", shopList);
                resp.sendRedirect(ConstantPath.HOME_JSP);
                return;
            } else {
                req.setAttribute("message", "Wrong password");
            }
        } else {
            req.setAttribute("message", "Shop not found");
        }
        getServletContext().getRequestDispatcher(ConstantPath.SHOP_AUTHORIZATION_JSP).forward(req, resp);
    }
}
