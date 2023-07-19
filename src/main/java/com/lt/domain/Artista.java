package com.lt.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data; //genera nuestro codigo set-get-hash-toString
import org.springframework.web.multipart.MultipartFile;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "artista")
    @JsonIgnore
    private Set<ImageArtist> artistImage = new HashSet<>();

//    hacer arraylist de dias 7
}
