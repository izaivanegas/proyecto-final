package com.microservice.inventario.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "inventario")
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long productoId;

    private Integer cantidad;

    private String ubicacion;

}
