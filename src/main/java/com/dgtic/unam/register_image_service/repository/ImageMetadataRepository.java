package com.dgtic.unam.register_image_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dgtic.unam.register_image_service.domain.ImageMetadata;

public interface ImageMetadataRepository extends MongoRepository<ImageMetadata, String> {
        
}
