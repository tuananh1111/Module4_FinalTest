package com.example.testfinal.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Nhập chuỗi ngắn")
    private String name;
    private double area;
    private Long quantity;
    private Long GPA;
    @NotEmpty(message = "Nhập vào một đoạn văn dài")
    private String description;
}
