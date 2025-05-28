package com.microservice.productos.microservice_productos.service.impl;

import com.microservice.productos.microservice_productos.model.Producto;
import com.microservice.productos.microservice_productos.repository.ProductoRepository;
import com.microservice.productos.microservice_productos.service.ProductoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
@Slf4j
public class ProductoServiceImpl implements ProductoService {


   private final ProductoRepository productoRepository;


   @Override
   public List<Producto> getAllProductos() {
      return this.productoRepository.findAll();
   }

   @Override
   public Producto findProductoById(Long id) {
      return null;
   }

   @Override
   public Producto crearProducto(Producto producto) {
      return null;
   }

   @Override
   public Producto actualizarProducto(Producto producto, Long id) {
      return null;
   }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }


}
