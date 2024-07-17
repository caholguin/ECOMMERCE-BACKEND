package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FileUploadDTO;
import com.ecommerce.ecommerce.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public Map<String, String> uploadFile(@ModelAttribute FileUploadDTO fileUploadDTO) {

        String url = storageService.store(fileUploadDTO.getFile(), fileUploadDTO.getType(), fileUploadDTO.getId());

        return Map.of("url", url);
    }

    @GetMapping("/{type}/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String type, @PathVariable String filename) throws IOException{
        Resource file = storageService.loadAsResource(type,filename);
        String contentType = Files.probeContentType(file.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(file);
    }
}








































