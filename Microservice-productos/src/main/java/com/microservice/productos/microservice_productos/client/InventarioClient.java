package com.microservice.productos.microservice_productos.client;


import com.microservice.productos.microservice_productos.dto.InventarioDTO;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="ms-inventario", url="localhost:8090/inventario" )
public interface InventarioClient {




    @GetMapping("/getProductStockById/{idProducto}")
    InventarioDTO getProductStockById(@PathVariable("idProducto") Long idProducto);

}
