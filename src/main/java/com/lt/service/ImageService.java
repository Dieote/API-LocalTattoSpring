package com.lt.service;

import com.lt.ImageResponse;
import com.lt.domain.Image;

import java.util.List;

public interface ImageService {


    public Image save(Image image);

    public Image findByFileName(String fileName);

    public Image findByUuid(String uuid);

    public List<ImageResponse> findAllImageResponse();


}