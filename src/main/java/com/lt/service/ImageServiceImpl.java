package com.lt.service;

import com.lt.ImageResponse;
import com.lt.dao.ArtistaDAO;
import com.lt.dao.ImageArtistDAO;
import com.lt.dao.ImageDAO;
import com.lt.domain.Artista;
import com.lt.domain.Image;
import com.lt.domain.ImageArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageDAO imageDao;
    @Autowired
    private ArtistaDAO artistaDao;
    @Autowired
    private ImageArtistDAO imageArtistDao;

    @Override
    public Image save(Image image) throws NullPointerException {
        if (image == null)
            throw new NullPointerException("Image Data NULL");
        return imageDao.save(image);
    }

    @Override
    public Image findByFileName(String fileName) {
        return this.imageDao.findByFileName(fileName);
    }

    @Override
    public Image findByUuid(String uuid) {
        return this.imageDao.findByUuid(uuid);
    }

    @Override
    public List<ImageResponse> findAllImageResponse() {
        return this.imageDao.findAllImageResponse();
    }

    @Override
    @Transactional
    public String deleteByUuid(String uuid) {
        Image image = imageDao.findByUuid(uuid);
        System.out.println("Uuid: " + uuid);
        if (image != null) {
            for(ImageArtist imageArtist : image.getArtistImage()){
                imageArtist.setImage(null);
            }
            imageDao.delete(image);
            return "Imagen eliminada.";
        } else {
            return "La imagen no existe.";
        }
    }

    @Override
    public ResponseEntity<List<String>> getArtistFileName( Artista artista) {
        List<ImageResponse> imageResponses = imageDao.findAllImageResponse();

       Set<String> imgUuids = artista.getArtistImage().stream()//obtiene uuid de imagenes asos
               .map(imageArtist -> imageArtist.getImage().getUuid())
               .collect(Collectors.toSet());

       List<ImageResponse> filterImage = imageResponses.stream() //filtra imagenes por uuids asos
               .filter(imageResponse -> imgUuids.contains(imageResponse.getUuid()))
               .collect(Collectors.toList());

       List<String> imgNames = filterImage.stream()//trae nombres de imagenes
               .map(ImageResponse::getFileName)
               .collect(Collectors.toList());

       return ResponseEntity.ok().body(imgNames);
    }

}
