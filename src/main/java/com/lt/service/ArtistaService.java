package com.lt.service;

import com.lt.domain.Artista;
import java.util.List;

public interface ArtistaService {

    public List<Artista> getArtists();

    public String save(Artista tatuador);
        

    public String delete(Artista tatuador);

    public Artista getArtistById(Artista tatuador);
    
//    public String update(Artista tatuador);

}
