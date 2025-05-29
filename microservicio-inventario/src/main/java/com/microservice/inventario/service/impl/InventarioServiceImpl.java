package com.microservice.inventario.service.impl;

import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.repository.InventarioRepository;
import com.microservice.inventario.service.InventarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
@Slf4j
public class InventarioServiceImpl implements InventarioService {


    private final InventarioRepository inventarioRepository;


    @Override
    public Inventario createInventario(Inventario inventario) {
        return null;
    }

    @Override
    public Inventario findStockProductoById(Long idProducto) {
        return this.inventarioRepository.findByProductoId(idProducto);
    }

    @Override
    public Inventario actualizarStockProductoById(Long id, Integer stock) {
        return null;
    }
}
