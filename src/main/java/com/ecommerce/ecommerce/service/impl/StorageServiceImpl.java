package com.ecommerce.ecommerce.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ecommerce.ecommerce.service.ImageVariantService;
import com.ecommerce.ecommerce.service.StorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    private final AmazonS3 s3;
    private final ImageVariantService imageVariantService;

    public StorageServiceImpl(AmazonS3 s3, ImageVariantService imageVariantService){
        this.s3 = s3;
        this.imageVariantService = imageVariantService;
    }

    @PostConstruct
    public void createBuckets(){
        //TODO aca se crean los buckets
        List<String> buckets = List.of("products", "subcategories");

        for (String bucket : buckets) {
            if (!s3.doesBucketExistV2(bucket)) {
                s3.createBucket(bucket);
            }
        }
    }

    public String uploadFile(MultipartFile file, String bucket, Long id) throws IOException {

        String key = UUID.randomUUID() + "-" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3.putObject(new PutObjectRequest(bucket, key, file.getInputStream(), metadata));

        String url = s3.getUrl(bucket, key).toString();

        handlePersistence(bucket, id, url);

        return url;
    }

    public List<String> uploadFiles(List<MultipartFile> files, String bucket, Long id) throws IOException {
        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            urls.add(uploadFile(file, bucket, id));
        }

        return urls;
    }

    private void handlePersistence(String bucket, Long id, String url){
        switch (bucket) {
            case "products" -> imageVariantService.save(id, url);
            //TODO queda pendiente crear los casos para los demás buckets de las otras imágenes
            //case "subcategories" -> subcategoryService.addImageToSubcategory(id, url);
            default -> throw new IllegalArgumentException("Tipo no soportado: " + bucket);
        }
    }
}
