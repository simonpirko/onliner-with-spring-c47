package by.fakeonliner.web.servlet.comment;

import by.fakeonliner.entity.product.Comment;
import by.fakeonliner.entity.user.User;
import by.fakeonliner.service.CommentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static by.fakeonliner.controller.constant.ConstantPath.COMMENT_ADD_JSP;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {
    private final CommentService commentService = new CommentService();
    private long productID = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = req.getParameter("comment");
        User user = (User) req.getSession().getAttribute("user");
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
//        Comment comment = new Comment(user, productID, message, timestamp);
//        if(commentService.addComment(comment)){
//            req.setAttribute("message_comment_add", "Comment added successfully ");
//        } else {
//            req.setAttribute("message_comment_add", "Error");
//        }
//        getServletContext().getRequestDispatcher(COMMENT_ADD_JSP);
    }
}
