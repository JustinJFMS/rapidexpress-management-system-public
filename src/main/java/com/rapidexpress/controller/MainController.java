package com.rapidexpress.controller;

import com.rapidexpress.model.Usuario;
import java.util.Scanner;
import com.rapidexpress.model.Rol;
import com.rapidexpress.controller.RutaController;
import com.rapidexpress.controller.ReporteController;


public class MainController {
    
    private final Scanner scanner;
    
    public MainController() {
        this.scanner = new Scanner(System.in);
    }
    
    // metodo que decide que menu mostrar
    public void mostrarMenuPrincipal(Usuario usuario) {
        System.out.println("\n ************************** ");
        System.out.println(" PANEL DE CONTROL: "+ usuario.getRol());
        System.out.println("**************************");
        
        //Switch que muestra el menu dependiendo de la situacion
        switch (usuario.getRol()) {
            case ADMIN:
                menuAdmin();
                break;
            case OPERADOR:
                menuOperador();
                break;
            case AUXILIAR:
                menuAuxiliar();
                break;
            default:
                System.out.println("ERROR: Rol no encontrado");
        }
    }
    private void menuAdmin() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ ADMINISTRADOR ---");
            System.out.println("1. Gestionar Vehículos");
            System.out.println("2. Gestionar Conductores");
            System.out.println("3. Gestionar Paquetes");
            System.out.println("4. Gestionar Rutas");
            System.out.println("5. Reportes");
            System.out.println("6. Gestionar Usuarios (Crear Cuentas)");
            System.out.println("0. Salir / Cerrar Sesión");
            System.out.print("Seleccione una opción: ");
            
            // Validación básica para que no explote si meten letras
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.println("\n>> MÓDULO VEHÍCULOS");
                    VehiculoController vController = new VehiculoController();  
                    vController.mostrarMenu(); 
                    break;
                case 2:
                    System.out.println("\n>> MÓDULO CONDUCTORES");
                    ConductorController cController = new ConductorController();
                    cController.mostrarMenu();
                    break;
                case 3:
                    System.out.println("\n>> MÓDULO PAQUETES");
                    PaqueteController pController = new PaqueteController();
                    pController.mostrarMenu();
                    break;
                case 4:
                    System.out.println("\n>> MÓDULO RUTAS");
                    RutaController rController = new RutaController();
                    rController.mostrarMenu();
                    break;
                case 5:
                    System.out.println("\n>> MÓDULO REPORTES");
                    ReporteController repController = new ReporteController();
                    repController.mostrarMenu();
                    break;
                case 6:
                    System.out.println("\n>> MÓDULO DE USUARIOS");
                    UsuarioController uController = new UsuarioController();
                    uController.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Cerrando sesión...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void menuOperador() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ OPERADOR LOGÍSTICO ---");
            System.out.println("1. Gestionar Rutas (Despachos)");
            System.out.println("2. Gestionar Paquetes (Envíos)");
            System.out.println("3. Reportes Operativos");
            System.out.println("0. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    // Reutilizamos el controlador que ya creamos
                    RutaController rController = new RutaController();
                    rController.mostrarMenu();
                    break;
                case 2:
                    PaqueteController pController = new PaqueteController();
                    pController.mostrarMenu();
                    break;
                case 3:
                    ReporteController repController = new ReporteController();
                    repController.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Cerrando sesión de operador...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void menuAuxiliar() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MENÚ AUXILIAR DE FLOTA ---");
            System.out.println("1. Gestionar Estado de Vehículos (Mantenimiento)");
            System.out.println("2. Ver Reporte de Flota");
            System.out.println("0. Cerrar Sesión");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    // Usamos el menu de vehiculos para enviar a MANTENIMIENTO
                    VehiculoController vController = new VehiculoController();
                    vController.mostrarMenu();
                    break;
                case 2:
                    // Un reporte rapido solo para ver que carros estan para mantenimiento
                    ReporteController repController = new ReporteController();
                    repController.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Cerrando sesión de auxiliar...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
