package com.lt.service;

import com.lt.ImageResponse;
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

}
