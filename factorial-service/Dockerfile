FROM maven:3.9.6-amazoncorretto-21-al2023 as build
MAINTAINER jorgehasd@gmail.com

COPY pom.xml .
COPY ./src ./src

RUN mvn clean verify

FROM amazoncorretto:21.0.1-al2023-headless

COPY --from=build target/factorial-service-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]