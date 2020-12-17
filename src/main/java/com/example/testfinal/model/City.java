package com.example.testfinal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Nation nation;
    private String name;

    private double area;
    private Long quantity;
    private Long GPA;
    private String description;
}
