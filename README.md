# GroveStreet

Este proyecto combina **Spring Boot** (Backend), **Angular** (Frontend) y **PostgreSQL** (Base de Datos) para crear una aplicación inmobiliaria.

---

## Backend - Spring Boot

### **Servidor de Desarrollo**
Para iniciar el servidor backend, es tan sencillo como runear la aplicación desde IntelliJ.
El servidor se ejecutará en http://localhost:8080

---

## Configuración de la Base de Datos
La configuración de la base de datos y otros parámetros se realiza en el archivo application.properties:
```# Nombre de la aplicacion
spring.application.name=GroveStreet

server.port=8080

# Configuracion de la base de datos PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/grovestreet_db
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

springdoc.api-docs.path=/api-docs

# Configuracion JWT
jwt.secret=AndWhenImBackInChicagoIFeelItAnotherVersionOfMeIWasInItIWaveGoodbyeToTheEndOfBeginning
jwt.expiration=86400000

# Configuracion de seguridad adicional
spring.security.user.name=admin
spring.security.user.password=admin

# Configuracion para limitar el tamaño de las imagens
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=50MBç
```
---

## Dependencias Principales
- **Spring Boot Starter Web:** Para exponer APIs REST.

- **Spring Boot Starter Security:** Para manejar autenticación y autorización.

- **JWT:** Para manejo de tokens de autenticación.

- **Spring Boot Starter Data JPA:** Para interactuar con la base de datos.

- **PostgreSQL Driver:** Para conectar la base de datos PostgreSQL.

---

## Endpoints API
Algunos endpoints principales:

- **Autenticación: POST /api/autenticacion**

- **Registro: POST /api/registro**

- **Propiedades:**
  -  GET /api/propiedades
  -  POST /api/propiedades/new
  -  GET /api/propiedades/{id}

---

## Frontend - Angular
### **Requisitos Previos**
- Node.js y npm instalados.
- Angular CLI instalado globalmente:
```
npm install -g @angular/cli
```
### **Servidor de Desarrollo**
```
cd Angular
ng serve
```
La aplicación estará disponible en: http://localhost:4200

### **Comandos Útiles**
- Generar un nuevo componente:
```
ng generate component component-name
```

- Construir la apliación
```
ng build
```
El resultado estará en el directorio dist/.

### **Autenticación**
El frontend usa JWT para autenticación. El token se almacena en localStorage.

### **Componentes Principales**
- **LandingPageComponent:** Página de inicio.
- **PropiedadesComponent:** Lista todas las propiedades.
- **PropiedadNuevaComponent:** Formulario para agregar propiedades.
- **PropiedadesListComponent:** Lista de propiedades del usuario porm su ID.
- **PropiedadesFilterComponent:** Filtra las propiedades en la landing page.

---

## Base de Datos - PostgresSQL

### **Despliegue con Docker**

La base de datos se ejecuta en un contenedor Docker utilizando la imagen oficial de PostgreSQL.

#### Iniciar el Contenedor

Para un contenedor nuevo deberemos de ejecutar:
```
docker run --name grovestreet_db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -p 5432:5432 -d postgres:latest
```

#### Acceder a la Base de Datos

En mi caso he usado la herramienta DataGrip del entorno de JetBrains:

- Host: localhost
- Puerto: 5432
- Base de Datos: grovestreet_db
- Usuario: admin
- Contraseña: admin

#### Ver Contenedores en Ejecución
```
docker ps
```

---

## Despliegue Completo de la Aplicación

### **1. Base de Datos**

Asegúrate de que el contenedor Docker esté en ejecución:
```
docker start grovestreet_db
```
O desde la aplicación de Docker.

### **2. Backend**

Navega al directorio del backend y ejecuta:
```
./mvnw spring-boot:run
```
O como es mi caso ejecuta desde el propio IDE.

### **3. Frontend**

Navega al directorio Angular y ejecuta:
```
cd Angular
ng serve
```

---

## Estructura del Proyecto
```
GroveStreet/
├── backend/                  # Código del backend en Spring Boot
│   ├── src/
│   ├── pom.xml
│   └── application.properties
├── Angular/                  # Código del frontend en Angular
│   ├── src/
│   ├── angular.json
│   └── package.json
└── README.md                 # Este archivo
```

---

## Tecnologías Utilizadas

- **Spring Boot:** Framework para el backend.
- **Angular:** Framework para el frontend.
- **PostgreSQL:** Base de datos relacional.
- **Docker:** Para contenerización de la base de datos.
- **JWT:** Manejo de autenticación con tokens.

---




















