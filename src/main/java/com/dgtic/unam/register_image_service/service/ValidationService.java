package com.dgtic.unam.register_image_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.dgtic.unam.register_image_service.domain.ImageMetadata;

@Service
public class ValidationService {

    @Autowired
    private Validator validator;

    public void validateImageMetadata(ImageMetadata imageMetadata) {
        BindingResult result = new BeanPropertyBindingResult(imageMetadata, "imageMetadata");
        validator.validate(imageMetadata, result);

        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().get(0).getDefaultMessage());
        }
    }

}
