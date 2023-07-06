package com.lt.domain;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Rol {
    
    @Id
    private Long rolId;
    private String nameRol;
    
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "rol")
    private Set<UserRol> usuarioRoles = new HashSet<>();

    public Rol(){
    }

    public Rol(Long rolId, String nameRol){
        this.rolId = rolId;
        this.nameRol = nameRol;
    }
    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public Set<UserRol> getUsuarioRoles() {
        return usuarioRoles;
    }

    public void setUsuarioRoles(Set<UserRol> usuarioRoles) {
        this.usuarioRoles = usuarioRoles;
    }
}
