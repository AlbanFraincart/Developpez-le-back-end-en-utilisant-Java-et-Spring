package com.backend.projet3OC.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Schema(description = "Réponse pour une location")
public class RentalResponseDTO {

    @Schema(description = "ID unique de la location", example = "1")
    private Long id;

    @Schema(description = "Nom de la location", example = "Appartement T2")
    private String name;

    @Schema(description = "Surface en mètres carrés", example = "50.0")
    private Double surface;

    @Schema(description = "Prix en euros", example = "750.0")
    private Double price;

    @Schema(description = "Description détaillée", example = "Bel appartement au centre-ville")
    private String description;

    @JsonProperty("picture")
    @Schema(description = "URL de l'image de la location", example = "http://localhost:3001/api/files/filename.jpg")
    private String image_url;

    @JsonProperty("owner_id")
    @Schema(description = "ID du propriétaire", example = "2")
    private Long owner_id;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Date de création", example = "2024-11-21T14:47:00.484Z")
    private Date created_at;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    @Schema(description = "Date de dernière mise à jour", example = "2024-11-21T14:47:00.484Z")
    private Date updated_at;
}
