package com.lt.controller;

import com.lt.domain.Comment;
import com.lt.service.CommentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @GetMapping("/get-comments")
    @ResponseBody
    public List<Comment>getComment(){
        return commentService.getComment();
    }
    
    @PostMapping("/post-comments")
    @ResponseBody
    public String postComment(@RequestBody Comment comment) {
        commentService.saveComment(comment);
        return ("Funciona");
    }
    
    @DeleteMapping("/delete-comment/{id}")
    @ResponseBody
    public String deleteComment(@PathVariable("id") Long id ) {
        Comment userComment = new Comment();
        userComment.setId(id);
        log.info("Eliminado comentario con id " + id);
         return commentService.delete(userComment);
    }
}
