package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.StorageService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ProductService productService;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.locationProduct}")
    private String mediaLocationProduct;



    public String getBaseUrl() {
        String requestUrl = request.getRequestURL().toString();
        String requestUri = request.getRequestURI();
        return requestUrl.replace(requestUri, "");
    }

    private Path generalPath;
    private Path productPath;

    @Override
    @PostConstruct
    public void init() throws IOException{
        generalPath = Paths.get(mediaLocation);
        productPath = Paths.get(mediaLocationProduct);

        Files.createDirectories(generalPath);
        Files.createDirectories(productPath);
    }


   @Override
   public String store(MultipartFile file, String type, Long id){
       try {
           if (file.isEmpty()) {
               throw new RuntimeException("El archivo está vacío");
           }

           String fileName = file.getOriginalFilename().replace(" ", "-").toLowerCase();
           Path destinationFile;
           String basePath;

           if ("products".equalsIgnoreCase(type)) {
               basePath = "products";
               destinationFile = productPath.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
           } else {
               basePath = "general";
               destinationFile = generalPath.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
           }

           try (InputStream inputStream = file.getInputStream()) {
               Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
           }

           return getUrlAndSave(basePath, fileName,id, type);

       } catch (IOException e) {
           throw new RuntimeException("Error al cargar el archivo", e);
       }
   }

    private String getUrlAndSave(String basePath, String fileName,Long id,String type){
        String resultPath = basePath + "/" + fileName;

        String host = this.getBaseUrl();
        String url = host + contextPath + "/media/" + resultPath;

        productService.addMedia(id,url);

        return url;
    }

    @Override
    public Resource loadAsResource(String type,String filename){
        try {

            Path file;

            if (type.equalsIgnoreCase("products")) {
                file = productPath.resolve(filename);
            }else{
                file = generalPath.resolve(filename);
            }

            Resource resource = new UrlResource((file.toUri()));

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Error al leer el archivo: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error al leer el archivo " + filename, e);
        }
    }
}
