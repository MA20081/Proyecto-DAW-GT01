# =====================================================
# Dockerfile — Backend Spring Boot (MediAgenda)
# Etapa 1: compilar el JAR con Maven
# Etapa 2: imagen ligera para ejecutar
# =====================================================

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -q

COPY src ./src
RUN mvn clean package -DskipTests -q

# ---- Imagen de ejecucion ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
