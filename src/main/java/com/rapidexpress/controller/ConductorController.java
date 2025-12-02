/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rapidexpress.controller;

import com.rapidexpress.dao.ConductorDAO;
import com.rapidexpress.model.Conductor;
import com.rapidexpress.model.EstadoConductor;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author thuale
 */
public class ConductorController {
    
    private final ConductorDAO conductorDAO;
    private final Scanner scanner;
    
    public ConductorController() {
        this.conductorDAO = new ConductorDAO();
        this.scanner = new Scanner(System.in);
    }
    
    // --- MENU PRINCIPAL ---
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0){
            System.out.println("\n--- GESTION DE CONDUCTORES ---");
            System.out.println("1. Registrar Nuevo Conductor");
            System.out.println("2. Listar Personal");
            System.out.println("3. Actualizar Estado (Vacaciones/Despido)");
            System.out.println("0. Volver al Menu Principal");
            System.out.println("Seleccione una opion: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            
            switch (opcion) {
                case 1:
                    registrarConductor();
                    break;
                case 2:
                    listarConductores();
                    break;
                case 3:
                    actualizarEstadoConductor();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:System.out.println("Opcion invalida.");    
            }
        }
    }
    
    //REGISTRAR
    private void registrarConductor(){
        System.out.println("\n>> NUEVO CONDUCTOR");
        
        System.out.println("Ingrese Cedula/Indentificacion: ");
        String identificacion = scanner.nextLine();
        
        //toca validar si existe 
        if (conductorDAO.buscarPorIdentificacion(identificacion) != null) {
            System.out.println("Erro ya hay un conductor con esa identificacion.");
            return;
        }
        
        System.out.println("Nombre Completo: ");
        String nombre = scanner.nextLine();
        
        System.out.println("Tipo de licencia (ejemplo. B1, C2): ");
        String licencia = scanner.nextLine();
        
        System.out.println("Telefono de contacto: ");
        String telefono = scanner.nextLine();
        
        // Hacemos objeto temporal
        Conductor nuevo = new Conductor(identificacion, nombre, licencia, telefono);
        
        //enviar a la base de datos
        if (conductorDAO.registrar(nuevo)) {
            System.out.println(" Conductor registrado exitosamente!");
        } else {
            System.out.println("Error al guardar en base de datos.");
        }  
    }
    
    //Listar
    private void listarConductores() {
        System.out.println("\n>> PLANTILLA DE PERSONAL");
        List<Conductor> lista = conductorDAO.listarTodos();
        
        if (lista.isEmpty()) {
            System.out.println("No hay conductores registrados.");
        } else {
            // Tabla bonita con printf
            System.out.printf("%-5s %-15s %-25s %-10s %-15s%n", "ID", "CEDULA", "NOMBRE", "LICENCIA", "ESTADO");
            System.out.println("---------------------------------------------------------------------------");
            
            for (Conductor c : lista) {
                System.out.printf("%-5d %-15s %-25s %-10s %-15s%n", 
                        c.getId(), c.getIdentificacion(), c.getNombreCompleto(), c.getTipoLicencia(), c.getEstado());
            }
        }
    }

    //Actualizar el estado
    private void actualizarEstadoConductor() {
        System.out.println("\n>> ACTUALIZAR ESTADO CONDUCTOR");
        System.out.print("Ingrese la CÉDULA del conductor: ");
        String cedula = scanner.nextLine();

        Conductor c = conductorDAO.buscarPorIdentificacion(cedula);
        if (c == null) {
            System.out.println("No se encontró personal con esa cédula.");
            return;
        }

        System.out.println("Conductor: " + c.getNombreCompleto());
        System.out.println("Estado actual: " + c.getEstado());
        System.out.println("Seleccione nuevo estado:");
        System.out.println("1. ACTIVO (Reintegrar)");
        System.out.println("2. DE VACACIONES");
        System.out.println("3. INACTIVO (Despedir/Retirar)");
        
        int op = 0;
        try {
            op = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            op = 0;
        }

        EstadoConductor nuevoEstado = null;
        if (op == 1) nuevoEstado = EstadoConductor.ACTIVO;
        else if (op == 2) nuevoEstado = EstadoConductor.VACACIONES;
        else if (op == 3) nuevoEstado = EstadoConductor.INACTIVO;
        else {
            System.out.println("Opción inválida.");
            return;
        }

        if (conductorDAO.actualizarEstado(c.getId(), nuevoEstado)) {
            System.out.println("Estado actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar.");
        }
    }
}