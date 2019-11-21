FROM openjdk:8-jre-alpine
COPY ./warehouse-app/build/libs/warehouse-app-0.0.1-SNAPSHOT.jar /warehouse-app-0.0.1-SNAPSHOT.jar
EXPOSE 8763
ENTRYPOINT ["java", "-jar", "warehouse-app-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]