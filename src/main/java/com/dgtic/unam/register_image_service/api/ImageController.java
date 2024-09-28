package com.dgtic.unam.register_image_service.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import com.dgtic.unam.register_image_service.dto.RegisterImageRequest;
import com.dgtic.unam.register_image_service.service.ImageMetadataService;


@RestController
public class ImageController implements ImageApi {

    @Autowired
    private ImageMetadataService imageMetadataService;

    @Override
    public ResponseEntity<ImageMetadata> registrarImagen(RegisterImageRequest request) {
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
