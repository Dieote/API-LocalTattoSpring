package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.domain.Cita;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface CitaService {

    public List<Cita> getCita();

    public ResponseEntity<RespuestaHttp> saveCita(Cita citado);

    public ResponseEntity<RespuestaHttp> deleteCita(Cita citado);

    public Cita getCitaById(Cita citado);
    
    public ResponseEntity<RespuestaHttp> updateCita(Cita citado);

}
