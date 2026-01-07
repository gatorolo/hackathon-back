# Fase 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Compilamos saltando tests para evitar errores de conexión a DB durante el build
RUN mvn clean package -Dmaven.test.skip=true

# Fase 2: Run
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Usamos un comodín para copiar el jar que sea que se haya creado
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-Dserver.port=8000", "-jar", "app.jar"]