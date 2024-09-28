package com.dgtic.unam.register_image_service.api;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import com.dgtic.unam.register_image_service.dto.RegisterImageRequest;
import com.dgtic.unam.register_image_service.service.ImageMetadataService;
import com.dgtic.unam.register_image_service.service.UploadImageService;


@RestController
public class ImageController implements ImageApi {

    @Autowired
    private ImageMetadataService imageMetadataService;

    @Autowired
    private UploadImageService uploadImageService;

    @Override
    public ResponseEntity<ImageMetadata> registrarImagen(RegisterImageRequest request, MultipartFile file) {
    //public ResponseEntity<ImageMetadata> registrarImagen(RegisterImageRequest request) {

        try {
            InputStream inputStream = file.getInputStream();
            //InputStream inputStream = request.getFile().getInputStream();
            String objectName = request.getImageMetadata().getNombre();
            uploadImageService.uploadImage(objectName, inputStream, file.getContentType());
            //uploadImageService.uploadImage(objectName, inputStream, request.getFile().getContentType());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageMetadataService.registrarImagen(request.getImageMetadata());
        return new ResponseEntity<>(request.getImageMetadata(), HttpStatus.CREATED);
    }

    @Override
    public List<ImageMetadata> obtenerImagenes() {
        return imageMetadataService.obtenerImagenes();
    }

    @Override
    public ResponseEntity<ImageMetadata> obtenerImagenPorId(String id) {
        Optional<ImageMetadata> imageMetadata = Optional.ofNullable(imageMetadataService.obtenerImagenPorId(id));
        if (imageMetadata.isPresent()) {
            return new ResponseEntity<>(imageMetadata.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
