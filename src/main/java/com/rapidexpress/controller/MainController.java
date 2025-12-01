package com.rapidexpress.controller;

import com.rapidexpress.model.Usuario;
import java.util.Scanner;
import com.rapidexpress.model.Rol;


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
            System.out.println("\n1. Gestionar Vehículos");
            System.out.println("2. Gestionar Conductores");
            System.out.println("3. Gestionar Rutas");
            System.out.println("4. Reportes");
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
                    VehiculoController vController = new VehiculoController(); // ¡Ahora sí existe!
                    vController.mostrarMenu(); 
                    break;
                case 2:
                    System.out.println(">> Abriendo módulo de Conductores...");
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
        System.out.println("1. Gestionar Rutas y Paquetes");
        System.out.println("0. Salir");
        // Aquí implementarás la lógica del operador después
    }

    private void menuAuxiliar() {
        System.out.println("1. Registrar Mantenimiento");
        System.out.println("0. Salir");
        // Aquí implementarás la lógica del auxiliar después
    }
}
