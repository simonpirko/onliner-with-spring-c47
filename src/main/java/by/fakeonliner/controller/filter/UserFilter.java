package by.fakeonliner.controller.filter;


import by.fakeonliner.entity.user.User;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/admin")
public class UserFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) request.getAttribute("authUser");
        if (user.getRoleUser().toString().equals("ADMIN")) {
            chain.doFilter(request, response);
        } else {
//            response.sendRedirect("/authorization");
        }
    }

//    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
//        User user = (User) req.getSession().getAttribute("authUser");
//        if (user.getRoleUser().toString().equals("ADMIN")) {
//            chain.doFilter(req, res);
//        } else {
//            res.sendRedirect("/authorization");
//        }
//    }
}
