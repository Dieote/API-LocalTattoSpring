package com.lt.service;

import com.lt.ImageResponse;
import com.lt.RespuestaHttp;
import com.lt.dao.ArtistaDAO;
import com.lt.dao.ImageArtistDAO;
import com.lt.dao.ImageDAO;
import com.lt.domain.Artista;

import java.util.*;
import java.util.stream.Collectors;

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
    private ImageArtistDAO imageArtistDao;
    @Autowired
    private FileNameHelper fileNameHelper;
    @Autowired
    private ImageService imageService;


    @Override
    @Transactional(readOnly = true)
    public List<Artista> getArtists()  {
        List<Artista> lista = new ArrayList<>();

        artistaDao.findAll().forEach(artista -> {
          List<String> listNamesImg = imageService.getArtistFileName(artista);

                if (listNamesImg != null && !listNamesImg.isEmpty()){
                    artista.setImageName(listNamesImg.get(0));
                }
            List<String> listUuidsImg = imageService.getArtistFileUuid(artista);

            if (listUuidsImg != null && !listUuidsImg.isEmpty()){
                artista.setImageUuid(listUuidsImg.get(0));
            }
            lista.add(artista);
        });
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
            respuesta.setId(savedArtista.getId());
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            respuesta.setMessage("Error al guardar el artista: " + e.getMessage());
            respuesta.setStatus("ERROR");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
    }

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

    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> delete(Long idArtista) {
        RespuestaHttp respuesta = new RespuestaHttp();
        //Eliminamos relacion image_artist
        Artista tatuador = artistaDao.findById(idArtista).orElse(null);
        if(tatuador == null){
            respuesta.setMessage("El artista id " + idArtista + " no existe.");
            respuesta.setStatus("Error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
    Set<ImageArtist> artistImages = tatuador.getArtistImage();

    Set<Image> imagesDel = new HashSet<>();
    for(ImageArtist imageArtist : artistImages){
        Image image = imageArtist.getImage();
        if (image != null){
            imagesDel.add(image);
            image.getArtistImage().clear();
        }
        for (Image imagen : imagesDel){
            imageDao.delete(imagen);
        }
    }
            artistaDao.delete(tatuador);
            respuesta.setMessage("Eliminado");
            respuesta.setStatus("Ok");
            return ResponseEntity.ok(respuesta);
        }
    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> update(Artista tatuador) throws Exception {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Optional<Artista> artistOptional = artistaDao.findById(tatuador.getId());
            if (!artistOptional.isPresent()) {
                throw new IllegalAccessError("No existe este artista");
            }
            artistaDao.save(tatuador);
            respuesta.setMessage("Artista Actualizado correctamente.");
            respuesta.setStatus("OK");
        } catch (Exception e) {
            respuesta.setMessage("Error al actualizar artista.");
            respuesta.setStatus("ERROR");
             throw new Exception("Error al agregar imágenes del artista: " + e.getMessage());
        }
        return ResponseEntity.ok().body(respuesta);
    }

/*
    @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> updateImageArtist(Long idArtista, String fileName, MultipartFile file) throws Exception {
        RespuestaHttp respuesta = new RespuestaHttp();
            try {
                Optional<Artista> artistaOptional = artistaDao.findById(idArtista);
                if (artistaOptional.isPresent()) {
                Artista tatuador = artistaOptional.get();

                 //imageArtistDao.findall();
                //filtrar el listado buscando el id del artista
                Image imageBD = imageService.findByFileName(artista.getImageName());
                Image image = Image.buildImage(file, fileNameHelper);
                image.setId(imageBD.getId());
                Image savedImage = imageDao.save(image);// Guarda la instancia de Image en la BD antes de relacionarla conartista

                //ImageArtist imageArtist = new ImageArtist();
                //imageArtist.setArtista(tatuador);
                //imageArtist.setImage(savedImage);//Asigan la instancia Image a ImageArtist

                // tatuador.getArtistImage().add(imageArtist); // Relaciona la imagen con el artista en la tabla imageArtist
                //artistaDao.save(tatuador); // Guarda la relacin en la base de datos

                respuesta.setMessage("Imagen del artista actualizada correctamente.");
                respuesta.setStatus("OK");
                } else {
                throw new IllegalArgumentException("Artista no encontrado con ID: " + idArtista);
                }
                } catch (Exception e) {
                respuesta.setMessage("Error al agregar imágenes del artista: " + e.getMessage());
                respuesta.setStatus("ERROR");
                throw new Exception("Error al agregar imágenes del artista: " + e.getMessage());
                }
                return ResponseEntity.ok().body(respuesta);
 }
*/
   @Override
    @Transactional
    public ResponseEntity<RespuestaHttp> updateImageArtist(Long idArtista, String fileName, MultipartFile file) throws Exception {
        RespuestaHttp respuesta = new RespuestaHttp();
        try {
            Optional<Artista> artistaOptional = artistaDao.findById(idArtista);
            if (artistaOptional.isPresent()) {
                Artista tatuador = artistaOptional.get();
    //
                Image imageBD = imageService.findByFileName(tatuador.getImageName());
                if(imageBD != null){
                    imageDao.delete(imageBD);
                }

                Image image = Image.buildImage(file, fileNameHelper);
                Image savedImage = imageDao.save(image);

                tatuador.setImageName(savedImage.getFileName());
                artistaDao.save(tatuador);

                respuesta.setMessage("Imagen del artista actualizada correctamente.");
                respuesta.setStatus("OK");
            } else {
                throw new IllegalArgumentException("Artista no encontrado con ID: " + idArtista);
            }
        } catch (Exception e) {
            respuesta.setMessage("Error al agregar imágenes del artista: " + e.getMessage());
            respuesta.setStatus("ERROR");
            throw new Exception("Error al agregar imágenes del artista: " + e.getMessage());
        }
        return ResponseEntity.ok().body(respuesta);
    }


}
