package com.lt.service;

import com.lt.ImageResponse;
import com.lt.domain.Artista;
import com.lt.domain.Image;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageService {


    public Image save(Image image);


    public Image findByFileName(String fileName);

    public Image findByUuid(String uuid);

    public List<ImageResponse> findAllImageResponse();

    public String deleteByUuid(String uuid);

    public ResponseEntity<List<String>> getArtistFileName(Artista artista);
}