package by.fakeonliner.service;

import by.fakeonliner.entity.product.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    private final List<Comment> commentList = new ArrayList<>();

    public boolean addComment(Comment comment){
        return commentList.add(comment);
    }

    public boolean deleteComment(long id) {
        Comment comment = (Comment) commentList.stream().filter(user -> user.getCommentID() == id);
        return  commentList.remove(comment);
    }
}
