# API de Gestión de Reservas

Este proyecto es un microservicio desarrollado en **Java** utilizando el ecosistema de **Spring Boot**. Está diseñado bajo una arquitectura limpia en capas (Controlador, Servicio, Repositorio, Modelo) para gestionar el agendamiento y los estados de citas o servicios en tiempo real de manera eficiente y segura.

---

## Tecnologías y Herramientas Utilizadas

* **Java 17 / 25** (Utilizando sintaxis nativa estándar sin librerías de generación externas).
* **Spring Boot 3.3.4** (Framework principal para el ecosistema REST).
* **Spring Data JPA** (Persistencia, consultas y mapeo de datos al motor relacional).
* **H2 Database** (Base de datos relacional en memoria para desarrollo ágil y pruebas rápidas).
* **Jakarta Bean Validation** (Mecanismo de seguridad y validación de datos activa en los endpoints).
* **Maven** (Gestor de dependencias y construcción del proyecto).

---

## Reglas de Negocio e Integridad de Datos

Para garantizar la consistencia de la información y proteger la base de datos de registros inconsistentes, el sistema cuenta con un escudo de validación activa mediante anotaciones en el modelo:

1. **Cliente:** El nombre del cliente es estrictamente obligatorio, no puede enviarse vacío o con puros espacios (`@NotBlank`), y debe tener una extensión de entre 3 y 100 caracteres (`@Size`).
2. **Fecha y Hora:** El sistema valida de forma automática que ninguna reserva sea agendada en el pasado; la fecha siempre debe ser posterior al tiempo actual de la solicitud (`@Future`).
3. **Estado Inicial:** Toda reserva creada ingresa de forma automática al sistema con el estado `PENDIENTE`, delegando esta lógica al servicio de forma segura.

---

## Endpoints de la API

La URL base para interactuar con la aplicación localmente es:  
`http://localhost:8080/api/reservas`

| Método | Endpoint | Descripción | Parámetros / Cuerpo (JSON) |
| :--- | :--- | :--- | :--- |
| **GET** | `/` | Listar todas las reservas existentes. | Ninguno |
| **POST** | `/` | Crear una nueva reserva validando los campos. | Cuerpo con `cliente`, `fechaHora`, `servicio` |
| **PATCH**| `/{id}/estado` | Actualizar dinámicamente el estado de una reserva. | Requiere `nuevoEstado` como RequestParam |
| **DELETE**| `/{id}` | Eliminar físicamente una reserva por su ID. | ID de la reserva en la URL |

### 📥 Ejemplo de JSON para Crear Reserva (POST)

```json
{
    "cliente": "Juan Perez",
    "fechaHora": "2026-03-17T16:30:00",
    "servicio": "Evaluación Técnica"
}
```

Nota: Si se intenta enviar un cliente vacío o una fecha del pasado, la API denegará la petición inmediatamente respondiendo con un estado 400 Bad Request.


## Cómo Ejecutar el Proyecto Localmente
Prerrequisitos
* Tener instalado un JDK (Java Development Kit) 17 o superior en tu máquina.

* Disponer de Maven instalado (o utilizar el wrapper ./mvnw incluido en el proyecto).

## Pasos para levantar el servidor
1. Clona este repositorio o descarga el código fuente en tu computadora.

2. Abre una terminal de comandos (o la terminal integrada de VS Code) en la carpeta raíz del proyecto.

3. Ejecuta el comando de limpieza, compilación y arranque:

```Bash
./mvnw clean spring-boot:run
```

El sistema descargará las dependencias necesarias y el servidor se desplegará de forma exitosa en el puerto 8080.

## Consola de Base de Datos H2
Puedes inspeccionar las tablas generadas por JPA y los registros almacenados en tiempo real ingresando desde tu navegador web a:

* http://localhost:8080/h2-console

* JDBC URL: jdbc:h2:mem:reservasdb

* User Name: sa

* Password: (Dejar completamente en blanco)
