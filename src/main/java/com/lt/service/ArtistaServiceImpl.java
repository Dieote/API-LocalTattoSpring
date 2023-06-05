package com.lt.service;

import com.lt.dao.ArtistaDAO;
import com.lt.domain.Artista;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaDAO artistaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Artista> getArtists() {
        List<Artista> lista = new ArrayList<>();
        artistaDao.findAll().forEach(lista::add);
        return lista;
    }

    @Override
    @Transactional
    public String save(Artista tatuador) {
        artistaDao.save(tatuador);
        return tatuador.getName();
    }

    @Override
    @Transactional
    public String delete(Artista tatuador) {
        artistaDao.delete(tatuador);
        return "Artista eliminado";
    }

    @Override
    @Transactional(readOnly = true)
    public Artista getArtistById(Artista tatuador) {
        return artistaDao.findById(tatuador.getId()).orElse(null);
    }

}
