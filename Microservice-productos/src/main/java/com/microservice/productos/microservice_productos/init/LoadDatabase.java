package com.microservice.productos.microservice_productos.init;


import com.microservice.productos.microservice_productos.model.Producto;
import com.microservice.productos.microservice_productos.repository.ProductoRepository;
import com.microservice.productos.microservice_productos.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(ProductoRepository productoRepository){

            return args -> {
                if( productoRepository.count() == 0 ){
                    Producto producto1 = new Producto();
                    producto1.setNombre("Laptop Lenovo");
                    producto1.setDescripcion("Laptop para el trabajo o escuela");
                    producto1.setSku("LAP-NEG-14-INT-9I");
                    producto1.setPrecio(35000.0f);
                    productoRepository.save(producto1);

                    Producto producto2 = new Producto();
                    producto2.setNombre("Macbook");
                    producto2.setDescripcion("Laptop para el trabajo o escuela");
                    producto2.setSku("LAP-GRIS-13-M1-AX");
                    producto2.setPrecio(42000.0f);
                    productoRepository.save(producto2);

                    log.info("Se han insertado los elementos en la tablaßß");
                }

            };
    }


}
