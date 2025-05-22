# proyecto-final
proyecto final del Boot Camp

## Uso

## Caracteristicas

Mi idea es iniciar con un proyecto de una tienda en linea que entiendo que me permitiria tener los dos microservicios propuestos 
que serian el inventario y el catalogo de productos


Microservicio de gestion de productos

Se considerarán las operaciones CRUD para el manejo de productos
- Agregar productos (comunicacion con el microservicio de inventario)
- Editar información de un producto
- Eliminar producto 
- Recuperar la informacion de un producto (comunicacion con el microservicio de inventarios para ver sus existencias en stock)

Microservicio de inventario

Se considerará la gestion del inventario/stock de los productos
 - Aumentar el stock (Cuando se realiza una compra a un proveedor y se actualiza con los productos recibidos)
 - Decrementar el stock (cuando una venta se confirma)
 - Historial de movimientos por producto (Entradas, Salidas, Ajustes)

Comunicacion entre los microservicios
