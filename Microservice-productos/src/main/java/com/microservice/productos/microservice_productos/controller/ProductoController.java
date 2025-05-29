package com.microservice.productos.microservice_productos.controller;

import com.microservice.productos.microservice_productos.http.response.InventarioByProductoResponse;
import com.microservice.productos.microservice_productos.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microservice.productos.microservice_productos.service.ProductoService;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Data
@RestController
@RequestMapping("/producto/")
@AllArgsConstructor
public class ProductoController {


    private final ProductoService productoService;


    @GetMapping("/todos")
    public ResponseEntity<List<Producto>> getAllProductos(){
        return ResponseEntity.ok(this.productoService.getAllProductos());
    }


    @PostMapping("nuevo")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto, UriComponentsBuilder ucb){
        URI uriEmpleado = ucb
                .path("/producto/nuevo/{id}")
                .buildAndExpand(this.productoService.crearProducto(producto).getId())
                .toUri();
        return ResponseEntity.created(uriEmpleado).build();
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto, @PathVariable("id") Long id){
        return ResponseEntity.ok(this.productoService.actualizarProducto(producto,id));
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id){
        this.productoService.eliminarProducto(id);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/stockinventario/{idProduct}")
    public ResponseEntity<InventarioByProductoResponse> findInventariobyIdProducto(@PathVariable("idProduct") Long idProduct ){
        return ResponseEntity.ok(this.productoService.findStockInformationByProductoId(idProduct));
    }


}
