package com.microservice.inventario.service;

import com.microservice.inventario.model.Inventario;

public interface InventarioService {

    /**
     * Este deberia de ser una lectura a un broker pero como
     * este proyecto solo tiene 2 microservicios lo va a mandar a llamar
     * el microservicio de productos cuando realizar el alta de un producto
     * aqui se debera de crear un inventario o un renglon en el inventario con el id del
     * producto con un stock en cero
     * @param inventario
     * @return
     */
    Inventario createInventario(Inventario inventario);

    /***
     * Buscara regresar el stock que tiene el producto, este si aplica para este caso dado que
     * el microservicio de producto si debe de tener acceso a la informacion que tiene cada
     * producto en el inventario
     * @param idProducto
     * @return
     */
    Inventario findStockProductoById(Long idProducto);


    /***
     * Se esperaria un numero positivo para que aumente el stock y un numero negativo para disminuir el
     * stock en el inventario, y el mismo caso como solo son 2 microservicios vamos a inventar que en el
     * microservicio de productos se puede aumentar o disminuir ya que segun mi analisis el que deberia de hacer
     * esto es cuando hay una confirmacion de un pedido o una cancelacion entonces eso deberia de ser otro
     * microservicio como de ventas u pedidos pero como no lo vamos a tener entonces se jalara desde el
     * microservicio de productos
     * @param id
     * @param stock
     * @return
     */
    Inventario actualizarStockProductoById(Long id, Integer stock);


}
