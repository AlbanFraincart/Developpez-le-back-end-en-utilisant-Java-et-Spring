package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.RentalCreateDTO;
import com.backend.projet3OC.dto.RentalResponseDTO;
import com.backend.projet3OC.dto.RentalUpdateDTO;
import com.backend.projet3OC.dto.UserResponseDTO;

import com.backend.projet3OC.model.Rental;
import com.backend.projet3OC.repository.RentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final FileStorageService fileStorageService;
    private final AuthService authService;


    // Constructor to inject dependencies required for database operations
    // and file storage functionality.

    public RentalServiceImpl(RentalRepository rentalRepository, FileStorageService fileStorageService, AuthService authService) {
        this.rentalRepository = rentalRepository;
        this.fileStorageService = fileStorageService;
        this.authService = authService;

    }

    @Override
    public void createRental(RentalCreateDTO rentalDTO) {
        // Handles the process of creating a rental:
        // Store the image and get its file name
        String fileName = fileStorageService.storeFile(rentalDTO.getPicture());

        // Construct the image URL
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/files/")
                .path(fileName)
                .toUriString();

        // Create Rental entity
        Rental rental = new Rental();
        rental.setName(rentalDTO.getName());
        rental.setSurface(rentalDTO.getSurface());
        rental.setPrice(rentalDTO.getPrice());
        rental.setDescription(rentalDTO.getDescription());
        rental.setImage_url(imageUrl);

        // get current user and define ownerId
        UserResponseDTO currentUser = authService.getCurrentUser();
        rental.setOwner_id(currentUser.getId());

        // Save the rental
        rentalRepository.save(rental);
    }

    @Override
    public List<RentalResponseDTO> getAllRentals() {
        // Retrieves all rental records from the database, converts them into DTOs,
        // and returns the results as a list.
        return rentalRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RentalResponseDTO getRentalById(Long id) {
        // Fetches a specific rental by its ID:
        // - Throws an exception if no record is found.
        // - Converts the retrieved entity into a response DTO.
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
        return convertToResponseDTO(rental);
    }

    @Override
    public void updateRental(Long id, RentalUpdateDTO rentalUpdateDTO) {
        // retrieve existing rental
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));

        // verify current user is owner
        UserResponseDTO currentUser = authService.getCurrentUser();
        if (!rental.getOwner_id().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized to update this rental");
        }

        // update fields
        rental.setName(rentalUpdateDTO.getName());
        rental.setSurface(rentalUpdateDTO.getSurface());
        rental.setPrice(rentalUpdateDTO.getPrice());
        rental.setDescription(rentalUpdateDTO.getDescription());

        // handle image upload if given
        if (rentalUpdateDTO.getPicture() != null && !rentalUpdateDTO.getPicture().isEmpty()) {
            // delete old image (optional)
            // fileStorageService.deleteFile(rental.getImage_url()); // Implémenter cette méthode si nécessaire

            // store new image
            String newFileName = fileStorageService.storeFile(rentalUpdateDTO.getPicture());

            // build new url
            String newImageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/")
                    .path(newFileName)
                    .toUriString();

            rental.setImage_url(newImageUrl);
        }

        // save modifications
        rentalRepository.save(rental);
    }


    private RentalResponseDTO convertToResponseDTO(Rental rental) {
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setDescription(rental.getDescription());
        dto.setImage_url(rental.getImage_url());
        dto.setOwner_id(rental.getOwner_id());
        dto.setCreated_at(rental.getCreated_at());
        dto.setUpdated_at(rental.getUpdated_at());
        return dto;
    }
}
