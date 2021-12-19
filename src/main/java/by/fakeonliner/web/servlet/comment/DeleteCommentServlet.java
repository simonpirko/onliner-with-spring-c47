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

@WebServlet("/delete")
public class DeleteCommentServlet extends HttpServlet {
    private final CommentService commentService = new CommentService();
    private final Comment comment = new Comment();
    private long commentID;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        commentID = Long.parseLong(req.getParameter("commentID"));
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
//        if (user.getId() == comment.getUser().getId()) {
//            if (commentService.deleteComment(commentID)) {
//                req.setAttribute("message_comment_delete", "Comment delete successfully ");
//            } else {
//                req.setAttribute("message_comment_delete", "Error");
//            }
//        } else {
//            req.setAttribute("message_comment_delete", "Comment not found");
//        }
//        getServletContext().getRequestDispatcher();
    }
}
