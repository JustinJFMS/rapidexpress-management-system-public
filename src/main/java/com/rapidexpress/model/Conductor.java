package com.rapidexpress.model;

public class Conductor {
    
    private int id;
    private String identificacion;
    private String nombreCompleto;
    private String tipoLicencia;
    private String telefono;
    private EstadoConductor estado;
    
    //Constructor vacio
    public Conductor(){    
    }
    
    // Constructor sin ID para crear nuevos conductores
    public Conductor(String identificacion, String nombreCompleto, String tipoLicencia, String telefono){
        this.identificacion =  identificacion;
        this.nombreCompleto = nombreCompleto;
        this.tipoLicencia = tipoLicencia;
        this.telefono = telefono;
        this.estado = EstadoConductor.ACTIVO;
    }
    
    // Constructor completo para leer de la DB
    public Conductor(int id, String identificacion, String nombreCompleto, String tipoLicencia, String telefono, EstadoConductor estado){
        this.id = id;
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.tipoLicencia = tipoLicencia;
        this.telefono = telefono;
        this.estado = estado;
    }
    
    // Getters y Setter
    public int getId() { 
        return id; 
    }
    public void setId (int id ){
        this.id = id; 
    }

    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto; 
    }
    public void setNombreCompleto (String nombreCompleto ) {
        this.nombreCompleto = nombreCompleto; 
    }
    
    public String getTipoLicencia() {
        return tipoLicencia; 
    }
    public void setTipoLicencia (String tipoLicencia ) {
        this.tipoLicencia = tipoLicencia; 
    }
    
    public String getTelefono() {
        return telefono; 
    }
    public void setTelefono (String telefono ) {
        this.telefono = telefono; 
    }
    
    public EstadoConductor getEstado() {
        return estado; 
    }
    public void setEstado (EstadoConductor estado) {
        this.estado = estado; 
    }
    
    @Override
    public String toString(){
        return nombreCompleto + "(Licencia: " + tipoLicencia + ")";
    }
    
}
