Using Swagger for REST API documentation
==============

### test here
  http://localhost:8080/swagger/

### build with maven
  mvn clean package

### build with docker
  docker build -t jax-rs-2.0-swagger .

### run
  java -DPORT=8090 -jar target/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar

### run docker
  docker run -p 8080:8080 jax-rs-2.0-swagger
