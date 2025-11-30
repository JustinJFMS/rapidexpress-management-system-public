/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rapidexpress.dao;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.model.Rol;
import com.rapidexpress.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario login(String username, String password) {
        
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ? AND estado = 'ACTIVO'";
        
        // Usamos try-with-resources para que la conexión se cierre automáticamente
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //Rellenamos los huecos (?) con los datos reales
            stmt.setString(1, username);
            stmt.setString(2, password);

            //Ejecutamos la consulta y obtenemos los resultados
            ResultSet rs = stmt.executeQuery();

            //Verificamos si la base de datos devolvió una fila
            if (rs.next()) {
                
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setNombreCompleto(rs.getString("nombre_completo"));
                usuario.setEmail(rs.getString("email"));
                usuario.setEstado(rs.getString("estado"));
                
                
                String rolTexto = rs.getString("rol");
                usuario.setRol(Rol.valueOf(rolTexto)); 

                return usuario; 
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar login: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: El rol en la base de datos no coincide con los del sistema.");
        }

        return null; 
    }
}
