package com.microservice.inventario.controller;

import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.service.InventarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventario")
@Data
@AllArgsConstructor
@Slf4j
public class InventarioController {


    private final InventarioService inventarioService;

    /***
     * Este es el endpoint que va a consultar el ms de productos para
     * saber su stock
     * @param idProducto
     * @return
     */
    @GetMapping("/getProductStockById/{idProducto}")
    public ResponseEntity<Inventario> getProductStockById(@PathVariable("idProducto") Long idProducto){

        return  ResponseEntity.ok(this.inventarioService.findStockProductoById(idProducto));
    }



}
