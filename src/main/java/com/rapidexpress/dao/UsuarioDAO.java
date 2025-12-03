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
    
    public boolean registrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, password, nombre_completo, email, rol, estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getNombreCompleto());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getRol().name()); // Enum a String
            stmt.setString(6, "ACTIVO"); // Por defecto nace activo

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }
    
    public java.util.List<Usuario> listarTodos() {
        java.util.List<Usuario> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                // No traemos el password por seguridad al listar
                u.setNombreCompleto(rs.getString("nombre_completo"));
                u.setEmail(rs.getString("email"));
                u.setRol(Rol.valueOf(rs.getString("rol")));
                u.setEstado(rs.getString("estado"));
                
                lista.add(u);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean existeUsername(String username) {
        String sql = "SELECT id FROM usuarios WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Si devuelve true, ya exiiiisteee!!!
        } catch (SQLException e) {
            return false;
        }
    }
}
