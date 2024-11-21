package com.backend.projet3OC.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Réponse d'erreur standard")
public class ErrorResponse {

    @Schema(description = "Code d'erreur", example = "NOT_FOUND")
    private String errorCode;

    @Schema(description = "Message détaillé de l'erreur", example = "Rental not found with id: 1")
    private String message;
}
