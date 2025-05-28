package com.microservice.productos.microservice_productos.controller;

import com.microservice.productos.microservice_productos.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.microservice.productos.microservice_productos.service.ProductoService;

import java.util.List;

@Data
@RestController
@RequestMapping("/producto/")
@AllArgsConstructor
public class ProductoController {


    private final ProductoService productoService;


    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAllProductos(){
        return ResponseEntity.ok(this.productoService.getAllProductos());
    }



}
