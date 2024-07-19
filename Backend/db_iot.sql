CREATE DATABASE db_iot;

CREATE TABLE IF NOT EXISTS public.datos
(
    id bigserial NOT NULL,
    corriente real,
    potencia real,
    PRIMARY KEY (id)
);