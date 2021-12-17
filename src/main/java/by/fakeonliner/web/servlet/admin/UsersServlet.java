package by.fakeonliner.web.servlet.admin;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.AdminService;
import by.fakeonliner.service.UserService;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/admin/users", name = "UsersServlet")
public class UsersServlet extends HttpServlet {

    private UserService userService;
    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        req.setAttribute("userList", users);
        getServletContext().getRequestDispatcher(ConstantPath.ADMIN_USERS_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userNumber = req.getParameter("userNumber");
        String operation = req.getParameter("userOperation");

        List<User> users = (List<User>) req.getAttribute("userList");
        User user = users.get(Integer.parseInt(userNumber));

        users = adminService.performOperation(operation, user, users, userNumber);

        req.setAttribute("userList", users);

        getServletContext().getRequestDispatcher(ConstantPath.ADMIN_USERS_JSP).forward(req, resp);
    }
}
