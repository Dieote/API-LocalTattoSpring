package com.lt.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class ImageArtist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageArtistId;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Artista artista;

    @ManyToOne
    private Image image;

    public Long getImageArtistId() {
        return imageArtistId;
    }

    public void setImageArtistId(Long imageArtistId) {
        this.imageArtistId = imageArtistId;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    }

