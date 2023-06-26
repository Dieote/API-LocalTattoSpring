package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.domain.Artista;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ArtistaService {

    public List<Artista> getArtists();

    public ResponseEntity<RespuestaHttp> save(Artista tatuador);

    public ResponseEntity<RespuestaHttp> delete(Artista tatuador);

    public Artista getArtistById(Artista tatuador);
    
    public ResponseEntity<RespuestaHttp> update(Artista tatuador);

}
