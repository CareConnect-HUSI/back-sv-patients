
CREATE TABLE IF NOT EXISTS paciente (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    documento VARCHAR(50) UNIQUE NOT NULL,
    tipo_documento VARCHAR(10) NOT NULL,
    nombre_familiar VARCHAR(255),
    parentesco_familiar VARCHAR(100),
    telefono_familiar VARCHAR(20),
    barrio VARCHAR(100),
    conjunto VARCHAR(100), -- nullable
    latitud DOUBLE,
    longitud DOUBLE
);


CREATE TABLE IF NOT EXISTS tratamiento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    dosis VARCHAR(255) NOT NULL,
    dias_tratamiento INT NOT NULL,
    hora_inicio_tratamiento VARCHAR(10) NOT NULL,
    fecha_inicio VARCHAR(10) NOT NULL,
    fecha_fin VARCHAR(10) NOT NULL,
    duracion_visita VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS procedimiento (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    dosis VARCHAR(255) NOT NULL,
    frecuencia VARCHAR(255) NOT NULL,
    dias_tratamiento INT NOT NULL,
    fecha_inicio VARCHAR(10) NOT NULL,
    fecha_fin VARCHAR(10) NOT NULL,
    duracion_visita VARCHAR(10) NOT NULL,
    hora_medicamento VARCHAR(10) NOT NULL
);



