package com.lt.controller;

import com.lt.RespuestaHttp;
import com.lt.domain.Artista;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.lt.service.ArtistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ArtistaController {
 
    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/get-list-artists")
    @ResponseBody
    public List<Artista> getList() {
        return artistaService.getArtists();
    } //el respuestaHttp debe tener un List

    @GetMapping("/get-artist")
    @ResponseBody
    public String getArtistById(@PathVariable("id") Long id) {
        return "";
    }

    @PostMapping("/post-artist")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> postArtist(@RequestBody Artista tatuador) {
        return artistaService.save(tatuador);
    }

    @DeleteMapping("/delete-artist/{id}")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> deleteArtist(@PathVariable("id") Long id) {
        Artista tatuador = new Artista();
        tatuador.setId(id);
        log.info("Eliminado artista con id " + id);
        return artistaService.delete(tatuador);
    }

    @PutMapping("/update-artist")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> updateArtist(@RequestBody Artista tatuador) {
        return artistaService.update(tatuador);
    }

}
