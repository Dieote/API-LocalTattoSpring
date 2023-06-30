package com.lt.service;

import com.lt.domain.UserRol;
import com.lt.domain.User;
import com.lt.dao.RolDAO;
import com.lt.dao.UserDAO;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UserDAO usersDao;
    
    @Autowired
    private RolDAO rolDao;


    @Override
    public User guardarUsuario(User usuario, Set<UserRol> usuarioRoles) throws Exception {
        User usuarioLocal = usersDao.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("El usuario ya existe");
            throw new Exception("El usuario ya esta presente");
        }
        else{
            for(UserRol usuarioRol:usuarioRoles){
                rolDao.save(usuarioRol.getRol());
            }
            usuario.getUserRoles().addAll(usuarioRoles);
            usuarioLocal = usersDao.save(usuario);
        }
        return usuarioLocal;
    }

    @Override
    public User obtenerUsuario(String username) {
    return usersDao.findByUsername(username);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usersDao.deleteById(id);
    }
}
