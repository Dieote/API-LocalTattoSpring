package com.lt.service;

import com.lt.ImageResponse;
import com.lt.domain.Image;
import com.lt.domain.ImageArtist;

import java.util.List;
import java.util.Set;

public interface ImageService {


    public Image save(Image image);


    public Image findByFileName(String fileName);

    public Image findByUuid(String uuid);

    public List<ImageResponse> findAllImageResponse();

    public String deleteByUuid(String uuid);

}