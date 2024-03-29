package com.lt.controller;

import com.lt.RespuestaHttp;
import com.lt.domain.Artista;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import com.lt.service.ArtistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class ArtistaController {
 
    @Autowired
    private ArtistaService artistaService;

    @GetMapping("/get-list-artists")
    @ResponseBody
    public List<Artista> getList() {
        return artistaService.getArtists();
    }

    @GetMapping("/get-artist/{id}")
    @ResponseBody
    public String getArtistById(@PathVariable("id") Long id) {
        return "";
    }

    @PostMapping("/post-artist")
    public ResponseEntity<RespuestaHttp> postArtist(@RequestBody Artista tatuador) throws Exception {

        return artistaService.save(tatuador);
    }

    @PostMapping("/post-image-artist/{artistaId}")
    public ResponseEntity<RespuestaHttp> postImageArtist(@PathVariable("artistaId") Long artistaId, @RequestParam("file") MultipartFile file) throws Exception{
        Artista tatuador = new Artista();
        tatuador.setId(artistaId);
        artistaService.getArtistById(tatuador);
        return artistaService.uploadImage(artistaId, file);
    }
    @DeleteMapping("/delete-artist/{id}")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> deleteArtist(@PathVariable("id") Long id) {
        log.info("Eliminado artista con id " + id);
        return artistaService.delete(id);
    }

    @PutMapping("/update-artist/")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> updateArtist(@RequestBody Artista tatuador) throws Exception {
        return artistaService.update(tatuador);
    }
   /* @PutMapping("/update-image-artist/{artistaId}")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> updateImageArtist(@PathVariable("artistaId") Long artistaId, @RequestParam("file") MultipartFile file) throws Exception{
        Artista tatuador = new Artista();
        tatuador.setId(artistaId);
        artistaService.getArtistById(tatuador);
        return artistaService.updateImageArtist(artistaId, tatuador.getImageName(), file);
    } */
}
