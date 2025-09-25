-- Tabla principal de usuarios
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    birth_date DATE
);

-- Roles
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Relación muchos a muchos user <-> role
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Administradores
CREATE TABLE administrators (
    user_id BIGINT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Clientes
CREATE TABLE clients (
    user_id BIGINT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    loyalty_points INT DEFAULT 0,
    payment_info VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Empleados
CREATE TABLE employees (
    user_id BIGINT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    hire_date DATE,
    salary NUMERIC(15,2),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Propietarios
CREATE TABLE owners (
    user_id BIGINT PRIMARY KEY,
    full_name VARCHAR(150) NOT NULL,
    tax_id VARCHAR(50) UNIQUE,
    contact_info VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
