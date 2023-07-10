package com.lt.controller;

import com.lt.dao.ImageDAO;
import com.lt.domain.Image;
import com.lt.service.ImageService;
import com.lt.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class StorageController {

    private final StorageService storageService;
    private final HttpServletRequest request;

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public Map<String, String> uploadFile(@RequestParam("file")MultipartFile multipartFile){
        Map<String, String> map = new HashMap<>();
        String path = storageService.store(multipartFile);
        String host = request.getRequestURL().toString().replace(request.getRequestURI(),"");
        String url = ServletUriComponentsBuilder
                .fromHttpUrl(host)
                .path("/media/")
                .path(path)
                .toUriString();
        map.put("url", url);

        Image image = new Image();
        image.setFileName(multipartFile.getOriginalFilename());
        image.setPath(path);
        image.setUrl(url);
        imageService.saveImage(image);

        return map;
    }

    @GetMapping("/{fileName:.*}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) throws IOException {
        Resource file = storageService.loadAsResource(fileName);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(file);
    }

    @GetMapping("/images")
    public List<Image> getImages() {
        return imageService.getImages();
    }

}
