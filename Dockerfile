# Dockerfile para la aplicación Spring Boot (Backend)
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR generado en el directorio actual
COPY target/GroveStreet-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto 8080 para el backend de Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
