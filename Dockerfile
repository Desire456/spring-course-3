FROM gradle:jdk11-hotspot AS build
WORKDIR /app
COPY . .
RUN gradle build

FROM openjdk:11-jdk-slim
ARG VERSION=0.0.1
WORKDIR /app
COPY --from=build /app/build/libs/api-${VERSION}.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080
