package com.dgtic.unam.register_image_service.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;

@Service
public class UploadImageServiceImpl implements UploadImageService {

    private final MinioClient minioClient;
    private final String bucketName;

    public UploadImageServiceImpl(
        @Value("${minio.url}") String url,
        @Value("${minio.access-key}") String accessKey,
        @Value("${minio.secret-key}") String secretKey,
        @Value("${minio.bucket-name}") String bucketName
    ) {
        this.minioClient = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build(); 
        this.bucketName = bucketName;
    }

    @Override
    public String uploadImage(String objectName, InputStream inputStream, String contentType) throws Exception {
        try{
            minioClient.putObject(
                PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, inputStream.available(),-1)
                    .contentType(contentType)
                    .build()
            );
        } catch (Exception e) {
            throw new Exception("Error al subir la imagen al servidor", e);
        }
        return null;
    }

}
