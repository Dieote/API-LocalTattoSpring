package com.lt.domain;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data; //genera nuestro codigo set-get-hash-toString

@Data
@Entity
@Table(name = "artist")
public class Artista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "phone")
    private String phone;
    @Column(name = "available")
    private String available;

}
