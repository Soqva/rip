FROM openjdk:11 as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

FROM base as test
RUN ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run"]

FROM base as build
RUN ["./mvnw", "package"]

FROM openjdk:11-jre-slim as production
COPY --from=build /app/target/backend-*.jar /backend.jar
CMD ["sh", "-c", "java -Dspring.profiles.active=prod -jar /backend.jar"]
