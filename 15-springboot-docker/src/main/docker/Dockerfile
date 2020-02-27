FROM openjdk:8-jre
VOLUME /tmp
ADD docker-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]