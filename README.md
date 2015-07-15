Using Swagger for REST API documentation
==============

A simple API microservice using the following technologies

Swagger, JAX-RS, Spring Framework, Apache CXF, Swagger UI, Embedded Jetty


### test it here
  http://localhost:8080/swagger/

### REST API served here
  http://localhost:8080/rest/

### build with maven
  mvn clean package

### build with docker
  docker build -t jax-rs-2.0-swagger .

### run
  java -DPORT=8080 -jar target/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar

# proxy note: 
  -DPORT=8080 - server port
  -DPROXY_PORT=80 - for running behind a proxy 

### run using docker
docker run -p 8080:8080 jax-rs-2.0-swagger

### run it on OSEv3

# command line
oc new-project api-example --display-name="Swagger Embeded Jetty API" --description='Swagger Embeded Jetty API'
oc new-app https://github.com/eformat/jax-rs-2.0-swagger#master --name=api-example --strategy=docker
oc expose dc "api-example" --port=8080 --generator=service/v1
oc expose service api-example --hostname=<externally visible project hostname>
