# Fase 1: Construcción
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# Copiamos los archivos de configuración primero para aprovechar el cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y compilamos el archivo app.jar
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Ejecución
# Usamos esta imagen que es muy estable en Koyeb
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos el jar desde la fase de build
# Usamos el asterisco por si el nombre varía, pero lo renombramos a app.jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8000

# Ejecutamos con el puerto forzado a 8000
ENTRYPOINT ["java", "-Dserver.port=8000", "-jar", "app.jar"]