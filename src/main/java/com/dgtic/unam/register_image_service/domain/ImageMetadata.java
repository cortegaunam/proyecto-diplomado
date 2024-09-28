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
    
    @NotBlank(message="El nombre de la imagen es obligatorio")
	private String nombre;

    @NotBlank(message="El fomato de la imagen es obligatorio")
    private String formato;

    @NotBlank(message="El método de compresión de la imagen es obligatorio")
    private String metodoCompresion;

    @NotBlank(message="El tamaño del 'Tile' es obligatorio")
    private String tamanioTile;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String nameInBucket;

    @Schema(accessMode = AccessMode.READ_ONLY)
    private String createdAt;
}
