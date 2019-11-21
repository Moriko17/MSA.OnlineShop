FROM openjdk:8-jre-alpine
COPY ./payment-app/build/libs/payment-app-0.0.1-SNAPSHOT.jar /payment-app-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "payment-app-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]