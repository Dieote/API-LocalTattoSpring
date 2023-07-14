package com.lt.dao;

import com.lt.ImageResponse;
import com.lt.domain.Image;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageDAO extends CrudRepository<Image,Long>{


    Image findByFileName(String fileName);

    Image findByUuid(String uuid);

    @Query(value = "select new com.lt.ImageResponse(im.uuid, im.fileName, im.fileType, im.size) from com.lt.domain.Image im where im.status=true", nativeQuery = false)
    List<ImageResponse> findAllImageResponse();

}
