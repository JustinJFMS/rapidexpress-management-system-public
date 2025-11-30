package com.rapidexpress.model;

/**
 *
 * @author ashly
 */
public class Vehiculo {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int anioFabricacion;
    private double capacidadCargaKg;
    private EstadoVehiculo estado;
    
    
    // Constructor vacio
    public Vehiculo(){
        
    }
    
    
    // Constructor sin ID (para crear nuevos vehiculos)
    public Vehiculo(String placa, String marca, String modelo, int anioFabricacion, double capacidadCargaKg){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.capacidadCargaKg = capacidadCargaKg;
        this.estado = EstadoVehiculo.DISPONIBLE; // Por defecto esta disponible
    }
    
            
    // Constructor completo (con ID para leer de la BD)
    public Vehiculo(int id, String placa, String marca, String modelo, int anioFabricacion, double capacidadCargaKg, EstadoVehiculo estado) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.capacidadCargaKg = capacidadCargaKg;
        this.estado = estado;
    }
    
    // Getters y Setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id; 
    }
    
    public String getPlaca(){
        return placa;
    }
    
    public void setPlaca(String placa){
        this.placa = placa;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public int getAnioFabricacion(){
        return anioFabricacion;
    }
    
    public void setAnioFabricacion(int anioFabricacion){
        this.anioFabricacion = anioFabricacion;
    }
    
    public double getCapacidadCargaKg(){
        return capacidadCargaKg;
    }
    
    public void setCapacidadCargaKg(double capacidadCargaKg){
        this.capacidadCargaKg = capacidadCargaKg;
    }
    
    public EstadoVehiculo getEstado(){
        return estado;
    }
    
    public void setEstado(EstadoVehiculo estado){
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Vehiculo [" + "id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", anio fabricacion=" + anioFabricacion + ", capacidad carga (kg)=" + capacidadCargaKg + ", estado=" + estado + ']';
    }
    
}
