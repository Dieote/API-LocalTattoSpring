package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.domain.Artista;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ArtistaService {

    public List<Artista> getArtists();

    public ResponseEntity<RespuestaHttp> save(Artista tatuador) throws Exception  ;

    public ResponseEntity<RespuestaHttp> delete(Long idArtista);

    public Artista getArtistById(Artista tatuador);
    
    public ResponseEntity<RespuestaHttp> update(Artista tatuador);

    public ResponseEntity<RespuestaHttp> uploadImage( Long idArtista, MultipartFile file) throws Exception;


}
