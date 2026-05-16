-- Script de creación de tabla principal: citas
-- Proyecto: MediAgenda
-- Autor: Oscar Manuel Peraza Velasquez - PV21001

CREATE TABLE citas (
    id               BIGSERIAL PRIMARY KEY,
    nombre_paciente  VARCHAR(100) NOT NULL,
    nombre_doctor    VARCHAR(100) NOT NULL,
    especialidad     VARCHAR(80)  NOT NULL,
    fecha_hora       TIMESTAMP    NOT NULL,
    estado           VARCHAR(20)  DEFAULT 'PENDIENTE'
);
