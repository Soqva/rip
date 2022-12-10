--liquibase formatted sql
--changeSet s0qva:1

CREATE TABLE client
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(64)  NOT NULL UNIQUE,
    password VARCHAR(512) NOT NULL,
    banned   BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE role
(
    code        VARCHAR(64) PRIMARY KEY,
    name        VARCHAR(64)  NOT NULL UNIQUE,
    description VARCHAR(512) NOT NULL
);

CREATE TABLE client_role
(
    id        BIGSERIAL PRIMARY KEY,
    client_id BIGINT      NOT NULL REFERENCES client (id) ON DELETE CASCADE,
    role_code VARCHAR(64) NOT NULL REFERENCES role (code) ON DELETE CASCADE,
    UNIQUE (client_id, role_code)
);

CREATE TABLE task
(
    id        BIGSERIAL PRIMARY KEY,
    title     VARCHAR(512) NOT NULL,
    completed BOOLEAN      NOT NULL DEFAULT FALSE
);

CREATE TABLE client_task
(
    id        BIGSERIAL PRIMARY KEY,
    client_id BIGINT NOT NULL REFERENCES client (id) ON DELETE CASCADE,
    task_id   BIGINT NOT NULL REFERENCES task (id) ON DELETE CASCADE,
    UNIQUE (client_id, task_id)
);
