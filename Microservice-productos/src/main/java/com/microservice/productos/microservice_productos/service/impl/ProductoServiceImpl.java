package com.microservice.productos.microservice_productos.service.impl;

import com.microservice.productos.microservice_productos.client.InventarioClient;
import com.microservice.productos.microservice_productos.dto.InventarioDTO;
import com.microservice.productos.microservice_productos.http.response.InventarioByProductoResponse;
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

   private final InventarioClient inventarioClient;


   @Override
   public List<Producto> getAllProductos() {
      return this.productoRepository.findAll();
   }

   @Override
   public Producto findProductoById(Long id) {

      return this.productoRepository.findById(id).orElse(null);

   }

   @Override
   public Producto crearProducto(Producto producto) {
      return this.productoRepository.save(producto);
   }

   @Override
   public Producto actualizarProducto(Producto producto, Long id) {
      var productoEncontrado = this.productoRepository.findById(id).orElse(null);
      if( productoEncontrado!= null ){
         productoEncontrado.setNombre(producto.getNombre());
         productoEncontrado.setDescripcion(producto.getDescripcion());
         productoEncontrado.setSku(producto.getSku());
         productoEncontrado.setPrecio(producto.getPrecio());
         return productoRepository.save(productoEncontrado);
      }
      return  null;

   }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }


   @Override
   public InventarioByProductoResponse findStockInformationByProductoId(Long idProducto) {

      //Obtenemos la informacion de mi producto
      var producto = this.productoRepository.findById(idProducto).orElse(new Producto());
      if(producto.getId()!=null){
         //ahora se obtiene la informacion del stock para el producto
         InventarioDTO inventarioDTO =  this.inventarioClient.getProductStockById(producto.getId());

         return InventarioByProductoResponse.builder()
                 .nombre(producto.getNombre())
                 .sku(producto.getSku())
                 .descripcion(producto.getDescripcion())
                 .precio(producto.getPrecio())
                 .inventario(inventarioDTO).build();
      }

      return null;
   }


}
