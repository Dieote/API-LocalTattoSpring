package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.dao.CitaDAO;
import com.lt.domain.Cita;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaDAO citaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> getCita() {
        List<Cita> lista = new ArrayList<>();
        citaDao.findAll().forEach(lista::add);
        return lista;
    }
    @Override
    @Transactional(readOnly = true)
    public Cita getCitaById(Cita citado) {
        return citaDao.findById(citado.getId()).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> saveCita(Cita citado) {
            RespuestaHttp respuesta = new RespuestaHttp();
            citaDao.save(citado);
            respuesta.setMessage("Cita programada con exito.");
            respuesta.setStatus("OK");
            return ResponseEntity.ok(respuesta);
            }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> deleteCita(Cita citado) {
        RespuestaHttp respuesta = new RespuestaHttp();
        citaDao.delete(citado);
        respuesta.setMessage("Cita Eliminada");
        respuesta.setStatus("Ok");
        return ResponseEntity.ok(respuesta);
    }

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> updateCita(Cita citado) {
                RespuestaHttp respuesta = new RespuestaHttp();

        try {
            if (citaDao.findById(citado.getId()) == null) {
                throw new IllegalAccessError("No existe esta Cita");
            }
            citaDao.save(citado);
            respuesta.setMessage("Cita Actualizada");
            respuesta.setStatus("Ok");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.ok(respuesta);

        }
    }
}
