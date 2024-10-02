package com.dgtic.unam.register_image_service.domain;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private String timestamp;
}
