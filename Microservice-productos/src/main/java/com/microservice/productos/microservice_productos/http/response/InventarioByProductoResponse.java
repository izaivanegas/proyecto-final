package com.microservice.productos.microservice_productos.http.response;


import com.microservice.productos.microservice_productos.dto.InventarioDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventarioByProductoResponse {


    private String nombre;

    private String sku;

    private String descripcion;

    private Float precio;
    /**
     * Esta es la informacion que viene del microservicio de inventario
     * que es un renglon completo
     */
    private InventarioDTO inventario;


}
