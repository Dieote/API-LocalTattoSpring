package com.lt.service;

import com.lt.dao.CommentDAO;
import com.lt.domain.Comment;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
    
    @Autowired
    private CommentDAO commentDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<Comment> getComment(){
        List<Comment> lista = new ArrayList<>();
        commentDao.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    @Transactional
    public String saveComment(Comment comment, String username) {
        try {
            if (comment.getComment() == null){
                throw new IllegalAccessException("El comentario no puede ser vacio");
            }
            comment.setUsername(username);
            commentDao.save(comment);
            return comment.getComment();
        } catch (Exception e) {
            return "Error al realizar el post " + e.getMessage();
        }
    }
    
    @Override
    @Transactional
    public String delete(Comment comment) {
        commentDao.delete(comment);
        return "Comentario eliminado";
    }
}

