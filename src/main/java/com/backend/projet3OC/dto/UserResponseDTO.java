package com.backend.projet3OC.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

//to retrieve user's information
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Date createdAt;
    private Date updatedAt;
}
