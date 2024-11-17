package com.backend.projet3OC.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
