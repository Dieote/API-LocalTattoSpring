package com.lt.dao;

import com.lt.domain.Artista;
import org.springframework.data.repository.CrudRepository;

public interface ArtistaDAO extends CrudRepository<Artista,Long> {
    
}

