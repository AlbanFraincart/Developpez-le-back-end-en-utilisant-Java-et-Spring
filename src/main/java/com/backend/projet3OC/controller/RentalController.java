package com.backend.projet3OC.controller;

import com.backend.projet3OC.dto.RentalCreateDTO;
import com.backend.projet3OC.dto.RentalResponseDTO;
import com.backend.projet3OC.dto.RentalUpdateDTO;
import com.backend.projet3OC.exception.ErrorResponse;
import com.backend.projet3OC.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//handle crud for rentals
@RestController
@RequestMapping("/api/rentals")
@Validated
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

//get all
@GetMapping
@Operation(
        summary = "Obtenir toutes les locations",
        description = "Récupère la liste de toutes les locations disponibles.",
        security = @SecurityRequirement(name = "bearerAuth")
)
@ApiResponse(
        responseCode = "200",
        description = "Liste des locations récupérée avec succès",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDTO.class, type = "array"))
)
@ApiResponse(
        responseCode = "401",
        description = "Non autorisé",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
)
@ApiResponse(
        responseCode = "500",
        description = "Erreur interne du serveur",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
)
public ResponseEntity<Map<String, List<RentalResponseDTO>>> getAllRentals() {
    List<RentalResponseDTO> rentals = rentalService.getAllRentals();
    Map<String, List<RentalResponseDTO>> response = new HashMap<>();
    response.put("rentals", rentals);
    return ResponseEntity.ok(response);
}






    //    get by id
@GetMapping("/{id}")
@Operation(
        summary = "Obtenir une location par ID",
        description = "Récupère les détails d'une location spécifique en utilisant son identifiant unique.",
        security = @SecurityRequirement(name = "bearerAuth")
)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Location récupérée avec succès",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = RentalResponseDTO.class))
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Location non trouvée",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Non autorisé",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Erreur interne du serveur",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        )
})
public ResponseEntity<RentalResponseDTO> getRentalById(
        @Parameter(description = "ID unique de la location", required = true, example = "1")
        @PathVariable Long id) {
    RentalResponseDTO rental = rentalService.getRentalById(id);
    return ResponseEntity.ok(rental);
}





    //    creation
    @PostMapping(consumes = {"multipart/form-data"})
@Operation(
        summary = "Créer une nouvelle location",
        description = "Crée une nouvelle location avec les informations fournies.",
        security = @SecurityRequirement(name = "bearerAuth")
)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "201",
                description = "Location créée avec succès",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Données invalides",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Non autorisé",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Erreur interne du serveur",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
        )
})
public ResponseEntity<String> createRental(
        @Valid @ModelAttribute RentalCreateDTO rentalDTO) {
    rentalService.createRental(rentalDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body("Rental created successfully");
}






    //update
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    @Operation(
            summary = "Mettre à jour une location",
            description = "Met à jour les détails d'une location existante.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Location mise à jour avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Location non trouvée",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Non autorisé",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Accès interdit",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erreur interne du serveur",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    public ResponseEntity<String> updateRental(
            @Parameter(description = "ID unique de la location à mettre à jour", required = true, example = "1")
            @PathVariable Long id,
            @Valid @ModelAttribute RentalUpdateDTO rentalUpdateDTO) {
        rentalService.updateRental(id, rentalUpdateDTO);
        return ResponseEntity.ok("Rental updated successfully");
    }

}
