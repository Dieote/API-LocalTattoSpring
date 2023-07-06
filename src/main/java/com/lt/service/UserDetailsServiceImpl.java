package com.lt.service;

import com.lt.dao.UserDAO;
import com.lt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = this.userDao.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
        return usuario;
    }
}
