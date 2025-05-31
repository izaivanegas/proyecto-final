package com.microservice.productos.microservice_productos;


import com.google.gson.JsonArray;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.microservice.productos.microservice_productos.model.Producto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;


import java.net.URI;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class MicroserviceProductosApplicationTests {


    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    /**
     * Se valida que este regesando al menos 2 elementos por defecto
     */
    @Test
    void regresaListaProdcutos(){
       ResponseEntity<String> response =  restTemplate.getForEntity("/producto/todos",String.class);
       //Verificacion de respuesta 200
       assertThat( response.getStatusCode() ).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int productoNum = documentContext.read("$.length()");
        log.info("Elementos que vienen en la respuesta:{}",productoNum);
        //Por defecto se tienen 2 elementos o sea q seria lo minimo
        assertThat(productoNum).isGreaterThanOrEqualTo(2);
        //como meti 2 de prueba deben de ser el 1 y el 2 al menos
        List<Integer> ids = documentContext.read("$[*].id");
        log.info("Los ids: {}", ids);
        assertThat(ids).contains(1,2);
    }

    /**
     * Se verifica que al menos cuando se pide el stock de un elemento
     * que se inserto por defecto regresa el valor indicado que
     * se estarian pidiendo siempre los ids = 1 para producto y para
     * su stock en inventario que son diferente dbs
     */
    @Test
    void regresaStockProductoPorId(){
      ResponseEntity<String> response = restTemplate.getForEntity("/producto/stockinventario/1", String.class);
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
      //Se verifica que esta activo
      DocumentContext documentContext = JsonPath.parse(response.getBody());
      var valorRecuperado = documentContext.read("$[*].productoId");
      List<Integer> productosId = (List<Integer>) valorRecuperado;
      log.info("El id recuperado es: {}",productosId.get(0));
      assertThat(productosId.get(0)).isEqualTo(1);
      var nombreProducto = documentContext.read("$.nombre");
      log.info("El nombre es: {}",nombreProducto);
      //aqui se verifica que es el primer producto por defecto
      assertThat(nombreProducto).isEqualTo("Laptop Lenovo");

    }


    /**
     * Verifica la creacion de un producto
     */
    @Test
    @DirtiesContext
    void verificarCreacionProducto(){

        String baseUrl = "http://localhost:"+port+"/producto/nuevo";
        String requestBody = """
        {
            "nombre": "Laptop Lenovo2",
            "sku": "LAP-NEG-14-INT-9I2",
            "descripcion": "Laptop para el trabajo o escuela2",
            "precio": 35020.0
        }
    """;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Establecer "application/json"
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        log.info(response.getBody());
        //No pude hacer jalar el ejemplo de la clase

    }


    @Test
    @DirtiesContext
    void validarActualizacionProducto() {
        Producto producto = new Producto();
        producto.setSku("LAP-BLA-15-M1-2923");
        producto.setNombre("Laptop edicion");
        producto.setDescripcion("Mi producto de prueba");
        producto.setPrecio(32344.34f);

        HttpEntity<Producto> request = new HttpEntity<>(producto);

        ResponseEntity<Void> response = restTemplate
                .exchange("/producto/actualizar/2", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/producto/busca/2", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
        Number id = documentContext.read("$.id");
        String nombre = documentContext.read("$.nombre");
        String sku = documentContext.read("$.sku");
        String descripcion = documentContext.read("$.descripcion");

        assertThat(id).isEqualTo(2);
        assertThat(nombre).isEqualTo("Laptop edicion");
        assertThat(sku).isEqualTo("LAP-BLA-15-M1-2923");
        assertThat(descripcion).isEqualTo("Mi producto de prueba");


    }



    /**
     * Verifica que el servicio elimina un empleado existente
     */
    @Test
    @DirtiesContext
    void shouldDeleteAnEmpleadoById() {
        ResponseEntity<Void> response = restTemplate
                .exchange("/producto/eliminar/2", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> getResponse = restTemplate
                .getForEntity("/producto/busca/2", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);


    }

}
