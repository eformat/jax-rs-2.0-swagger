#
# docker build -t jax-rs-2.0-swagger .
#
FROM java:8-jre

ENV WORK_HOME /usr/local/jaxrs-2.0-swagger
RUN mkdir -p "$WORK_HOME"
WORKDIR $WORK_HOME

ENV get-new-jar 11

RUN curl -S -L "http://eformat.co.nz/swagger/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar" -o jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar
RUN curl -S -L "http://eformat.co.nz/swagger/startup.sh" -o startup.sh

RUN chmod +x /usr/local/jaxrs-2.0-swagger/startup.sh

EXPOSE 8080

ENV JAVA_JAR /usr/local/jaxrs-2.0-swagger/jax-rs-2.0-swagger-0.0.1-SNAPSHOT.jar
ENV PROXY_PORT 80
ENV PORT 8080

ENTRYPOINT ["/usr/local/jaxrs-2.0-swagger/startup.sh"]
CMD [""]

#end