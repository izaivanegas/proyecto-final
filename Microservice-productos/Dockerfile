FROM azul/zulu-openjdk:17-jre

WORKDIR /app

COPY target/microservice-productos-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

CMD ["java","-jar","app.jar"]