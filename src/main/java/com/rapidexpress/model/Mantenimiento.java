package com.rapidexpress.model;

import java.time.LocalDateTime;

public class Mantenimiento {
    private int id;
    private int vehiculoId;
    private LocalDateTime fecha;
    private String descripcion;

    public Mantenimiento() {}

    public Mantenimiento(int vehiculoId, String descripcion) {
        this.vehiculoId = vehiculoId;
        this.descripcion = descripcion;
        this.fecha = LocalDateTime.now(); // Marca la hora exacta del reporte
    }
    
    // Getters y Setters
    public int getId() {
        return id; 
    }
    public void setId(int id) {
        this.id = id; 
    }
    public int getVehiculoId() {
        return vehiculoId; 
    }
    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId; 
    }
    public LocalDateTime getFecha() {
        return fecha; 
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha; 
    }
    public String getDescripcion() {
        return descripcion; 
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; 
    }
}
