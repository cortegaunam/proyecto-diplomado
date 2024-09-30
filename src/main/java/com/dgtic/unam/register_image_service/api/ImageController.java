package com.dgtic.unam.register_image_service.api;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import com.dgtic.unam.register_image_service.service.ImageMetadataService;
import com.dgtic.unam.register_image_service.service.UploadImageService;
import com.dgtic.unam.register_image_service.service.ValidationService;
import com.google.gson.Gson;


@RestController
public class ImageController implements ImageApi {

    @Autowired
    private ImageMetadataService imageMetadataService;

    @Autowired
    private UploadImageService uploadImageService;

    @Autowired
    private ValidationService validationService;

    private Object responseError = new Object();

    @Override
    public ResponseEntity<Object> registrarImagen(String requestBodyAsJson, MultipartFile file) {
        String contentType = file.getContentType();
        if (!contentType.startsWith("image/")) {
            return new ResponseEntity<>(null, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }
        Gson gson = new Gson();
        ImageMetadata request = gson.fromJson(requestBodyAsJson, ImageMetadata.class);
        try {
            validationService.validateImageMetadata(request);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            responseError.message = e.getMessage();
            return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
        }
        String objectName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        request.setNameInBucket(objectName);
        request.setCreatedAt(LocalDateTime.now().toString());
        try {
            InputStream inputStream = file.getInputStream();
            uploadImageService.uploadImage(objectName, inputStream, file.getContentType());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //imageMetadataService.registrarImagen(request.getImageMetadata());
        imageMetadataService.registrarImagen(request);
        //return new ResponseEntity<>(request.getImageMetadata(), HttpStatus.CREATED);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    @Override
    public List<ImageMetadata> obtenerImagenes() {
        return imageMetadataService.obtenerImagenes();
    }

    @Override
    public ResponseEntity<ImageMetadata> obtenerImagenPorId(String id) {
        if (ObjectId.isValid(id)) {
            Optional<ImageMetadata> imageMetadata = Optional.ofNullable(imageMetadataService.obtenerImagenPorId(id));
            if (imageMetadata.isPresent()) {
                return new ResponseEntity<>(imageMetadata.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
