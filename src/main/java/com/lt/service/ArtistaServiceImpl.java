package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.dao.ArtistaDAO;
import com.lt.domain.Artista;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Transactional(readOnly = true)
    public Artista getArtistById(Artista tatuador) {
        return artistaDao.findById(tatuador.getId()).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> save(Artista tatuador) {
                    RespuestaHttp respuesta = new RespuestaHttp();
        try {
            if (tatuador.getName() == null) {
                throw new IllegalAccessException("El nombre no puede ser nulo");
            }
            artistaDao.save(tatuador);
            respuesta.setMessage(tatuador.getName());
            respuesta.setStatus("OK");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.ok(respuesta);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> delete(Artista tatuador) {
        RespuestaHttp respuesta = new RespuestaHttp();
        artistaDao.delete(tatuador);
        respuesta.setMessage("Eliminado");
        respuesta.setStatus("Ok");
        return ResponseEntity.ok(respuesta);
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> update(Artista tatuador) {
                RespuestaHttp respuesta = new RespuestaHttp();

        try {
            if (artistaDao.findById(tatuador.getId()) == null) {
                throw new IllegalAccessError("No existe este artista");
            }
            artistaDao.save(tatuador);
            respuesta.setMessage("Artista Actualizado");
            respuesta.setStatus("Ok");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.ok(respuesta);

        }
    }
}
