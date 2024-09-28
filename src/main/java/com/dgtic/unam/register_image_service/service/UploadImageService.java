package com.dgtic.unam.register_image_service.service;

import java.io.InputStream;

public interface UploadImageService {
    public String uploadImage(String objectName, InputStream inputStream, String contentType) throws Exception;
}
