FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/uicc-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005","-jar","/app.jar"]

