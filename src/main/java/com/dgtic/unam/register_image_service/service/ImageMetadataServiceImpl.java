package com.dgtic.unam.register_image_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import com.dgtic.unam.register_image_service.repository.ImageMetadataRepository;

@Service
public class ImageMetadataServiceImpl implements ImageMetadataService {

    @Autowired
    private ImageMetadataRepository imageMetadataRepository;
   
    @Override
    public ImageMetadata registrarImagen(ImageMetadata imagen) {
        imageMetadataRepository.save(imagen);
        return imagen;
    }

    @Override
    public ImageMetadata obtenerImagenPorId(String id) {
        return imageMetadataRepository.findById(id).orElse(null);
    }

    @Override
    public List<ImageMetadata> obtenerImagenes() {
        return imageMetadataRepository.findAll();
    }

}
