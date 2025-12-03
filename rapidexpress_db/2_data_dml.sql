DELETE FROM usuarios WHERE id = 3;

USE rapidexpress_db;

-- =============================================
-- Insersion de usuarios
-- =============================================
INSERT INTO usuarios (username, password, nombre_completo, email, rol, estado) VALUES
('admin', '1234', 'Super Administrador', 'admin@rapidexpress.com', 'ADMIN', 'ACTIVO'),
('operador1', '1234', 'Carlos Operaciones', 'carlos@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('operador2', '1234', 'Maria Logistica', 'maria@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('operador3', '1234', 'Fernando Despacho', 'fernando@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('auxiliar1', '1234', 'Pedro Mecanico', 'pedro@rapidexpress.com', 'AUXILIAR', 'ACTIVO'),
('auxiliar2', '1234', 'Luisa Taller', 'luisa@rapidexpress.com', 'AUXILIAR', 'ACTIVO'),
('gerente', '1234', 'Roberto Gerencia', 'roberto@rapidexpress.com', 'ADMIN', 'ACTIVO'),
('analista1', '1234', 'Sofia Datos', 'sofia@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('conductor_lead', '1234', 'Miguel Jefe Flota', 'miguel@rapidexpress.com', 'AUXILIAR', 'ACTIVO'),
('auditor', '1234', 'Laura Auditoria', 'laura@rapidexpress.com', 'ADMIN', 'ACTIVO'),
('user11', '1234', 'Andres User', 'andres@rapidexpress.com', 'OPERADOR', 'INACTIVO'),
('user12', '1234', 'Valeria User', 'valeria@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('user13', '1234', 'Camilo User', 'camilo@rapidexpress.com', 'AUXILIAR', 'ACTIVO'),
('user14', '1234', 'Daniela User', 'daniela@rapidexpress.com', 'AUXILIAR', 'INACTIVO'),
('user15', '1234', 'Jorge User', 'jorge@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('user16', '1234', 'Tatiana User', 'tatiana@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('user17', '1234', 'Esteban User', 'esteban@rapidexpress.com', 'AUXILIAR', 'ACTIVO'),
('user18', '1234', 'Natalia User', 'natalia@rapidexpress.com', 'ADMIN', 'ACTIVO'),
('user19', '1234', 'Felipe User', 'felipe@rapidexpress.com', 'OPERADOR', 'ACTIVO'),
('user20', '1234', 'Gabriela User', 'gabriela@rapidexpress.com', 'AUXILIAR', 'ACTIVO');

-- =============================================
-- Insersion de vehiculos
-- =============================================
INSERT INTO vehiculos (placa, marca, modelo, anio_fabricacion, capacidad_carga_kg, estado) VALUES
('TZO-101', 'Chevrolet', 'NHR', 2020, 2500.00, 'EN_RUTA'),
('TZO-102', 'Chevrolet', 'NPR', 2021, 4500.00, 'EN_RUTA'),
('TZO-103', 'Hino', 'Dutro', 2019, 5000.00, 'EN_RUTA'),
('TZO-104', 'Kenworth', 'T800', 2018, 12000.00, 'EN_RUTA'),
('TZO-105', 'Foton', 'Aumark', 2022, 3000.00, 'EN_RUTA'),
('VHA-201', 'Renault', 'Kangoo', 2023, 800.00, 'DISPONIBLE'),
('VHA-202', 'Renault', 'Master', 2020, 1500.00, 'DISPONIBLE'),
('VHA-203', 'Chevrolet', 'N300', 2019, 700.00, 'EN_MANTENIMIENTO'),
('VHA-204', 'Chevrolet', 'NHR', 2018, 2500.00, 'DISPONIBLE'),
('VHA-205', 'Hino', '300 Series', 2021, 4000.00, 'DISPONIBLE'),
('WLX-301', 'International', 'DuraStar', 2017, 10000.00, 'EN_MANTENIMIENTO'),
('WLX-302', 'Foton', 'Miler', 2023, 2800.00, 'DISPONIBLE'),
('WLX-303', 'JAC', 'Urban', 2020, 3500.00, 'DISPONIBLE'),
('WLX-304', 'Volkswagen', 'Delivery', 2021, 4200.00, 'DISPONIBLE'),
('WLX-305', 'Mercedes', 'Atego', 2019, 8000.00, 'DISPONIBLE'),
('KJU-401', 'Isuzu', 'ELF', 2022, 3000.00, 'DISPONIBLE'),
('KJU-402', 'Volvo', 'VM', 2020, 15000.00, 'DISPONIBLE'),
('KJU-403', 'Scania', 'P320', 2021, 18000.00, 'DISPONIBLE'),
('KJU-404', 'Dongfeng', 'Duolika', 2019, 5000.00, 'EN_MANTENIMIENTO'),
('KJU-405', 'Hyundai', 'HD78', 2018, 4500.00, 'DISPONIBLE');

-- =============================================
-- Insersion de conductores
-- =============================================
INSERT INTO conductores (identificacion, nombre_completo, tipo_licencia, telefono, estado) VALUES
('1090100', 'Juan Perez', 'C2', '3001112233', 'ACTIVO'),
('1090101', 'Andres Gomez', 'C3', '3002223344', 'ACTIVO'),
('1090102', 'Martha Lopez', 'C2', '3003334455', 'ACTIVO'),
('1090103', 'Carlos Ruiz', 'C3', '3004445566', 'ACTIVO'),
('1090104', 'Diana Morales', 'B2', '3005556677', 'ACTIVO'),
('1090105', 'Pedro Sanchez', 'C2', '3006667788', 'VACACIONES'),
('1090106', 'Luis Fernandez', 'C3', '3007778899', 'ACTIVO'),
('1090107', 'Ana Torres', 'B1', '3008889900', 'ACTIVO'),
('1090108', 'Jose Ramirez', 'C2', '3009990011', 'ACTIVO'),
('1090109', 'Sofia Castillo', 'C1', '3001010101', 'ACTIVO'),
('1090110', 'Diego Vargas', 'C3', '3002020202', 'ACTIVO'),
('1090111', 'Lucia Mendoza', 'C2', '3003030303', 'INACTIVO'),
('1090112', 'Fernando Rios', 'C3', '3004040404', 'ACTIVO'),
('1090113', 'Gloria Paredes', 'B2', '3005050505', 'ACTIVO'),
('1090114', 'Hector Silva', 'C2', '3006060606', 'VACACIONES'),
('1090115', 'Isabel Castro', 'C1', '3007070707', 'ACTIVO'),
('1090116', 'Ricardo Peña', 'C3', '3008080808', 'ACTIVO'),
('1090117', 'Valeria Ortiz', 'C2', '3009090909', 'ACTIVO'),
('1090118', 'Manuel Guzman', 'C3', '3001212121', 'ACTIVO'),
('1090119', 'Patricia Leon', 'C2', '3001313131', 'ACTIVO');

-- =============================================
-- Insersion de rutas
-- =============================================
INSERT INTO rutas (vehiculo_id, conductor_id, fecha_inicio, fecha_fin, estado) VALUES
(1, 1, DATE_SUB(NOW(), INTERVAL 2 HOUR), NULL, 'ACTIVA'),
(2, 2, DATE_SUB(NOW(), INTERVAL 5 HOUR), NULL, 'ACTIVA'),
(3, 3, DATE_SUB(NOW(), INTERVAL 1 DAY), NULL, 'ACTIVA'),
(4, 4, DATE_SUB(NOW(), INTERVAL 30 MINUTE), NULL, 'ACTIVA'),
(5, 7, DATE_SUB(NOW(), INTERVAL 3 HOUR), NULL, 'ACTIVA'),
(6, 6, '2023-10-01 08:00:00', '2023-10-01 18:00:00', 'FINALIZADA'),
(7, 8, '2023-10-02 07:00:00', '2023-10-02 16:00:00', 'FINALIZADA'),
(8, 9, '2023-10-03 09:00:00', '2023-10-03 20:00:00', 'FINALIZADA'),
(9, 10, '2023-10-04 06:00:00', '2023-10-04 14:00:00', 'FINALIZADA'),
(10, 11, '2023-10-05 08:30:00', '2023-10-05 17:30:00', 'FINALIZADA'),
(11, 12, '2023-10-06 05:00:00', '2023-10-06 12:00:00', 'FINALIZADA'),
(12, 13, '2023-10-07 10:00:00', '2023-10-07 22:00:00', 'FINALIZADA'),
(13, 14, '2023-10-08 08:00:00', '2023-10-08 15:00:00', 'FINALIZADA'),
(14, 15, '2023-10-09 07:30:00', '2023-10-09 19:30:00', 'FINALIZADA'),
(15, 16, '2023-10-10 09:15:00', '2023-10-10 18:45:00', 'FINALIZADA'),
(16, 17, '2023-10-11 06:00:00', '2023-10-11 14:00:00', 'FINALIZADA'),
(17, 18, '2023-10-12 08:00:00', '2023-10-12 16:00:00', 'FINALIZADA'),
(18, 19, '2023-10-13 07:00:00', '2023-10-13 15:00:00', 'FINALIZADA'),
(19, 20, '2023-10-14 10:00:00', '2023-10-14 20:00:00', 'FINALIZADA'),
(20, 1, '2023-10-15 08:00:00', '2023-10-15 17:00:00', 'FINALIZADA');

-- =============================================
-- Insersion de paquetes
-- =============================================
INSERT INTO paquetes (tracking_id, descripcion, peso_kg, direccion_origen, direccion_destino, remitente, destinatario, ruta_id, estado) VALUES
('TRK-001', 'Caja de Repuestos', 15.5, 'Bogotá', 'Medellín', 'Autopartes SAS', 'Taller Central', 1, 'EN_TRANSITO'),
('TRK-002', 'Documentos Legales', 0.5, 'Bogotá', 'Medellín', 'Notaria 5', 'Juan Abogado', 1, 'EN_TRANSITO'),
('TRK-003', 'Electrodomésticos', 45.0, 'Cali', 'Buenaventura', 'Samsung Store', 'Cliente Final', 2, 'EN_TRANSITO'),
('TRK-004', 'Ropa Textil', 12.0, 'Medellín', 'Cartagena', 'Fabricato', 'Tienda Moda', 3, 'EN_TRANSITO'),
('TRK-005', 'Medicamentos', 5.0, 'Bogotá', 'Cúcuta', 'Laboratorios X', 'Droguería Y', 4, 'EN_TRANSITO'),
('TRK-006', 'Herramientas', 25.0, 'Bucaramanga', 'Cúcuta', 'Ferretería A', 'Obra B', 5, 'EN_TRANSITO'),
('TRK-007', 'Computadores', 10.0, 'Bogotá', 'Cali', 'Lenovo', 'Oficina Z', 6, 'ENTREGADO'),
('TRK-008', 'Muebles Oficina', 80.0, 'Medellín', 'Bogotá', 'Muebles SA', 'Empresa 1', 7, 'ENTREGADO'),
('TRK-009', 'Libros Educativos', 30.0, 'Bogotá', 'Barranquilla', 'Editorial Norma', 'Colegio 10', 8, 'ENTREGADO'),
('TRK-010', 'Zapatos Deportivos', 8.0, 'Cúcuta', 'Bogotá', 'Zapatería Real', 'Cliente Online', 9, 'ENTREGADO'),
('TRK-011', 'Juguetes', 12.0, 'Bogotá', 'Pasto', 'Toy Store', 'Niño Feliz', 10, 'ENTREGADO'),
('TRK-012', 'Café Exportación', 100.0, 'Armenia', 'Santa Marta', 'Finca Cafetera', 'Puerto', 11, 'ENTREGADO'),
('TRK-013', 'Celulares', 3.0, 'Bogotá', 'Villavicencio', 'Xiaomi', 'Tienda Movil', 12, 'ENTREGADO'),
('TRK-014', 'Insumos Médicos', 15.0, 'Medellín', 'Quibdó', 'Hospital X', 'Centro Salud', 13, 'ENTREGADO'),
('TRK-015', 'Artesanías', 5.0, 'Ráquira', 'Bogotá', 'Artesano', 'Galería', 14, 'ENTREGADO'),
('TRK-016', 'Bicicleta', 14.0, 'Bogotá', 'Tunja', 'Ciclo Ruta', 'Pedro Perez', NULL, 'EN_BODEGA'),
('TRK-017', 'Televisor 50"', 18.0, 'Cali', 'Popayán', 'LG Electronics', 'Maria Gomez', NULL, 'EN_BODEGA'),
('TRK-018', 'Colchón Doble', 25.0, 'Bogotá', 'Ibagué', 'Colchones Y', 'Hotel Z', NULL, 'EN_BODEGA'),
('TRK-019', 'Caja Sorpresa', 2.0, 'Medellín', 'Pereira', 'Regalos Ya', 'Cumpleañero', NULL, 'EN_BODEGA'),
('TRK-020', 'Motor Moto', 35.0, 'Bogotá', 'Neiva', 'Yamaha', 'Taller Sur', NULL, 'EN_BODEGA'),
('TRK-021', 'Guitarra', 4.0, 'Bogotá', 'Manizales', 'Instrumentos', 'Musico', NULL, 'EN_BODEGA'),
('TRK-022', 'Pinturas', 20.0, 'Medellín', 'Rionegro', 'Pintuco', 'Ferretería', NULL, 'EN_BODEGA'),
('TRK-023', 'Luces LED', 1.0, 'Bogotá', 'Sogamoso', 'Iluminación', 'Casa', NULL, 'EN_BODEGA'),
('TRK-024', 'Impresora 3D', 12.0, 'Cali', 'Palmira', '3D Tech', 'Maker', NULL, 'EN_BODEGA'),
('TRK-025', 'Dron Agrícola', 8.0, 'Bogotá', 'Yopal', 'DJI', 'Finca Arroz', NULL, 'EN_BODEGA');