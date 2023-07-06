package com.lt.service;

import com.lt.domain.User;
import com.lt.domain.UserRol;
import java.util.*;

public interface UsersService {

  public User guardarUsuario(User usuario, Set<UserRol> usuarioRoles) throws Exception;

  public User obtenerUsuario(String username);

  public void eliminarUsuario(Long id);
} 
