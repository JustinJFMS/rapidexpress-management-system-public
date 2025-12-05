# RapidExpress Management System (CLI)

## 🔎 Qué es

RapidExpress es una aplicación de consola desarrollada en Java que sirve para gestionar operaciones logísticas: flotas, conductores, paquetería, rutas y envíos. Permite crear, consultar, actualizar y eliminar datos clave para administrar envíos de forma ordenada.

## 🛠️ Tecnologías

- Java 17+  
- Maven como herramienta de construcción  
- MySQL como base de datos  
- Patrón MVC + DAO para organización del código  

## ⚙️ Funcionalidades principales

- CRUD completo para conductores, vehículos (flota), rutas y envíos/paquetería  
- Gestión del estado de conductores y rutas  
- Menús interactivos por consola para navegación sencilla  
- Estructura modular orientada a mantenimiento y escalabilidad  

## 🚀 Cómo ejecutar

1. Crear la base de datos: ejecutar `database/1_schema_ddl.sql`.  
2. Cargar datos iniciales: ejecutar `database/2_data_dml.sql`.  
3. Configurar credenciales de la base de datos en `src/main/java/com/rapidexpress/config/DatabaseConnection.java`.  
4. Compilar el proyecto
