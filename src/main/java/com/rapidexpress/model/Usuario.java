package com.rapidexpress.model;


public class Usuario {
    
    private int id;
    private String username;
    private String password;
    private String nombreCompleto;
    private String email;
    private Rol rol; 
    
    // se crea el atributo estado como String porque puede variar dependiendo de la clase, conductor, paquete, envio, vehiculo.
    private String estado; 

    // Constructor Vacío
    public Usuario() {
    }

    // Constructor 
    public Usuario(int id, String username, String password, String nombreCompleto, String email, Rol rol, String estado) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }
    
    // Constructor sin ID 
    public Usuario(String username, String password, String nombreCompleto, String email, Rol rol) {
        this.username = username;
        this.password = password;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.rol = rol;
        this.estado = "ACTIVO"; 
    }

    // Getters y Setters 
    // Tip: En NetBeans puedes generarlos con Alt + Insert -> Getters and Setters
    
    public int getId() { 
        return id; 
    }
    
    public void setId(int id) {
        this.id = id; 
    }

    public String getUsername() {
        return username; 
    }
    
    public void setUsername(String username) { 
        this.username = username;
    }

    public String getPassword() { 
        return password; 
    }
    
    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getNombreCompleto() { 
        return nombreCompleto; 
    }
    
    public void setNombreCompleto(String nombreCompleto) { 
        this.nombreCompleto = nombreCompleto; 
    }

    public String getEmail() {
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }

    public Rol getRol() { 
        return rol; 
    }
    
    public void setRol(Rol rol) { 
        this.rol = rol; 
    }

    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }

    @Override
    public String toString() {
        return "Usuario [ID=" + id + ", User=" + username + ", Nombre=" + nombreCompleto + ", Rol=" + rol + "]";
    }
}
