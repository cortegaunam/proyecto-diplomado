package com.dgtic.unam.register_image_service.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

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
    @CreatedDate
    private LocalDateTime createdAt;

    @Schema(accessMode = AccessMode.READ_ONLY)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
