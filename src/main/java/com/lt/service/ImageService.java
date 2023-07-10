package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.domain.Image;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImageService {

    public List<Image> getImages();

    public ResponseEntity<RespuestaHttp> saveImage(Image imagen);

    public ResponseEntity<RespuestaHttp> deleteImage(Image imagen);

    public Image getImageById(Image imagen);

}