package com.lt.service;

import com.lt.ImageResponse;
import com.lt.dao.ImageDAO;
import com.lt.domain.Image;
import com.lt.domain.ImageArtist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageDAO imageDao;

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
}
