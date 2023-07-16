package com.lt.controller;

import com.lt.ImageResponse;
import com.lt.domain.Image;
import com.lt.helpers.FileNameHelper;
import com.lt.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/media")
public class ImageController {

    @Autowired
    private ImageService imageService;

    private FileNameHelper fileHelper = new FileNameHelper();

    @GetMapping("/images")
    public ResponseEntity<List<ImageResponse>> getAllImageInfo() throws Exception {
        List<ImageResponse> imageResponses = imageService.findAllImageResponse();
        return ResponseEntity.ok().body(imageResponses);
    }

    @PostMapping("/upload")
    public ImageResponse uploadSingleFile(@RequestParam("file") MultipartFile file) {
        Image image = Image.buildImage(file, fileHelper);
        imageService.save(image);
        return new ImageResponse(image);
    }

    @PostMapping("/uploads")
    public List<ImageResponse> uploadMultiFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> uploadSingleFile(file)).collect(Collectors.toList());
    }

    @GetMapping("/show/{uuid}")
    public ResponseEntity<byte[]> getImage(@PathVariable String uuid) throws Exception {
        Image image = getImageByName(uuid);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
    }

    @GetMapping("/show")
    public ResponseEntity<byte[]> getImageWithRequestParam(@RequestParam(required = false, value = "uuid") String uuid,
                                                           @RequestParam(required = false, value = "name") String name) throws Exception {
        if (uuid != null) {
            Image image = getImageByUuid(uuid);
            return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
        }
        if (name != null) {
            return getImage(name);
        }
        Image defaultImage = Image.defaultImage();
        return ResponseEntity.ok().contentType(MediaType.valueOf(defaultImage.getFileType()))
                .body(defaultImage.getData());
    }

    @GetMapping("/show/{width}/{height}")
    public ResponseEntity<byte[]> getScaledImageWithRequestParam(@PathVariable int width, @PathVariable int height,
                                                                 @RequestParam(required = false, value = "uuid") String uuid,
                                                                 @RequestParam(required = false, value = "name") String name) throws Exception {
        if (uuid != null) {
            Image image = getImageByUuid(uuid, width, height);
            return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
        }
        if (name != null) {
            Image image = getImageByName(name, width, height);
            return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
        }
        Image defImage = Image.defaultImage(width, height);
        return ResponseEntity.ok().contentType(MediaType.valueOf(defImage.getFileType())).body(defImage.getData());
    }

    @GetMapping("/show/{width}/{height}/{fileName:.+}")
    public ResponseEntity<byte[]> getScaledImage(@PathVariable int width, @PathVariable int height,
                                                 @PathVariable String fileName) throws Exception {
        Image image = getImageByName(fileName, width, height);
        return ResponseEntity.ok().contentType(MediaType.valueOf(image.getFileType())).body(image.getData());
    }

    public Image getImageByName(String name) throws Exception {
        Image image = imageService.findByFileName(name);
        if (image == null) {
            return Image.defaultImage();
        }
        return image;
    }

    public Image getImageByName(String name, int width, int height) throws Exception {
        Image image = imageService.findByFileName(name);
        if (image == null) {
            Image defImage = Image.defaultImage();
            defImage.scale(width, height);
            return defImage;
        }
        image.scale(width, height);
        return image;
    }
    public Image getImageByUuid(String uuid) throws Exception {
        Image image = imageService.findByUuid(uuid);
        if (image == null) {
            return Image.defaultImage();
        }
        return image;
    }
    public Image getImageByUuid(String uuid, int width, int height) throws Exception {
        Image image = imageService.findByUuid(uuid);
        if (image == null) {
            Image defImage = Image.defaultImage();
            defImage.scale(width, height);
            return defImage;
        }
        image.scale(width, height);
        return image;
    }

    @DeleteMapping("/delete-image/{uuid}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> deleteByUuid(@PathVariable("uuid") String uuid) {
        log.info("Imagen eliminada: " + uuid);
        String deleteMessage = imageService.deleteByUuid(uuid);
        if (deleteMessage.equals("Imagen eliminada.")) {
            Map<String, String> response = new HashMap<>();
            response.put("message", deleteMessage);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", deleteMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }



}


