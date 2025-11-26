package com.ecommerce.ecommerce.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    String uploadFile(MultipartFile file, String bucket, Long id) throws IOException;

    List<String> uploadFiles(List<MultipartFile> files, String bucket, Long id) throws IOException;
}
