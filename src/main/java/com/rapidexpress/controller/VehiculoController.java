package com.rapidexpress.controller;

import com.rapidexpress.dao.MantenimientoDAO;
import com.rapidexpress.dao.VehiculoDAO;
import com.rapidexpress.model.EstadoVehiculo;
import com.rapidexpress.model.Mantenimiento;
import com.rapidexpress.model.Vehiculo;
import java.util.List;
import java.util.Scanner;

public class VehiculoController {
    private final VehiculoDAO vehiculoDAO;
    private final Scanner scanner;
    
    public VehiculoController(){
        this.vehiculoDAO = new VehiculoDAO();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu(){
        int opcion = -1;
        while (opcion != 0){
            System.out.println("\n---  GESTIÓN DE VEHÍCULOS ---");
            System.out.println("1. Registrar Nuevo Vehículo");
            System.out.println("2. Listar Flota");
            System.out.println("3. Actualizar Estado (Mantenimiento/Ruta)");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }
            
            switch (opcion){
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    listarVehiculos();
                    break;
                case 3:
                    actualizarEstadoVehiculo();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void registrarVehiculo(){
        System.out.println("\n>> NUEVO VEHÍCULO");
        
        System.out.print("Ingrese Placa (ej. AAA-123): ");
        String placa = scanner.nextLine().toUpperCase();//Convertimos a mayus
        
        //Validación de existencia
        
        if (vehiculoDAO.buscarPorPlaca(placa) != null){
            System.out.println("Error: Ya existe un vehículo con esa placa.");
            return;
        }
        
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        
        int anio = 0;
        double capacidad = 0;
        
        try {
            System.out.print("Año de Fabricación: ");
            anio = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Capacidad de Carga (Kg): ");
            capacidad = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar números válidos para año y capacidad.");
            return;
        }
        
        // Creación de objeto temporal
        Vehiculo nuevo = new Vehiculo(placa, marca, modelo, anio, capacidad);
        
        // Lo mandamos a la BD
        if (vehiculoDAO.registrar(nuevo)){
            System.out.println("!Vehículo registrado exitosamente¡");
        }else {
            System.out.println("Error al guardar en base de datos.");
        }
    }
        
    private void listarVehiculos(){
        System.out.println("\n>> FLOTA ACTUAL");
        List<Vehiculo> lista = vehiculoDAO.listarTodos();

        if (lista.isEmpty()){
            System.out.println("No hay vehículos registrados.");
        }else{
            // Cabecera de tabla en formato prinf
            System.out.printf("%-5s %-10s %-15s %-15s %-10s %-15s%n", "ID", "PLACA", "MARCA", "MODELO", "KG", "ESTADO");
            System.out.println("---------------------------------------------------------------------------");

            for (Vehiculo v : lista){
                System.out.printf("%-5d %-10s %-15s %-15s %-10.1f %-15s%n", 
                    v.getId(), v.getPlaca(), v.getMarca(), v.getModelo(), v.getCapacidadCargaKg(), v.getEstado());
            }
        }
    }
        
    private void actualizarEstadoVehiculo(){
        System.out.println("\n>> ACTUALIZAR ESTADO");
        System.out.print("Ingrese la PLACA del vehículo a modificar: ");
        String placa = scanner.nextLine().toUpperCase();

        Vehiculo v = vehiculoDAO.buscarPorPlaca(placa);
        if(v == null){
            System.out.println("No se encontró ningún vehículo con esa placa.");
            return;
        }   
        
        System.out.println("Vehículo: " + v.getMarca() + " " + v.getModelo());
        System.out.println("Estado actual: " + v.getEstado());
        System.out.println("Seleccione nuevo estado:");
        System.out.println("1. DISPONIBLE");
        System.out.println("2. EN MANTENIMIENTO");

        int op = 0;
        try {
            op = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            op = 0;
        }

        EstadoVehiculo nuevoEstado = null;
        String descripcionMantenimiento = null;
            
        if (op == 1) {
            nuevoEstado = EstadoVehiculo.DISPONIBLE;
        } 
        else if (op == 2) {
            nuevoEstado = EstadoVehiculo.EN_MANTENIMIENTO;
            // --- AQUÍ ESTÁ EL ARREGLO ---
            System.out.print("Ingrese el motivo del mantenimiento (Descripción): ");
            descripcionMantenimiento = scanner.nextLine();
            if (descripcionMantenimiento.isEmpty()) descripcionMantenimiento = "Mantenimiento General";
        } 
        else {
            System.out.println("Opción inválida.");
            return;
        }

        // PRIMERO ACTUALIZAMOS EL ESTADO DEL VEHÍCULO
        if (vehiculoDAO.actualizarEstado(v.getId(), nuevoEstado)) {
            System.out.println("Estado del vehículo actualizado a: " + nuevoEstado);

            // GUARDAMOS HISTORIAL
            if (nuevoEstado == EstadoVehiculo.EN_MANTENIMIENTO) {
                MantenimientoDAO mantDao = new MantenimientoDAO();
                Mantenimiento registro = new Mantenimiento(v.getId(), descripcionMantenimiento);
                
                if (mantDao.registrar(registro)) {
                    System.out.println("Registro guardado en el historial de mantenimientos.");
                } else {
                    System.out.println("OJO: El estado cambió, pero falló el registro en el historial.");
                }
            }
        } else {
            System.out.println("Error al actualizar estado.");
        }
    }
}
