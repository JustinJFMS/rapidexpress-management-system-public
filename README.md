# RapidExpress Management System (CLI)

Sistema de gestión logística backend desarrollado en Java. Permite administrar flotas, conductores, paquetería y rutas de distribución mediante una interfaz de consola robusta.

##  Tecnologías
* *Lenguaje:* Java 17+
* *Build Tool:* Maven
* *Base de Datos:* MySQL
* *Arquitectura:* MVC (Modelo - Vista - Controlador) + DAO Pattern
* *Control de Versiones:** Git / GitHub

##  Funcionalidades Principales

* *Registro, consulta, edición y eliminación de **conductores**
* *Administración de **estados del conductor**
* *Gestión de **rutas** y estados de ruta
* *Manejo de **flotas** y vehículos
* *CRUD de **envíos y paquetería**
* *Menús interactivos por consola
* *Conexión segura a MySQL mediante clase centralizada
* *Estructura totalmente modular y mantenible



##  Instalación y Ejecución

1. *Base de Datos:*
   - Ejecute el script database/1_schema_ddl.sql en su cliente MySQL.
   - Ejecute el script database/2_data_dml.sql para cargar datos iniciales.
   - Configure sus credenciales en src/main/java/com/rapidexpress/config/DatabaseConnection.java.

2. *Ejecución:*
   - Compile el proyecto: mvn clean install
   - Ejecute la clase principal: com.rapidexpress.RapidexpressManagementSystem

## 👥 Autores
- [Justin Moreno] 
- [Ashly Perez]
- [Thubal Contreras]
- [Duban Marquez]
