package com.lt.controller;

import com.lt.domain.Artista;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lt.service.ArtistaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/get-artist")
    @ResponseBody
    public String getArtistById(@PathVariable("id") Long id){
        return "";
    }

    @PostMapping("/post-artist")
    @ResponseBody
    public String postArtist(@RequestBody Artista tatuador) {
        return artistaService.save(tatuador);
    }
    
    @DeleteMapping("/delete-artist/{id}")
    @ResponseBody
    public String deleteArtist(@PathVariable("id") Long id ) {
        Artista tatuador = new Artista();
        tatuador.setId(id);
        log.info("Eliminado artista con id " + id);
         return artistaService.delete(tatuador);
    }
    
    @PutMapping("/update-artist")
    @ResponseBody
        public String updateArtist ( @RequestBody Artista tatuador){
        return artistaService.update(tatuador);
    }
    
}
