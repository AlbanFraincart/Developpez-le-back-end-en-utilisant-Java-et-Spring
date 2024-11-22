package com.backend.projet3OC.service;

import com.backend.projet3OC.dto.RentalCreateDTO;
import com.backend.projet3OC.dto.RentalResponseDTO;
import com.backend.projet3OC.dto.RentalUpdateDTO;
import java.util.List;

//define crud methods for rentals
public interface RentalService {
    void createRental(RentalCreateDTO rentalDTO);
    List<RentalResponseDTO> getAllRentals();
    RentalResponseDTO getRentalById(Long id);
    void updateRental(Long id, RentalUpdateDTO rentalUpdateDTO);

}
