package com.lt.service;

import com.lt.RespuestaHttp;
import com.lt.dao.ArtistaDAO;
import com.lt.dao.ImageDAO;
import com.lt.domain.Artista;

import java.util.*;

import com.lt.domain.Image;
import com.lt.domain.ImageArtist;
import com.lt.helpers.FileNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    @Autowired
    private ArtistaDAO artistaDao;
    @Autowired
    private ImageDAO imageDao;

    @Autowired
    private FileNameHelper fileNameHelper;

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
              throw new IllegalArgumentException("El nombre del artista no puede ser nulo");
            }
            Artista savedArtista = artistaDao.save(tatuador);

            respuesta.setMessage(savedArtista.getName());
            respuesta.setStatus("OK");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.setMessage("Error al guardar el artista: " + e.getMessage());
            respuesta.setStatus("ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

    /*@Override
    @Transactional
    public ResponseEntity<RespuestaHttp> save(Artista tatuador){
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
*/
    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> uploadImage(Long idArtista, MultipartFile file) throws Exception {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Optional<Artista> artistaOptional = artistaDao.findById(idArtista);
            if (artistaOptional.isPresent()) {
                Artista artista = artistaOptional.get();

                Image image = Image.buildImage(file, fileNameHelper);
                Image savedImage = imageDao.save(image);// Guarda la instancia de Image en la BD antes de relacionarla conartista

                ImageArtist imageArtist = new ImageArtist();
                imageArtist.setArtista(artista);

                imageArtist.setImage(savedImage);//Asigan la instancia Image a ImageArtist

                artista.getArtistImage().add(imageArtist); // Relaciona la imagen con el artista en la tabla imageArtist
                artistaDao.save(artista); // Guarda la relacin en la base de datos

                respuesta.setMessage("Imágenes del artista agregadas correctamente");
                respuesta.setStatus("OK");
                return ResponseEntity.ok(respuesta);
            } else {
                throw new IllegalArgumentException("Artista no encontrado con ID: " + idArtista);
            }
        } catch (Exception e) {
            throw new Exception("Error al agregar imágenes del artista: " + e.getMessage());
        }
    }

  /*  @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> uploadImage(Long idArtista, MultipartFile file) throws Exception {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Optional<Artista> artistaOptional = artistaDao.findById(idArtista);
        ImageArtist imageArtist = new ImageArtist();
        imageArtist.setArtista(artistaOptional.get());
        imageArtist.setImage(Image.buildImage(file, fileNameHelper));

            if (artistaOptional.isPresent()) {
                Artista artista = artistaOptional.get();
                Set<ImageArtist> setImageArtist = new HashSet<>();
                setImageArtist.add(imageArtist);
                for (ImageArtist setImageArtistFor : setImageArtist) {
                    imageDao.save(setImageArtistFor.getImage());
                    artistaDao.save(setImageArtistFor.getArtista());
                }

                respuesta.setMessage("Imágenes del artista agregadas correctamente");
                respuesta.setStatus("OK");
                return ResponseEntity.ok(respuesta);
            } else {
                throw new IllegalArgumentException("Artista no encontrado con ID: " + idArtista);
            }
        } catch (Exception e) {
            throw new Exception("Error al agregar imágenes del artista: " + e.getMessage());
        }
    }
*/
    /*@Override
    @Transactional
    public ResponseEntity<RespuestaHttp> uploadImage( Long idArtista, MultipartFile file) {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Optional<Artista> AAA = artistaDao.findById(idArtista);
            Image image = Image.buildImage(file, fileNameHelper);
           AAA.get();
            Artista.pushImage(image);
            Artista artistimag = AAA.get();
            artistimag.pushImage(image);

            artistaDao.save(artistimag);
            respuesta.setMessage(AAA.get().getName());
            respuesta.setStatus("OK");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.ok(respuesta);
        }
    }
    */

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
    public ResponseEntity<RespuestaHttp> update(Artista artista) {
        RespuestaHttp respuesta = new RespuestaHttp();

        try {
            Optional<Artista> artistOptional = artistaDao.findById(artista.getId());
            if (!artistOptional.isPresent()) {
                throw new IllegalAccessError("No existe este artista");
            }
            artistaDao.save(artista);
            respuesta.setMessage("Artista Actualizado");
            respuesta.setStatus("Ok");
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.ok(respuesta);
        }
    }
}
