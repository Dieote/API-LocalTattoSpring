package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.dao.ImageDAO;
import com.lt.domain.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageDAO imageDao;

    @Override
    @Transactional(readOnly = true)
    public List<Image> getImages() {
        List<Image> lista = new ArrayList<>();
        imageDao.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> saveImage(Image imagen) {
        RespuestaHttp respuesta = new RespuestaHttp();
        imageDao.save(imagen);
        respuesta.setMessage("Imagen cargada con exito.");
        respuesta.setStatus("Ok");
        return ResponseEntity.ok(respuesta);
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> deleteImage(Image imagen) {
        RespuestaHttp respuesta = new RespuestaHttp();
        imageDao.delete(imagen);
        respuesta.setMessage("Imagen eliminada con exito.");
        respuesta.setStatus("Ok");
        return ResponseEntity.ok(respuesta);
    }

    @Override
    public Image getImageById(Image imagen) {
        return imageDao.findById(imagen.getId()).orElse(null);
    }
}
