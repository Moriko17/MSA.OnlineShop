FROM openjdk:8-jre-alpine
COPY ./build/libs/Gateway-0.0.1-SNAPSHOT.jar /Gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8762
ENTRYPOINT ["java", "-jar", "Gateway-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]