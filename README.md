# Back-SV-Patients CareConnect

## Descripción
El servicio `back-sv-patients` es un componente del sistema CareConnect, desarrollado para el Hospital Universitario San Ignacio. Gestiona operaciones relacionadas con pacientes, actividades de visitas, y suministros médicos, integrándose con un API Gateway y un servicio de geocodificación. Soporta el registro, consulta, actualización y eliminación de datos para el portal web y la app móvil en la gestión de visitas domiciliarias.

## Funcionalidades
- **Gestión de Pacientes**: Registro (`POST /pacientes/registrar-paciente`), consulta paginada (`GET /pacientes`), actualización (`PUT /pacientes/actualizar-paciente/{id}`), y obtención por ID (`GET /pacientes/{id}`).
- **Geocoding**: Obtiene coordenadas de direcciones mediante integración con un servicio externo (`http://coordenadas:8086/geocode`).
- **Actividades de Visitas**: Registro (`POST /actividad-paciente-visita/registrar`), consulta (`GET /actividad-paciente-visita/listar`, `GET /actividad-paciente-visita/por-documento/{documento}`), actualización (`PUT`), y eliminación (`DELETE`).
- **Suministros Médicos**: Registro (`POST /insumos/registrar-instalacion`, `POST /insumos/agregar`), consulta de inventario (`GET /insumos/inventario-paciente/{pacienteId}`), medicamentos (`GET /insumos/medicamentos/{id}`), y actualización (`PUT /insumos/actualizar/{id}`).
- **Datos Auxiliares**: Consulta de tipos de identificación (`GET /pacientes/tipos-identificacion`), tipos de actividad (`GET /pacientes/tipos-actividad`), localidades (`GET /pacientes/localidades`), y barrios (`GET /pacientes/barrios/{codigoLocalidad}`).

## Tecnologías
- **Framework**: Spring Boot
- **Lenguaje**: Java 17
- **Base de Datos**: PostgreSQL
- **Dependencias Clave**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `postgresql`
  - `spring-boot-starter-test`
- **Integraciones**: API Gateway, servicio de geocodificación

## Requisitos
- Java 17
- Maven 3.8+
- PostgreSQL
- API Gateway activo
- Servicio de geocodificación (`http://coordenadas:8086`)
- Archivo `application.properties`:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:8000/careconnect
  spring.datasource.username=your_user
  spring.datasource.password=your_password
  server.port=8081
  ```

## Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/careconnect/back-sv-patients.git
   cd back-sv-patients
   ```

2. Configurar `application.properties` con las credenciales de la base de datos.

3. Compilar e instalar dependencias:
   ```bash
   mvn clean install
   ```

4. Iniciar el servicio:
   ```bash
   mvn spring-boot:run
   ```

   Disponible en `http://localhost:8081`, accesible vía API Gateway.

## Uso
- **Endpoints** (prefijo `/pacientes`, `/insumos`, o `/actividad-paciente-visita`):
  - Registrar paciente: `POST http://localhost:8080/api/pacientes/registrar-paciente`
  - Consultar inventario: `GET http://localhost:8080/api/insumos/inventario-paciente/{pacienteId}`
  - Registrar actividad: `POST http://localhost:8080/api/actividad-paciente-visita/registrar`
- **Autenticación**: Gestionada por el API Gateway (e.g., JWT).
- **Ejemplo**:
  ```bash
  curl -X POST "http://localhost:8080/api/pacientes/registrar-paciente" -H "Content-Type: application/json" -d '{"nombre": "Juan", "numero_identificacion": "123456", "tipoIdentificacion": {"id": 1}, "direccion": "Calle 123", "barrio": "Usaquén", "conjunto": "Torre 1"}'
  ```

## Autoría
- Juan David González
- Lina María Salamanca
- Laura Alexandra Rodríguez
- Axel Nicolás Caro

**Pontificia Universidad Javeriana**  
**Mayo 26, 2025**