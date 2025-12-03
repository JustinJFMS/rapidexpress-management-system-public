package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.EstadoPaquete;
import com.rapidexpress.model.Paquete;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class PaqueteDAO {
    //Registrarr paquete nuevo
    public boolean registrar(Paquete paquete) {
        String sql = "INSERT INTO paquetes (tracking_id, descripcion, peso_kg, direccion_origen, direccion_destino, remitente, destinatario, estado, ruta_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paquete.getTrackingId());
            stmt.setString(2, paquete.getDescripcion());
            stmt.setDouble(3, paquete.getPesoKg());
            stmt.setString(4, paquete.getDireccionOrigen());
            stmt.setString(5, paquete.getDireccionDestino());
            stmt.setString(6, paquete.getRemitente());
            stmt.setString(7, paquete.getDestinatario());
            stmt.setString(8, paquete.getEstado().name()); 

            // El paquete nace con valor 0 = null, aqui se hace manejo de null 
            if (paquete.getRutaId() == 0) {
                stmt.setNull(9, Types.INTEGER);
            } else {
                stmt.setInt(9, paquete.getRutaId());
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar paquete: " + e.getMessage());
            return false;
        }
    }

    // Busqueda por TrackingId
    public Paquete buscarPorTrackingId(String trackingId) {
        String sql = "SELECT * FROM paquetes WHERE tracking_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trackingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearPaquete(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar paquete: " + e.getMessage());
        }
        return null;
    }

    // Listar para el inventario
    public List<Paquete> listarTodos() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquetes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPaquete(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar paquetes: " + e.getMessage());
        }
        return lista;
    }
    
    // Listar todos
    public List<Paquete> listarEnBodega() {
        List<Paquete> lista = new ArrayList<>();
        String sql = "SELECT * FROM paquetes WHERE estado = 'EN_BODEGA'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearPaquete(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar bodega: " + e.getMessage());
        }
        return lista;
    }

    // Extrae los datos del ResultSet y crea el objeto Java fundamentos DRY padre
    private Paquete mapearPaquete(ResultSet rs) throws SQLException {
        Paquete p = new Paquete();
        p.setId(rs.getInt("id"));
        p.setTrackingId(rs.getString("tracking_id"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPesoKg(rs.getDouble("peso_kg"));
        p.setDireccionOrigen(rs.getString("direccion_origen"));
        p.setDireccionDestino(rs.getString("direccion_destino"));
        p.setRemitente(rs.getString("remitente"));
        p.setDestinatario(rs.getString("destinatario"));
        
        // Manejo de la ruta
        int rutaId = rs.getInt("ruta_id");
        if (rs.wasNull()) {
            p.setRutaId(0);
        } else {
            p.setRutaId(rutaId);
        }

        p.setEstado(EstadoPaquete.valueOf(rs.getString("estado")));
        return p;
    }
        // Asignaremos ruta
    public boolean asignarRuta(int idPaquete, int idRuta){
        // cambiamos el estado a ASIGNADO y guardamos el ID de la ruta
        String sql = "UPDATE paquetes SET ruta_id = ?, estado = 'ASIGNADO' WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRuta);
            stmt.setInt(2, idPaquete);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al asignar paquete: " + e.getMessage());
            return false;
        }
    }
    
    // Buscaremos por id
    public Paquete buscarPorId(int id) {
        String sql = "SELECT * FROM paquetes WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapearPaquete(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por ID: " + e.getMessage());
        }
        return null;
    }
}
