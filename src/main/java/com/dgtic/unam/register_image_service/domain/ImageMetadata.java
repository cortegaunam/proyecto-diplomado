package com.dgtic.unam.register_image_service.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection="images")
public class ImageMetadata {
    
    @Schema(accessMode = AccessMode.READ_ONLY)
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    
    @Schema(accessMode = AccessMode.READ_ONLY)
	private String originalName;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String format;

    @NotBlank(message="El método de compresión de la imagen es obligatorio")
    private String compressionMethod;

    private String quality;

    @NotBlank(message="El tamaño del 'Tile' es obligatorio")
    private String tileSize;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String nameInBucket;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String createdAt;
}
