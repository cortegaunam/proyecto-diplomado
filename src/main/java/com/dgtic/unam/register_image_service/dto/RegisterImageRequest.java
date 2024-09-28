package com.dgtic.unam.register_image_service.dto;

import org.springframework.web.multipart.MultipartFile;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterImageRequest {

    @NotNull(message = "Los metadatos de la imagen son obligatorios")
    @Valid
    private ImageMetadata imageMetadata;

    /*@NotNull(message = "La imagen es obligatoria")
    @Valid
    private MultipartFile file;*/
}