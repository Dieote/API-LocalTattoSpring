package com.lt.controller;

import com.lt.domain.Comment;
import com.lt.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> postComment(@RequestBody Comment comment) {
        String username = comment.getUsername();
        commentService.saveComment(comment, username);
        return ResponseEntity.ok().body("{\"message\": \"Funciona\"}");
    }
    
    @DeleteMapping("/delete-comment/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable("id") Long id ) {
        Comment userComment = new Comment();
        userComment.setId(id);
        log.info("Eliminado comentario con id " + id);

        String deleteMessage = commentService.delete(userComment);
        if(deleteMessage.equals("Comentario eliminado")){
            Map<String, String> response = new HashMap<>();
            response.put("message", deleteMessage);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
