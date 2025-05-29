package com.microservice.productos.microservice_productos.service;


import com.microservice.productos.microservice_productos.http.response.InventarioByProductoResponse;
import com.microservice.productos.microservice_productos.model.Producto;

import java.util.List;


public interface ProductoService {

    List<Producto> getAllProductos();

    Producto findProductoById(Long id);

    Producto crearProducto( Producto producto);

    Producto actualizarProducto(Producto producto, Long id);

    void eliminarProducto(Long id);


    /**
     * Se regresa la informacion ya tratada uniendo la informacion del producto
     * con la inforamcion que tiene el producto en el inventario
     * @param idProducto
     * @return
     */
    InventarioByProductoResponse findStockInformationByProductoId(Long idProducto);
}
