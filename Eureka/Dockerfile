FROM openjdk:8-jre-alpine
COPY ./build/libs/eureka-0.0.1-SNAPSHOT.jar /eureka-0.0.1-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]