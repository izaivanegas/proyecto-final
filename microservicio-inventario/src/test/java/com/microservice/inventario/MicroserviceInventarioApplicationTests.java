package com.microservice.inventario;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.RANDOM_PORT )
@Slf4j
public class MicroserviceInventarioApplicationTests {


    @Autowired
    TestRestTemplate testRestTemplate;


    /**
     * Se realiza la validacion para recuperar el stock que se mete por defecto
     * entonces siempre que se ejecute va a tener ese dato inicial
     */
    @Test
    void verificarStockProductoById(){

        ResponseEntity<String> response = testRestTemplate.getForEntity("/inventario/getProductStockById/1",String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
         var id = documentContext.read("$.id");
         var productoId = documentContext.read("$.productoId");
         var cantidad = documentContext.read("$.cantidad");
         var ubicacion = documentContext.read("$.ubicacion");
         assertThat(id).isEqualTo(1);
         assertThat(productoId).isEqualTo(1);
         assertThat(cantidad).isEqualTo(10);
         assertThat(ubicacion).isEqualTo("Rack 1");



    }



}
