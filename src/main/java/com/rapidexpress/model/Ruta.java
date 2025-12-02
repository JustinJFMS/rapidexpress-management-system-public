package com.rapidexpress.model;

import java.time.LocalDateTime;

public class Ruta {
    
    private int id;
    private int vehiculoId;
    private int conductorId;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private EstadoRuta estado;
    
    // Creacion del constructor vacio
    public Ruta(){
    }
    
    // Constructor para crear una nueva ruta
    public Ruta(int vehiculoId, int conductorId, LocalDateTime fechaInicio, LocalDateTime fechaFin, EstadoRuta estado){
        this.id = id;
        this.vehiculoId = vehiculoId;
        this.conductorId = conductorId;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = null;
        this.estado = EstadoRuta.ACTIVA;
    }
    
    // Constructor completo desde la DB
    public Ruta(int id, int vehiculoId, int conductorId, LocalDateTime fechaInicio, LocalDateTime fechaFin, EstadoRuta estado) {
        this.id = id;
        this.vehiculoId = vehiculoId;
        this.conductorId = conductorId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
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

    public int getConductorId() {
        return conductorId;
    }
    public void setConductorId(int conductorId) {
        this.conductorId = conductorId;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public EstadoRuta getEstado() {
        return estado;
    }
    public void setEstado(EstadoRuta estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return "Ruta #" + "[Vehiculo =" + vehiculoId + " Conductor" + conductorId + "]" + estado; 
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
