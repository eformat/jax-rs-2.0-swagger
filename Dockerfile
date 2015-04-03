#
# docker build -t jax-rs-2.0-swagger .
#
FROM java:7-jre

ENV WORK_HOME /usr/local/jaxrs-2.0-swagger
RUN mkdir -p "$WORK_HOME"
WORKDIR $WORK_HOME

RUN curl -S -L "http://eformat.co.nz/swagger/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar" -o jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar
#ADD target/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar $WORK_HOME/

EXPOSE 8080

# "-DSWAG_HOST=swagger.cloudapps.example.com"

CMD ["java", "-jar", "/usr/local/jaxrs-2.0-swagger/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar"]

#end