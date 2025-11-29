-- ==========================================
-- SCRIPT DE INICIALIZACIÓN: RapidExpress DB
-- ==========================================

DROP DATABASE IF EXISTS rapidexpress_db;
CREATE DATABASE rapidexpress_db;
USE rapidexpress_db;

-- 1. TABLA DE USUARIOS (ROLES)
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL, 
    nombre_completo VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    rol ENUM('ADMIN', 'OPERADOR', 'AUXILIAR') NOT NULL,
    estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO'
);

-- 2. TABLA DE VEHICULOS
CREATE TABLE vehiculos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio_fabricacion INT NOT NULL,
    capacidad_carga_kg DECIMAL(10,2) NOT NULL,
    estado ENUM('DISPONIBLE', 'EN_RUTA', 'EN_MANTENIMIENTO') DEFAULT 'DISPONIBLE'
);

-- 3. TABLA DE CONDUCTORES
CREATE TABLE conductores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    nombre_completo VARCHAR(100) NOT NULL,
    tipo_licencia VARCHAR(20) NOT NULL,
    telefono VARCHAR(20),
    estado ENUM('ACTIVO', 'VACACIONES', 'INACTIVO') DEFAULT 'ACTIVO'
);

-- 4. TABLA DE RUTAS
CREATE TABLE rutas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehiculo_id INT NOT NULL,
    conductor_id INT NOT NULL,
    fecha_inicio DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_fin DATETIME NULL,
    estado ENUM('ACTIVA', 'FINALIZADA') DEFAULT 'ACTIVA',
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
    FOREIGN KEY (conductor_id) REFERENCES conductores(id)
);

-- 5. TABLA DE PAQUETES
CREATE TABLE paquetes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tracking_id VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL,
    peso_kg DECIMAL(10,2) NOT NULL,
    direccion_origen VARCHAR(255) NOT NULL,
    direccion_destino VARCHAR(255) NOT NULL,
    remitente VARCHAR(100) NOT NULL,
    destinatario VARCHAR(100) NOT NULL,
    ruta_id INT NULL,
    estado ENUM('EN_BODEGA', 'ASIGNADO', 'EN_TRANSITO', 'ENTREGADO', 'DEVUELTO') DEFAULT 'EN_BODEGA',
    FOREIGN KEY (ruta_id) REFERENCES rutas(id)
);

-- 6. HISTORIAL DE MANTENIMIENTO
CREATE TABLE mantenimientos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    vehiculo_id INT NOT NULL,
    fecha_mantenimiento DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT,
    FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id)
);

-- DATOS INICIALES (SEMILLA)
INSERT INTO usuarios (username, password, nombre_completo, rol) VALUES 
('admin', '1234', 'Super Admin', 'ADMIN'),
('operador', '1234', 'Juan Operador', 'OPERADOR'),
('auxiliar', '1234', 'Pedro Mecanico', 'AUXILIAR');