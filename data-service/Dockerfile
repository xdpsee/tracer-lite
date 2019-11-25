FROM openjdk:8-jdk-alpine
VOLUME /tmp
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=5007,server=y,suspend=n
ADD target/data-service-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Xdebug -Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

