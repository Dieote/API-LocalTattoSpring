package com.lt.service;

import com.lt.domain.Comment;
import java.util.List;

public interface CommentService {
    
    public List<Comment> getComment();
    
    public String saveComment(Comment comment, String username);
    
    public String delete(Comment comment);
}
