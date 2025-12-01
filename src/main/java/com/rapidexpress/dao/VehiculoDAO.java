package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.EstadoVehiculo;
import com.rapidexpress.model.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VehiculoDAO {
    
    public boolean registrar(Vehiculo vehiculo){
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio_fabricacion, capacidad_carga_kg, estado) VALUES (?, ?, ?, ?, ?, ?,)";
        
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnioFabricacion());
            stmt.setDouble(5, vehiculo.getCapacidadCargaKg());
            //Guardar el Enum como String
            stmt.setString(6, vehiculo.getEstado().name());
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // true si guardo correctamente
            
        } catch (Exception e) {
            System.err.println("Error al registrar vehiculo: " + e.getMessage());
            return false;
        }
    }
    
    // Listar: todos los camiones para mostrarlos en pantalla
    public List<Vehiculo> listarTodos(){
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
           
            //Se reconstruye el objeto con los datos de SQL
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setId(rs.getInt("id"));
                v.setPlaca(rs.getString("placa"));
                v.setMarca(rs.getString("marca"));
                v.setModelo(rs.getString("modelo"));
                v.setAnioFabricacion(rs.getInt("anio_fabricacion"));
                v.setCapacidadCargaKg(rs.getDouble("capacidad_carga_kg"));
                // convertir a Enum
                v.setEstado(EstadoVehiculo.valueOf(rs.getString("estado")));
                
                lista.add(v);
            }
        } catch (SQLException e){
            System.err.println("Eror al listar vehiculos: " + e.getMessage());
        }
        return lista;       
    }
    
    // Buscar por placa para no tener duplicados
    public Vehiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM vehiculos WHERE placa = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("anio_fabricacion"),
                    rs.getDouble("capacidad_carga_kg"),
                    EstadoVehiculo.valueOf(rs.getString("estado"))
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar vehículo: " + e.getMessage());
        }
        return null; // Si no existe, retorna null
    }

    // Para cuando el camión sale a ruta o toca que vaya al taller
    public boolean actualizarEstado(int idVehiculo, EstadoVehiculo nuevoEstado) {
        String sql = "UPDATE vehiculos SET estado = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoEstado.name());
            stmt.setInt(2, idVehiculo);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
}