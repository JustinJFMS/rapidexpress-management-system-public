package com.rapidexpress.controller;

import com.rapidexpress.dao.PaqueteDAO;
import com.rapidexpress.model.Paquete;
import java.util.List;
import java.util.Scanner;

public class PaqueteController {
    private final PaqueteDAO paqueteDAO;
    private final Scanner scanner;

    public PaqueteController() {
        this.paqueteDAO = new PaqueteDAO();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE PAQUETERÍA ---");
            System.out.println("1. Recibir/Registrar Paquete");
            System.out.println("2. Buscar por Tracking ID (Rastreo)");
            System.out.println("3. Ver Inventario en Bodega");
            System.out.println("4. Listar Todos los Paquetes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    registrarPaquete();
                    break;
                case 2:
                    rastrearPaquete();
                    break;
                case 3:
                    listarBodega();
                    break;
                case 4:
                    listarTodos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    //REgistrar
    private void registrarPaquete() {
        System.out.println("\n>> REGISTRO DE NUEVO ENVÍO");
        
        System.out.print("Descripción del contenido: ");
        String descripcion = scanner.nextLine();
        
        double peso = 0;
        try {
            System.out.print("Peso (Kg): ");
            peso = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido.");
            return;
        }

        System.out.print("Dirección Origen: ");
        String dirOrigen = scanner.nextLine();
        
        System.out.print("Dirección Destino: ");
        String dirDestino = scanner.nextLine();
        
        System.out.print("Nombre Remitente: ");
        String remitente = scanner.nextLine();
        
        System.out.print("Nombre Destinatario: ");
        String destinatario = scanner.nextLine();

        // Creamos el paquete aqui se genera el Id del constructor
        Paquete nuevoPaquete = new Paquete(descripcion, peso, dirOrigen, dirDestino, remitente, destinatario);

        if (paqueteDAO.registrar(nuevoPaquete)) {
            System.out.println("¡Paquete registrado con éxito!");
            System.out.println("=========================================");
            System.out.println("TRACKING ID GENERADO: " + nuevoPaquete.getTrackingId());
            System.out.println("=========================================");
            System.out.println("Por favor, entregue este código al cliente.");
        } else {
            System.out.println("Error al registrar en base de datos.");
        }
    }

    private void rastrearPaquete() {
        System.out.print("\nIngrese el Tracking ID a buscar: ");
        String trackingId = scanner.nextLine();

        Paquete p = paqueteDAO.buscarPorTrackingId(trackingId);
        
        if (p != null) {
            System.out.println("\n--- 🔎 DETALLE DEL ENVÍO ---");
            System.out.println("Tracking ID: " + p.getTrackingId());
            System.out.println("Estado:      " + p.getEstado()); // Para ver el estado
            System.out.println("Contenido:   " + p.getDescripcion());
            System.out.println("Origen:      " + p.getDireccionOrigen());
            System.out.println("Destino:     " + p.getDireccionDestino());
            if (p.getRutaId() > 0) {
                System.out.println("Asignado a Ruta #: " + p.getRutaId());
            } else {
                System.out.println("Ruta:  Pendiente de asignación");
            }
        } else {
            System.out.println("No se encontró ningún paquete con ese ID.");
        }
    }

    // Listar pendientes en bodega
    private void listarBodega() {
        System.out.println("\n>> PAQUETES EN BODEGA (Pendientes de Ruta)");
        List<Paquete> lista = paqueteDAO.listarEnBodega();
        mostrarTabla(lista);
    }

    // Lista todos los paquetes como historial
    private void listarTodos() {
        System.out.println("\n>> HISTORIAL COMPLETO DE PAQUETES");
        List<Paquete> lista = paqueteDAO.listarTodos();
        mostrarTabla(lista);
    }

    // Es como la plantilla que nos ayuda a mostrar todo de manera odenada
    private void mostrarTabla(List<Paquete> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay paquetes para mostrar.");
            return;
        }
        
        System.out.printf("%-15s %-20s %-10s %-15s %-15s%n", "TRACKING ID", "DESCRIPCION", "PESO", "ESTADO", "DESTINO");
        System.out.println("---------------------------------------------------------------------------------");
        for (Paquete p : lista) {
            System.out.printf("%-15s %-20s %-10.1f %-15s %-15s%n", 
                    p.getTrackingId(), 
                    recortar(p.getDescripcion(), 20),
                    p.getPesoKg(), 
                    p.getEstado(),
                    recortar(p.getDireccionDestino(), 15));
        }
    }
    
    // Para cortar strings largos en la tabla solo agrega (...)
    private String recortar(String texto, int largo) {
        if (texto.length() > largo) return texto.substring(0, largo - 3) + "...";
        return texto;
    }
}
