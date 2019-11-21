FROM openjdk:8-jre-alpine
COPY ./orders-app/build/libs/orders-app-0.0.1-SNAPSHOT.jar /orders-app-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "orders-app-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]