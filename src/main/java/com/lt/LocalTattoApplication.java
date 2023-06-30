package com.lt;

import com.lt.domain.Rol;
import com.lt.domain.User;
import com.lt.domain.UserRol;
import com.lt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
//public class LocalTattoApplication implements CommandLineRunner{
public class LocalTattoApplication{
    //@Autowired
    //private UsersService userService;

    public static void main(String[] args) {
        SpringApplication.run(LocalTattoApplication.class, args);
    }

    //@Override
    //public void run(String... args) throws Exception {
    /*   User usuario = new User();

       usuario.setUsername("Diego");
        usuario.setPassword("123Admin");
        usuario.setEmail("gomezmdiego97@gmail.com");
        usuario.setEnabled(true);

        Rol rol = new Rol();
        rol.setRolId(4L);
        rol.setNameRol("ADMIN");

        Set<UserRol> userRoles = new HashSet<>();
        UserRol userRol = new UserRol();
        userRol.setRol(rol);
        userRol.setUser(usuario);
        userRoles.add(userRol);

        User userGuardado = userService.guardarUsuario(usuario,userRoles);
        System.out.println(userGuardado.getUsername());
*/
      }

    //}