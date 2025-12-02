package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.EstadoRuta;
import com.rapidexpress.model.Ruta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RutaDAO {
    
    // Registro de ruta , actualiza el estado de la ruta y si algo falla hace un Rollback
    public boolean registrar(Ruta ruta){
        String sqlInsert = "INSERT INTO rutas(vehiculo_id, conductor_id, fecha_inicio, estado) VALUES (?,?,?,?)";
        String sqlUpdateVehiculo = "UPDATE vehiculos SET estado = 'EN_RUTA' WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement stmtInsert = null;
        PreparedStatement stmtUpdate = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Se desactiva el guardado automatico
            conn.setAutoCommit(false);
            
            // RETURN _GENERATED_KEYS recupera el ID que MySQL le ponga a la ruta
            stmtInsert = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            stmtInsert.setInt(1, ruta.getVehiculoId());
            stmtInsert.setInt(2, ruta.getConductorId());
            
            // Conversion de LocalDateTime a Timestamp (de java a mysql)
            stmtInsert.setTimestamp(3, Timestamp.valueOf(ruta.getFechaInicio()));
            stmtInsert.setString(4, ruta.getEstado().name());
            
            int filasRuta = stmtInsert.executeUpdate();
            
            // Recuperar el ID generado
            try (ResultSet generatedKeys = stmtInsert.getGeneratedKeys()) {
                if (generatedKeys.next()){
                    ruta.setId(generatedKeys.getInt(1));
                }
            }
            
            // Poner el vehiculo en ruta
            stmtUpdate = conn.prepareStatement(sqlUpdateVehiculo);
            stmtUpdate.setInt(1, ruta.getVehiculoId());
            int filasVehiculo = stmtUpdate.executeUpdate();
            
            // Verificamos que todo esta bien y guardamos
            if (filasRuta > 0 && filasVehiculo > 0){
                conn.commit();
                return true;
            }else{
                conn.rollback();
                return false;
            }
                    
        } catch (SQLException e) {
            System.err.println("Error al crear la ruta: " + e.getMessage());
            if  (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Error en el rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            // Cierre de manera manual de los recursos que utilizamos
            try {
                if (stmtInsert != null )
                    stmtInsert.close();
                if (stmtUpdate != null )
                    stmtUpdate.close();
                if (conn != null){
                    conn.setAutoCommit(true); // Restaura los parametros de comportamiento por defecto
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Listar todas las rutas
    public List<Ruta> listarTodas() {
        List<Ruta> lista = new ArrayList<>();
        String sql = "SELECT * FROM rutas";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()){
            
            while (rs.next()) {
                Ruta r = new Ruta();
                r.setId(rs.getInt("id"));
                r.setVehiculoId(rs.getInt("vehiculo_id"));
                r.setConductorId(rs.getInt("conductor_id"));
                
                // Arriba convertimos de LocalDateTime a Timestamp pero aqui hacemos lo inverso
                r.setFechaInicio(rs.getTimestamp("fecha_inicio").toLocalDateTime());
                
                if (rs.getTimestamp("fecha_fin") != null) {
                    r.setFechaFin(rs.getTimestamp("fecha_fin").toLocalDateTime());
                }
                
                r.setEstado(EstadoRuta.valueOf(rs.getString("estado")));
                lista.add(r);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al listar rutas: " + e.getMessage());
        }
        return lista;
    }
    
    // Finalizamos la ruta y dejamos el camion libre
    public boolean finalizarRuta(int idRuta, int idVehiculo) {
        String sqlRuta = "UPDATE rutas SET fecha_fin = NOW(), estado = 'FINALIZADA' WHERE id = ?";
        String sqlVehiculo = "UPDATE vehiculos SET estado = 'DISPONIBLE' WHERE id = ?";
        
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);
            
            // Cerrar ruta
            try (PreparedStatement stmtRuta = conn.prepareStatement(sqlRuta)) {
                stmtRuta.setInt(1, idRuta);
                stmtRuta.executeUpdate();
            }
            
            // Liberar vehiculo
            try (PreparedStatement stmtVeh = conn.prepareStatement(sqlVehiculo)) {
                stmtVeh.setInt(1, idVehiculo);
                stmtVeh.executeUpdate();   
            }
            
            conn.commit();
            return true;
            
        } catch (SQLException e) {
            if (conn != null)
                try {
                conn.rollback();
            } catch (SQLException ex) {
            }
            return false;
        } finally {
            if (conn != null)
                try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
    
}
