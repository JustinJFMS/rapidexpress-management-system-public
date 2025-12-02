package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.Conductor;
import com.rapidexpress.model.EstadoConductor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {
    
    // === Registrar nuevo conductor ===
    public boolean registrar(Conductor conductor) {
        
        String sql = "INSERT INTO conductores (identificacion, nombre_completo, tipo_licencia, telefono, estado) VALUES (?, ?, ?, ?, ?)";  // Consulta SQL de insersion
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            // Asignacion de valores del objeto a los parametros de la consulta
            stmt.setString(1, conductor.getIdentificacion());
            stmt.setString(2, conductor.getNombreCompleto());
            stmt.setString(3, conductor.getTipoLicencia());
            stmt.setString(4, conductor.getTelefono());
            stmt.setString(5, conductor.getEstado().name()); 
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // retorna true si al menos una fila fue insertada correctamente.
            
        } catch (SQLException e){
            System.err.println("[ERROR] No se puedo registrar el conductor: " + e.getMessage());
            return false;
        }
    }
    
    // === Listar todos ===
    public List<Conductor> listarTodos(){
        
        List<Conductor> lista = new ArrayList<>();  // Lista para almacenar los conductores 
        
        String sql = "SELECT * FROM conductores"; // Consulta SQL que trae todos los registros de la tabla conductores
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Conductor c = new Conductor();
                
                c.setId(rs.getInt("id"));
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombreCompleto(rs.getString("nombre_completo"));
                c.setTipoLicencia(rs.getString("tipo_licencia"));
                c.setTelefono(rs.getString("telefono"));
                c.setEstado(EstadoConductor.valueOf(rs.getString("estado"))); // convierte el texto en enum
                
                lista.add(c);
            }
        } catch (SQLException e){
            System.err.println("[ERROR] Error al listar conductores: " + e.getMessage());
        }
        return lista;
    }
    
    // === Buscar por identificacion ===
    public Conductor buscarPorIdentificacion(String identificacion) {
        
        String sql = "SELECT * FROM conductores WHERE identificacion = ?";  // Consulta SQL para encontrar un conductor segun su identificacion
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, identificacion);  // Asignacion de valor de la identificacion al parametro del query
            
            ResultSet rs = stmt.executeQuery();  // Ejecucion de la consulta
            
            if(rs.next()) {
                return new Conductor(
                    rs.getInt("id"),
                    rs.getString("identificacion"),
                    rs.getString("nombre_completo"),
                    rs.getString("tipo_licencia"),
                    rs.getString("telefono"),
                    EstadoConductor.valueOf(rs.getString("estado"))
                );
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al buscar el conductor: " + e.getMessage());
        }
        return null;
    }
    
    // === Actualizar estado ===
    public boolean actualizarEstado(int idConductor, EstadoConductor nuevoEstado){
        String sql = "UPDATE conductores SET estado = ? WHERE id = ?"; // Consulta SQL para actualizar su estado por su Id
        
        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, nuevoEstado.name()); // asigna el nuevo estado al primer parametro query
            stmt.setInt(2, idConductor); // asigna el id al segundo parametro
            
            return stmt.executeUpdate() > 0; // return true si al menos una fila se modifico
        } catch (SQLException e) {
            System.err.println("[ERROR] Error al actualizar estado del conductor: " + e.getMessage());
            return false;
        }
    }
}
