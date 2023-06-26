package com.lt.dao;

import com.lt.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO extends CrudRepository<Comment,Long>{
    
}
