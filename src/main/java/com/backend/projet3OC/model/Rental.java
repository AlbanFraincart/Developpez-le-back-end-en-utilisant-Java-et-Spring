package com.backend.projet3OC.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double surface;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String image_url;

    @Column( nullable = false)
    private Long owner_id;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column
    private Date updated_at;

}
