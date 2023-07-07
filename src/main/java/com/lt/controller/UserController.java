package com.lt.controller;

import com.lt.domain.Artista;
import com.lt.domain.Rol;
import com.lt.domain.User;
import com.lt.domain.UserRol;
import com.lt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
public class UserController {
 
    @Autowired
    private UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping ("/post-user")
    public User guardarUsuario(@RequestBody User usuario) throws Exception{
        usuario.setPassword(this.bCryptPasswordEncoder.encode(usuario.getPassword()));

        Set<UserRol> userroles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setNameRol("USER");

        UserRol usuarioRol = new UserRol();
        usuarioRol.setUser(usuario);
        usuarioRol.setRol(rol);

        userroles.add(usuarioRol);
        return usersService.guardarUsuario(usuario,userroles);
    }

    @GetMapping("/{username}")
    public User obtenerUsuario(@PathVariable("username") String username){
        return usersService.obtenerUsuario(username);
    }

    @DeleteMapping("/{id}")
    public  void eliminarUsuario(@PathVariable("id") Long id){
        usersService.eliminarUsuario(id);
    }
}
