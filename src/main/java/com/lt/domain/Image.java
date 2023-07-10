package com.lt.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "imagenes")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "imagename")
    private String fileName;
    @Column(name = "imagepath")
    private String path;
    @Column(name = "imageurl")
    private String url;


}
