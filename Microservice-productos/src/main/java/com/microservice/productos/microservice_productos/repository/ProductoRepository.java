package com.microservice.productos.microservice_productos.repository;

import com.microservice.productos.microservice_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {


}
