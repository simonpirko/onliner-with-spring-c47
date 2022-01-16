package by.fakeonliner.controller.filter;


import by.fakeonliner.entity.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/admin")
public class UserFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("authUser");
        if (user.getRoleUser().toString()=="ADMIN") {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect("/authorization");
        }
    }
}
