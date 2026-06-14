-- ============================================================
-- Script de base de datos: MediAgenda
-- Proyecto Final DAW - GT01 | Universidad de El Salvador
-- ============================================================

-- Secuencias explícitas compatibles con Hibernate GenerationType.IDENTITY
CREATE SEQUENCE IF NOT EXISTS pacientes_id_seq     START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS especialidades_id_seq START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS doctores_id_seq      START 1 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS citas_id_seq         START 1 INCREMENT 1;

-- Tabla: pacientes
-- Relacion: 1:N con citas (un paciente tiene muchas citas)
CREATE TABLE IF NOT EXISTS pacientes (
    id        BIGINT       DEFAULT nextval('pacientes_id_seq') PRIMARY KEY,
    nombre    VARCHAR(100) NOT NULL,
    telefono  VARCHAR(20),
    correo    VARCHAR(100)
);

-- Tabla: especialidades
-- Relacion: N:M con doctores (via tabla intermedia doctor_especialidad)
CREATE TABLE IF NOT EXISTS especialidades (
    id     BIGINT      DEFAULT nextval('especialidades_id_seq') PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL UNIQUE
);

-- Tabla: doctores
-- Relacion: 1:N con citas, N:M con especialidades
CREATE TABLE IF NOT EXISTS doctores (
    id       BIGINT       DEFAULT nextval('doctores_id_seq') PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- Tabla intermedia: doctor_especialidad (relacion N:M)
CREATE TABLE IF NOT EXISTS doctor_especialidad (
    doctor_id       BIGINT NOT NULL REFERENCES doctores(id)       ON DELETE CASCADE,
    especialidad_id BIGINT NOT NULL REFERENCES especialidades(id)  ON DELETE CASCADE,
    PRIMARY KEY (doctor_id, especialidad_id)
);

-- Tabla: citas
-- FK a pacientes (N:1) y a doctores (N:1)
CREATE TABLE IF NOT EXISTS citas (
    id           BIGINT      DEFAULT nextval('citas_id_seq') PRIMARY KEY,
    paciente_id  BIGINT      NOT NULL REFERENCES pacientes(id) ON DELETE CASCADE,
    doctor_id    BIGINT      NOT NULL REFERENCES doctores(id)  ON DELETE CASCADE,
    especialidad VARCHAR(80) NOT NULL,
    fecha_hora   TIMESTAMP   NOT NULL,
    estado       VARCHAR(20) DEFAULT 'PENDIENTE'
);

-- ============================================================
-- Datos iniciales de prueba
-- ============================================================

INSERT INTO especialidades (nombre) VALUES
    ('Medicina General'),
    ('Pediatría'),
    ('Cardiología'),
    ('Dermatología'),
    ('Neurología'),
    ('Traumatología')
ON CONFLICT (nombre) DO NOTHING;

INSERT INTO pacientes (nombre, telefono, correo) VALUES
    ('María López',     '7111-2222', 'maria@correo.com'),
    ('José Martínez',   '7333-4444', 'jose@correo.com'),
    ('Sofía Hernández', '7555-6666', 'sofia@correo.com')
ON CONFLICT DO NOTHING;

INSERT INTO doctores (nombre, telefono) VALUES
    ('Dr. Carlos Rivas',  '2222-1111'),
    ('Dra. Ana Flores',   '2222-3333'),
    ('Dr. Roberto Vega',  '2222-5555')
ON CONFLICT DO NOTHING;

INSERT INTO doctor_especialidad (doctor_id, especialidad_id) VALUES
    (1, 3),
    (1, 1),
    (2, 1),
    (3, 2)
ON CONFLICT DO NOTHING;
