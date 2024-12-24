package com.ecommerce.ecommerce.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    void init() throws IOException;

    String store(MultipartFile file,String type, Long id);

    List<String> store(List<MultipartFile> files, String type, Long id);

    Resource loadAsResource(String type,String filename);
}
