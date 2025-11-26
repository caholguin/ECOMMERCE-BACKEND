package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.FileUploadDTO;
import com.ecommerce.ecommerce.service.StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final StorageService storageService;

    public MediaController(StorageService storageService){
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, List<String>>> upload(FileUploadDTO dto) throws Exception {

        List<String> urls = storageService.uploadFiles(dto.getFiles(), dto.getBucket(), dto.getId());

        return ResponseEntity.ok(Map.of("urls", urls));
    }
}








































