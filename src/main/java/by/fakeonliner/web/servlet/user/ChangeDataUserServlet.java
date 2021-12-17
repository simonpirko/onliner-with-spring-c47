package by.fakeonliner.web.servlet.user;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.UserService;
import by.fakeonliner.controller.constant.ConstantMessage;
import by.fakeonliner.controller.constant.ConstantPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/user/changerUser", name = "ChangeDataUser")
public class ChangeDataUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getServletContext().getRequestDispatcher(ConstantPath.CHANGE_DATA_USER_JSP).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newFirstName = req.getParameter("firstname");
        String newLastName = req.getParameter("lastname");
        String newPassword = req.getParameter("password");
        String newUsername = req.getParameter("username");
        String newPhoneNumber = req.getParameter("phoneNumber");
        String newEmail = req.getParameter("email");

        User user = (User) req.getSession().getAttribute("user");

        if (checkForNullAndEmpty(newFirstName)) {
            if (!user.getFirstName().equals(req.getParameter("firstname"))) {
                userService.changeFirstName(user.getId(), newFirstName);
            }
        } else {
            req.setAttribute("firstNameMessage", ConstantMessage.FIRST_NAME_IS_EMPTY);
        }

        if (checkForNullAndEmpty(newLastName)) {
            if (!user.getLastName().equals(req.getParameter("lastname"))) {
                userService.changeLastName(user.getId(), newLastName);
            }
        } else {
            req.setAttribute("lastNameMessage", ConstantMessage.LAST_NAME_IS_EMPTY);
        }

        if (checkForNullAndEmpty(newUsername)) {
            if (!user.getUsername().equals(req.getParameter("username"))) {
                if (!userService.existByUsername(newUsername)) {
                    userService.changeUsername(user.getId(), newUsername);
                } else {
                    req.setAttribute("usernameMessage", ConstantMessage.USER_ALREADY_EXIST);
                }
            }
        } else {
            req.setAttribute("usernameMessage", ConstantMessage.USERNAME_IS_EMPTY);
        }

        if (checkForNullAndEmpty(newPassword)) {
            if (!user.getPassword().equals(req.getParameter("password"))) {
                userService.changePassword(user.getId(), newPassword);
            }
        } else {
            req.setAttribute("passwordMessage", ConstantMessage.PASSWORD_IS_EMPTY);
        }

        if (checkForNullAndEmpty(newPhoneNumber)) {
            if (!user.getPhoneNumber().equals(req.getParameter("phonenumber"))) {
                if (!userService.existByPhoneNumber(newPhoneNumber)) {
                    userService.changePhoneNumber(user.getId(), newPhoneNumber);
                }
            } else {
                req.setAttribute("phoneMessage", ConstantMessage.PHONE_NUMBER_ALREADY_EXIST);
            }
        } else {
            req.setAttribute("phoneMessage", ConstantMessage.PHONE_NUMBER_IS_EMPTY);
        }

        if (checkForNullAndEmpty(newEmail)) {
            if (!user.getEmail().equals(req.getParameter("email"))) {
                if (!userService.existByEmail(newEmail)) {
                    userService.changeEmail(user.getId(), newEmail);
                }
            } else {
                req.setAttribute("emailMessage", ConstantMessage.EMAIL_ALREADY_EXIST);
            }
        } else {
            req.setAttribute("emailMessage", ConstantMessage.EMAIL_IS_EMPTY);
        }
        resp.sendRedirect("/user/changerUser");
    }


    private boolean checkForNullAndEmpty(String value) {
        return value != null && !value.isEmpty();
    }

}