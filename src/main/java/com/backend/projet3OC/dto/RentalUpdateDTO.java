package com.backend.projet3OC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RentalUpdateDTO {

    @NotBlank
    private String name;

    @NotNull
    @Positive
    private Double surface;

    @NotNull
    @Positive
    private Double price;

    @NotBlank
    private String description;

    private MultipartFile picture;
}
