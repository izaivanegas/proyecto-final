FROM azul/zulu-openjdk:17-jre

WORKDIR /app

COPY target/microservice-config-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888

CMD ["java","-jar","app.jar"]