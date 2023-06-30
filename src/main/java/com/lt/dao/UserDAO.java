package com.lt.dao;

import com.lt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,Long> {
    
    public User findByUsername(String username);
}


