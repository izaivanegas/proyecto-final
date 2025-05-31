package com.microservice.productos.microservice_productos;



import com.microservice.productos.microservice_productos.model.Producto;
import com.microservice.productos.microservice_productos.repository.ProductoRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductosIntegrationTests {


    private final ProductoRepository productoRepository;

    @LocalServerPort
    private int port;

    @Autowired
    ProductosIntegrationTests(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }



    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.28");

    @BeforeAll
    static void beforeAll() {
        mysql.start();
    }


    @AfterAll
    static void afterAll(){
        mysql.stop();
    }


    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",mysql::getJdbcUrl);
        registry.add("spring.datasource.username",mysql::getUsername);
        registry.add("spring.datasource.password",mysql::getPassword);
    }

    @BeforeEach
    void setUp(){
        RestAssured.baseURI = "http://localhost:" + port;
        productoRepository.deleteAll();
    }

    @Test
    void regresaTodosLosProductoc(){
        List<Producto> productos = List.of(
                new Producto(null,"A-B-C-D1","producto1","descripcion1",100000.34f),
                new Producto(null,"A-B-C-D2","producto2","descripcion2",200000.34f),
                new Producto(null,"A-B-C-D3","producto3","descripcion3",300000.34f)
        );
        this.productoRepository.saveAll(productos);

        given().contentType(ContentType.JSON).when().get("/producto/todos")
                .then().statusCode(200).body(".",hasSize(3));
    }


}
