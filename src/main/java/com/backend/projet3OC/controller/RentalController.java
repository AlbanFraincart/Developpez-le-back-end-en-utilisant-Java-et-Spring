package com.backend.projet3OC.controller;

import com.backend.projet3OC.dto.RentalCreateDTO;
import com.backend.projet3OC.dto.RentalResponseDTO;
import com.backend.projet3OC.dto.RentalUpdateDTO;
import com.backend.projet3OC.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
@Validated
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    @GetMapping
    public ResponseEntity<Map<String, List<RentalResponseDTO>>> getAllRentals() {
        List<RentalResponseDTO> rentals = rentalService.getAllRentals();
        Map<String, List<RentalResponseDTO>> response = new HashMap<>();
        response.put("rentals", rentals);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getRentalById(@PathVariable Long id) {
        RentalResponseDTO rental = rentalService.getRentalById(id);
        return ResponseEntity.ok(rental);
    }


    @PostMapping
    public ResponseEntity<String> createRental(@Valid @ModelAttribute RentalCreateDTO rentalDTO) {
        rentalService.createRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rental created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRental(
            @PathVariable Long id,
            @Valid @ModelAttribute RentalUpdateDTO rentalUpdateDTO) {
        rentalService.updateRental(id, rentalUpdateDTO);
        return ResponseEntity.ok("Rental updated successfully");
    }
}
