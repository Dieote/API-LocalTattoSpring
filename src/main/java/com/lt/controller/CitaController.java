package com.lt.controller;

import com.lt.RespuestaHttp;
import com.lt.domain.Artista;
import com.lt.domain.Cita;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.lt.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class CitaController {
 
    @Autowired
    private CitaService citaService;

    @GetMapping("/get-list-citas")
    @ResponseBody
    public List<Cita> getCita() {
        return citaService.getCita();
    } 

    @GetMapping("/get-cita")
    @ResponseBody
    public String getCitaById(@PathVariable("id") Long id) {
        return "";
    }

    @PostMapping("/post-cita")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> postCita(@RequestBody Cita citado) {
        return citaService.saveCita(citado);
    }

    @DeleteMapping("/delete-cita/{id}")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> deleteCita(@PathVariable("id") Long id) {
        Cita citado = new Cita();
        citado.setId(id);
        log.info("Eliminado cita con id " + id);
        return citaService.deleteCita(citado);
    }

    @PutMapping("/update-cita")
    @ResponseBody
    public ResponseEntity<RespuestaHttp> updateCita(@RequestBody Cita citado) {
        return citaService.updateCita(citado);
    }

}
