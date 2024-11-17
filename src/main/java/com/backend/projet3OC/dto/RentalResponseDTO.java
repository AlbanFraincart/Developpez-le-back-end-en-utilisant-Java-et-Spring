package com.backend.projet3OC.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentalResponseDTO {

    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String description;

    @JsonProperty("picture")
    private String image_url;

    @JsonProperty("owner_id")
    private Long owner_id;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date created_at;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date updated_at;
}
