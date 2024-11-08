# Usa una imagen base de Tomcat
FROM tomcat:9.0

# Copiar el archivo WAR a la carpeta webapps de Tomcat
COPY target/GroveStreet-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

# Exponer el puerto en el que Tomcat escucha
EXPOSE 8080
