package com.rapidexpress.controller;

import com.rapidexpress.dao.ConductorDAO;
import com.rapidexpress.dao.RutaDAO;
import com.rapidexpress.dao.VehiculoDAO;
import com.rapidexpress.model.Conductor;
import com.rapidexpress.model.EstadoConductor;
import com.rapidexpress.model.EstadoRuta;
import com.rapidexpress.model.EstadoVehiculo;
import com.rapidexpress.model.Ruta;
import com.rapidexpress.model.Vehiculo;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RutaController {
    private final RutaDAO rutaDAO;
    private final VehiculoDAO vehiculoDAO;
    private final ConductorDAO conductorDAO;
    private final Scanner scanner;

    public RutaController() {
        this.rutaDAO = new RutaDAO();
        this.vehiculoDAO = new VehiculoDAO();
        this.conductorDAO = new ConductorDAO();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE RUTAS LOGÍSTICAS ---");
            System.out.println("1. Crear Nueva Ruta (Despacho)");
            System.out.println("2. Listar Historial de Rutas");
            System.out.println("3. Finalizar Ruta (Retorno a Base)");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    crearRuta();
                    break;
                case 2:
                    listarRutas();
                    break;
                case 3:
                    finalizarRuta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("⚠️ Opción inválida.");
            }
        }
    }
    
    // CREAR RUTA 
    private void crearRuta() {
        System.out.println("\n>> CREACIÓN DE NUEVA HOJA DE RUTA");

        // Selecionamos el vehiculo disponible
        // Usamos Streams de Java para filtrar la lista rapido
        List<Vehiculo> disponibles = vehiculoDAO.listarTodos().stream()
                .filter(v -> v.getEstado() == EstadoVehiculo.DISPONIBLE)
                .collect(Collectors.toList());

        if (disponibles.isEmpty()) {
            System.out.println("Error: No hay vehículos DISPONIBLES en la flota.");
            return;
        }

        System.out.println("--- Vehículos Disponibles ---");
        for (Vehiculo v : disponibles) {
            System.out.printf("ID: %d | Placa: %s | Capacidad: %.1f Kg%n", 
                    v.getId(), v.getPlaca(), v.getCapacidadCargaKg());
        }
        
        System.out.print("Ingrese el ID del Vehículo a asignar: ");
        int vehiculoId = leerEntero();
        
        // Validar que el ID elegido sea valido y este disponible
        Vehiculo vSeleccionado = vehiculoDAO.buscarPorPlaca( 
                disponibles.stream().filter(v -> v.getId() == vehiculoId).findFirst().map(Vehiculo::getPlaca).orElse("") 
        );

        if (vSeleccionado == null || vSeleccionado.getEstado() != EstadoVehiculo.DISPONIBLE) {
            System.out.println("Selección inválida.");
            return;
        }

        // Seleccionamos un conductor activo
        List<Conductor> activos = conductorDAO.listarTodos().stream()
                .filter(c -> c.getEstado() == EstadoConductor.ACTIVO)
                .collect(Collectors.toList());

        if (activos.isEmpty()) {
            System.out.println("Error: No hay conductores ACTIVOS.");
            return;
        }

        System.out.println("\n--- Conductores Activos ---");
        for (Conductor c : activos) {
            System.out.printf("ID: %d | Nombre: %s | Licencia: %s%n", 
                    c.getId(), c.getNombreCompleto(), c.getTipoLicencia());
        }

        System.out.print("Ingrese el ID del Conductor a asignar: ");
        int conductorId = leerEntero();

        // Creamos la ruta desde aca
        Ruta nuevaRuta = new Ruta(vehiculoId, conductorId);
        
        if (rutaDAO.registrar(nuevaRuta)) {
            System.out.println("¡Ruta creada exitosamente!");
            System.out.println("El vehículo ha cambiado a estado EN_RUTA automáticamente.");
            
            // AQUÍ LLAMAREMOS A LA ASIGNACIÓN DE PAQUETES EN EL SIGUIENTE PASO!!!!!! 
            System.out.println("️RECUERDA: Ahora debes asignar los paquetes a esta ruta.");
        } else {
            System.out.println("Error al crear la ruta.");
        }
    }

    // Listamos rutas
    private void listarRutas() {
        System.out.println("\n>> HISTORIAL DE RUTAS");
        List<Ruta> lista = rutaDAO.listarTodas();
        
        if (lista.isEmpty()) {
            System.out.println("No hay rutas registradas.");
            return;
        }

        System.out.printf("%-5s %-10s %-10s %-20s %-15s%n", "ID", "VEHICULO", "CONDUCTOR", "FECHA INICIO", "ESTADO");
        System.out.println("----------------------------------------------------------------------");
        
        for (Ruta r : lista) {
            System.out.printf("%-5d %-10d %-10d %-20s %-15s%n", 
                    r.getId(), r.getVehiculoId(), r.getConductorId(), 
                    r.getFechaInicio().toString().substring(0, 16).replace("T", " "), // Formato de fecha simple
                    r.getEstado());
        }
    }

    // AQui hacemos lo finalizacion de la ruta
    private void finalizarRuta() {
        System.out.println("\n>> FINALIZAR RUTA (Llegada a Base)");
        System.out.print("Ingrese el ID de la Ruta a cerrar: ");
        int idRuta = leerEntero();

        // Buscamos la ruta en la lista (simplificado)
        List<Ruta> rutas = rutaDAO.listarTodas();
        Ruta rutaAProcesar = null;
        for (Ruta r : rutas) {
            if (r.getId() == idRuta) {
                rutaAProcesar = r;
                break;
            }
        }

        if (rutaAProcesar == null) {
            System.out.println("Ruta no encontrada.");
            return;
        }

        if (rutaAProcesar.getEstado() == EstadoRuta.FINALIZADA) {
            System.out.println("Esta ruta ya estaba finalizada.");
            return;
        }

        if (rutaDAO.finalizarRuta(idRuta, rutaAProcesar.getVehiculoId())) {
            System.out.println("Ruta finalizada. El vehículo ahora está DISPONIBLE nuevamente.");
        } else {
            System.out.println("Error al finalizar ruta.");
        }
    }

    // Para leer enteros sin que explote el Scanner
    private int leerEntero() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
