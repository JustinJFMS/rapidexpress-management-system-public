package com.rapidexpress.model;

import java.util.UUID;

public class Paquete {
    private int id;
    private String trackingId; // El codigo de rastreo)
    private String descripcion;
    private double pesoKg;
    private String direccionOrigen;
    private String direccionDestino;
    private String remitente;
    private String destinatario;
    private int rutaId; //0 si no tiene ruta asignada
    private EstadoPaquete estado;

    // Constructor Vacío
    public Paquete() {
    }

    // Constructor para NUEVOS PAQUETES
    public Paquete(String descripcion, double pesoKg, String direccionOrigen, String direccionDestino, String remitente, String destinatario) {
        // Genera un código único corto 
        this.trackingId = "PKG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.estado = EstadoPaquete.EN_BODEGA; // Siempre nace en bodega
        this.rutaId = 0; // 0 es Sin ruta
    }

    //Constructor Completo para poder leer de la BD
    public Paquete(int id, String trackingId, String descripcion, double pesoKg, String direccionOrigen, String direccionDestino, String remitente, String destinatario, int rutaId, EstadoPaquete estado) {
        this.id = id;
        this.trackingId = trackingId;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.direccionOrigen = direccionOrigen;
        this.direccionDestino = direccionDestino;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.rutaId = rutaId;
        this.estado = estado;
    }

    // Getter Y Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTrackingId() {
        return trackingId; 
    }
    public void setTrackingId(String trackingId) { 
        this.trackingId = trackingId; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public double getPesoKg() { 
        return pesoKg; 
    }
    public void setPesoKg(double pesoKg) { 
        this.pesoKg = pesoKg; 
    }

    public String getDireccionOrigen() { 
        return direccionOrigen; 
    }
    public void setDireccionOrigen(String direccionOrigen) { 
        this.direccionOrigen = direccionOrigen; 
    }

    public String getDireccionDestino() { 
        return direccionDestino; 
    }
    public void setDireccionDestino(String direccionDestino) { 
        this.direccionDestino = direccionDestino; 
    }

    public String getRemitente() { 
        return remitente; 
    }
    public void setRemitente(String remitente) { 
        this.remitente = remitente; 
    }

    public String getDestinatario() { 
        return destinatario; 
    }
    public void setDestinatario(String destinatario) { 
        this.destinatario = destinatario; 
    }

    public int getRutaId() { 
        return rutaId; 
    }
    public void setRutaId(int rutaId) { 
        this.rutaId = rutaId; 
    }

    public EstadoPaquete getEstado() { 
        return estado; 
    }
    public void setEstado(EstadoPaquete estado) { 
        this.estado = estado; 
    }

    @Override
    public String toString() {
        return "Paquete [" + trackingId + "] " + descripcion + " -> " + estado;
    }
}
