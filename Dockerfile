
# Fase 1: Compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos el pom y descargamos dependencias (esto ahorra tiempo en el deploy)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el archivo app.jar generado en la fase anterior
COPY --from=build /app/target/app.jar app.jar

# Exponemos el puerto 8000 para Koyeb
EXPOSE 8000

# Ejecutamos la aplicación
ENTRYPOINT ["java", "-Dserver.port=8000", "-jar", "app.jar"]