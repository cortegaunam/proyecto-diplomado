package com.dgtic.unam.register_image_service.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
			version = "v1",
			title = "Servicio de carga de imágenes para conversión de formato", 
			description = "Este servicio permite la carga de imágenes para su conversión a formato TIFF piramidal para su uso el el proyecto de Microscopio Virtual (<a href='https://microscopio-redmacro.org/'>microscopio-redmacro.org</a>)", 
			contact = @Contact(
				name = "Cristian Ricardo Ortega Ramírez (Software Developer)", 
				url = "https://www.paginaspersonales.unam.mx/app/webroot/index.php/academicos/datosContacto/alias:cristianricardoortega", 
			email = "cristian.ortega@comunidad.unam.mx")))
public class OpenApiConfig {

}
