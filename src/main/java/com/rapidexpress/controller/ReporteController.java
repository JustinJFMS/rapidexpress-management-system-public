package com.rapidexpress.controller;

import com.rapidexpress.dao.PaqueteDAO;
import com.rapidexpress.dao.RutaDAO;
import com.rapidexpress.dao.VehiculoDAO;
import com.rapidexpress.model.EstadoPaquete;
import com.rapidexpress.model.EstadoRuta;
import com.rapidexpress.model.EstadoVehiculo;
import com.rapidexpress.model.Paquete;
import com.rapidexpress.model.Ruta;
import com.rapidexpress.model.Vehiculo;
import java.util.List;
import java.util.Scanner;

public class ReporteController {
    private final PaqueteDAO paqueteDAO;
    private final RutaDAO rutaDAO;
    private final VehiculoDAO vehiculoDAO;
    private final Scanner scanner;

    public ReporteController() {
        this.paqueteDAO = new PaqueteDAO();
        this.rutaDAO = new RutaDAO();
        this.vehiculoDAO = new VehiculoDAO();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- MÓDULO DE REPORTES Y AUDITORÍA ---");
            System.out.println("1. Reporte de Estado de la Flota");
            System.out.println("2. Reporte de Operación (Paquetes)");
            System.out.println("3. Ver Rutas Activas (En curso)");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    reporteFlota();
                    break;
                case 2:
                    reportePaquetes();
                    break;
                case 3:
                    reporteRutasActivas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        }
    }
    
    private void reporteFlota() {
        System.out.println("\n>> ESTADO DE LA FLOTA");
        List<Vehiculo> flota = vehiculoDAO.listarTodos();
        
        long disponibles = flota.stream().filter(v -> v.getEstado() == EstadoVehiculo.DISPONIBLE).count();
        long enRuta = flota.stream().filter(v -> v.getEstado() == EstadoVehiculo.EN_RUTA).count();
        long enTaller = flota.stream().filter(v -> v.getEstado() == EstadoVehiculo.EN_MANTENIMIENTO).count();

        System.out.println("Total Vehículos:   " + flota.size());
        System.out.println("-----------------------------");
        System.out.println("Disponibles:    " + disponibles);
        System.out.println("En Ruta:        " + enRuta);
        System.out.println("En Taller:      " + enTaller);
    }
    
    private void reportePaquetes() {
        System.out.println("\n>> ESTADO DE PAQUETERÍA");
        List<Paquete> paquetes = paqueteDAO.listarTodos();

        long bodega = paquetes.stream().filter(p -> p.getEstado() == EstadoPaquete.EN_BODEGA).count();
        long transito = paquetes.stream().filter(p -> p.getEstado() == EstadoPaquete.EN_TRANSITO || p.getEstado() == EstadoPaquete.ASIGNADO).count();
        long entregados = paquetes.stream().filter(p -> p.getEstado() == EstadoPaquete.ENTREGADO).count();

        System.out.println("Total Paquetes:    " + paquetes.size());
        System.out.println("-----------------------------");
        System.out.println("En Bodega:      " + bodega);
        System.out.println("En Tránsito:    " + transito);
        System.out.println("Entregados:     " + entregados);
    }
    
    private void reporteRutasActivas() {
        System.out.println("\n>> RUTAS ACTIVAS AL MOMENTO");
        List<Ruta> rutas = rutaDAO.listarTodas();
        
        boolean hayActivas = false;
        System.out.printf("%-5s %-15s %-10s%n", "ID", "FECHA SALIDA", "VEHICULO ID");
        System.out.println("----------------------------------------");
        
        for (Ruta r : rutas) {
            if (r.getEstado() == EstadoRuta.ACTIVA) {
                hayActivas = true;
                System.out.printf("%-5d %-15s %-10d%n", 
                        r.getId(), 
                        r.getFechaInicio().toString().substring(0, 10), // Solo fecha
                        r.getVehiculoId());
            }
        }
        
        if (!hayActivas) {
            System.out.println("(No hay rutas activas en este momento)");
        }
    }
}
