package com.microservice.productos.microservice_productos.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventarioDTO {

    private Long productoId;

    private Integer cantidad;

    private String ubicacion;

}
