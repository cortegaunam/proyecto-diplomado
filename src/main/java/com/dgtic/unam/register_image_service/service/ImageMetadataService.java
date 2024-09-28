package com.dgtic.unam.register_image_service.service;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import java.util.List;

public interface ImageMetadataService {
    ImageMetadata registrarImagen(ImageMetadata imagen);
    ImageMetadata obtenerImagenPorId(String id);
    List<ImageMetadata> obtenerImagenes();
}
