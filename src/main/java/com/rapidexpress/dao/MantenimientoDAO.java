package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.Mantenimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MantenimientoDAO {
    public boolean registrar(Mantenimiento m) {
        String sql = "INSERT INTO mantenimientos (vehiculo_id, fecha_mantenimiento, descripcion) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, m.getVehiculoId());
            stmt.setTimestamp(2, Timestamp.valueOf(m.getFecha()));
            stmt.setString(3, m.getDescripcion());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar mantenimiento: " + e.getMessage());
            return false;
        }
    }
}
