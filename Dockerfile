# 🔧 Etapa 1 - Build (Maven)
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia tudo do projeto
COPY . .

# Gera o JAR
RUN mvn clean package -DskipTests


# 🚀 Etapa 2 - Runtime (leve)
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copia o JAR gerado da etapa anterior
COPY --from=build /app/target/backend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]