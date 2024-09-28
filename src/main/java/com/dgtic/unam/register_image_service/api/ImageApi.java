package com.dgtic.unam.register_image_service.api;

import java.util.List;

import javax.print.attribute.standard.Media;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;
import com.dgtic.unam.register_image_service.dto.RegisterImageRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestMapping(path="api/images", produces="application/json")
@CrossOrigin(origins="*")
@Tag(name="imagen", description="API del servicio de registro de imágenes")
public interface ImageApi {

    @Operation(summary = "Registrar imagen")
    /*@ApiResponses(value = { 
	    @ApiResponse(responseCode = "201", 
		    description = "Imagen registrada exitosamente", 
	        content = { 
                @Content(mediaType="application/json", 
	            schema = @Schema(implementation=RegisterImageRequest.class)),
                @Content(mediaType="multipart/form-data",
                schema = @Schema(implementation=MultipartFile.class))
            })
    })*/
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ImageMetadata> registrarImagen(@NotNull @Valid @RequestPart("metadata") RegisterImageRequest request, @RequestPart("file") MultipartFile file);
    //ResponseEntity<ImageMetadata> registrarImagen(@NotNull @Valid @ModelAttribute RegisterImageRequest request);

    
    @Operation(summary = "Obtener el registro de metadatos de todas las imágenes (con fines de test)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de imágenes", 
        content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ImageMetadata.class)) })
    })
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    List<ImageMetadata> obtenerImagenes();

    @Operation(summary = "Obtener registro de metadatos de una imagen por su ID (con fines de test)")
    @ApiResponse(responseCode = "200", description = "Imagen encontrada",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = ImageMetadata.class)) })
    @GetMapping("{id}")
    @Parameter(name = "id", description = "ID de la imagen a consultar", required = true)
    ResponseEntity<ImageMetadata> obtenerImagenPorId(@NotNull @PathVariable("id") String id);

}
