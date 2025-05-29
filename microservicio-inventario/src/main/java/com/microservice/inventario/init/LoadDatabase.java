package com.microservice.inventario.init;

import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {




    @Bean
    CommandLineRunner initDatabase(InventarioRepository inventarioRepository){


        /***
         * Para complementar lo que hizo en el ms de productos q fue inventar dos elementos
         * aqui estaria su correspondiente registro en el inventario y su cantidad en stock
         */
        return args -> {
            if( inventarioRepository.count() == 0 ){
                Inventario inventario1 = new Inventario();
                //Laptop Lenovo
                inventario1.setProductoId(1l);
                inventario1.setUbicacion("Rack 1");
                inventario1.setCantidad(10);
                inventarioRepository.save(inventario1);


                Inventario inventario2 = new Inventario();
                //macbook
                inventario2.setProductoId(2l);
                inventario2.setUbicacion("Rack 4");
                inventario2.setCantidad(5);
                inventarioRepository.save(inventario2);


                log.info("Se han insertado los elementos en el inventario");
            }

        };
    }



}
