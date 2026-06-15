#  MediAgenda — Sistema de Gestión de Citas Médicas

**Proyecto Final — Desarrollo de Aplicaciones Web**  
Universidad de El Salvador | Facultad Multidisciplinaria de Occidente  
Tutor: Ing. Victoria Castro

## Integrantes

| Nombre | Carnet |
|---|---|
| Oscar Manuel Peraza Velasquez | PV21001 |
| Geovanni Edmundo Ibáñez Campos | IC22003 |
| Wilber Daniel Garcia Martinez | GM16091 |
| Francisco Javier Calderón Castro | CC18147 |
| Diego Oswaldo Meza Argueta | MA20081 |

---

## Descripción del Proyecto

MediAgenda es una aplicación web full-stack para la gestión de citas médicas. Permite registrar pacientes, doctores (con sus especialidades) y administrar citas médicas con operaciones CRUD completas.

**Problema que resuelve:** Centralizar el agendamiento de citas médicas evitando conflictos de horarios, pérdida de registros y duplicidad de información.

**Funciones principales:**
- Registro y gestión de pacientes
- Registro de doctores con asignación de especialidades
- Creación, consulta, edición y cancelación de citas médicas
- Interfaz web responsiva accesible desde cualquier navegador

## Manual de Despliegue con Docker

### Pre-requisitos
- Docker Desktop instalado y corriendo
- Docker Compose v2+

### Pasos

**1. Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd Proyecto-DAW-GT01
```

**2. Levantar todo el sistema con un solo comando**
```bash
docker-compose up --build
```

Este comando construye las imágenes y levanta los 3 servicios:
- **db** -> PostgreSQL en puerto `5432`
- **backend** -> Spring Boot en puerto `8080`
- **frontend** -> Nginx en puerto `80`

**3. Acceder a la aplicación**

| Servicio | URL |
|---|---|
| Frontend | http://localhost |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| API Docs | http://localhost:8080/api-docs |

**4. Detener el sistema**
```bash
docker-compose down
```

Para eliminar también el volumen de la base de datos:
```bash
docker-compose down -v
```

---

## Tabla de Endpoints (API REST)

### Citas — `/api/citas`
| Método | Ruta | Descripción | Código |
|---|---|---|---|
| GET | `/api/citas` | Listar todas las citas | 200 |
| GET | `/api/citas/{id}` | Obtener cita por ID | 200 |
| POST | `/api/citas` | Crear nueva cita | 201 |
| PUT | `/api/citas/{id}` | Actualizar cita | 200 |
| DELETE | `/api/citas/{id}` | Eliminar cita | 204 |

### Pacientes — `/api/pacientes`
| Método | Ruta | Descripción | Código |
|---|---|---|---|
| GET | `/api/pacientes` | Listar todos los pacientes | 200 |
| GET | `/api/pacientes/{id}` | Obtener paciente por ID | 200 |
| POST | `/api/pacientes` | Registrar paciente | 201 |
| PUT | `/api/pacientes/{id}` | Actualizar paciente | 200 |
| DELETE | `/api/pacientes/{id}` | Eliminar paciente | 204 |

### Doctores — `/api/doctores`
| Método | Ruta | Descripción | Código |
|---|---|---|---|
| GET | `/api/doctores` | Listar todos los doctores | 200 |
| GET | `/api/doctores/{id}` | Obtener doctor por ID | 200 |
| POST | `/api/doctores` | Registrar doctor | 201 |
| PUT | `/api/doctores/{id}` | Actualizar doctor | 200 |
| DELETE | `/api/doctores/{id}` | Eliminar doctor | 204 |

### Especialidades — `/api/especialidades`
| Método | Ruta | Descripción | Código |
|---|---|---|---|
| GET | `/api/especialidades` | Listar especialidades | 200 |
| POST | `/api/especialidades` | Crear especialidad | 201 |
| PUT | `/api/especialidades/{id}` | Actualizar especialidad | 200 |
| DELETE | `/api/especialidades/{id}` | Eliminar especialidad | 204 |

## Stack Tecnológico

| Capa | Tecnología |
|---|---|
| Frontend | HTML5, CSS3, JavaScript (ES6+) |
| Backend | Spring Boot 3.2.5, Java 17 |
| ORM | JPA / Hibernate |
| Base de datos | PostgreSQL 15 |
| Documentación API | Swagger / SpringDoc OpenAPI 2.5 |
| Contenedores | Docker + Docker Compose |
| Servidor web | Nginx 1.25 |
