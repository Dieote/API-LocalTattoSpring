package com.lt.dao;

import com.lt.domain.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageDAO extends CrudRepository<Image,Long>{
    
}
