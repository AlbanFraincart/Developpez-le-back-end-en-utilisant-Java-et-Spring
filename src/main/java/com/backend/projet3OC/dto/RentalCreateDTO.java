package com.backend.projet3OC.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Schema(description = "Données pour la création d'une location")
public class RentalCreateDTO {

    @NotBlank
    @Schema(description = "Nom de la location", example = "Appartement T2")
    private String name;

    @NotNull
    @Positive
    @Schema(description = "Surface en mètres carrés", example = "50.0")
    private Double surface;

    @NotNull
    @Positive
    @Schema(description = "Prix en euros", example = "750.0")
    private Double price;

    @NotBlank
    @Schema(description = "Description détaillée", example = "Bel appartement au centre-ville")
    private String description;

    @NotNull
    @Schema(description = "Image de la location")
    private MultipartFile picture;
}
