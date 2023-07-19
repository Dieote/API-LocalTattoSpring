package com.lt.dao;

import com.lt.domain.Artista;
import com.lt.domain.ImageArtist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageArtistDAO extends JpaRepository<ImageArtist, Long> {

    List<ImageArtist> findAllByArtista(Artista artista);
}