package com.backend.projet3OC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreateDTO {

    @NotBlank
    private String message;

    @NotNull
    private Long rental_id;
}
