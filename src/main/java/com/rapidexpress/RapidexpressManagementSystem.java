package com.rapidexpress;

import com.rapidexpress.config.DatabaseConnection;
import com.rapidexpress.controller.MainController;
import com.rapidexpress.dao.UsuarioDAO;
import com.rapidexpress.model.Usuario;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class RapidexpressManagementSystem {

    public static void main(String[] args) {

        System.out.println("--- INICIANDO SISTEMA RAPIDEXPRESS ---");

        //Probar conexión
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("CONEXIÓN EXITOSA CON LA BASE DE DATOS");
        } catch (SQLException e) {
            System.out.println("ERROR DE CONEXIÓN: " + e.getMessage());
            return; // si no hay conexión, salimos del programa
        }

        //Pedir credenciales por consola
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese su username: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        //Crear el DAO y llamar al método login
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuarioLogueado = usuarioDAO.login(username, password);

        //Verificar resultado
        if (usuarioLogueado != null) {
            System.out.println("\n LOGIN EXITOSO");
            System.out.println("Bienvenido, " + usuarioLogueado.getNombreCompleto());
            System.out.println("Rol: " + usuarioLogueado.getRol());
            
            MainController controller = new MainController();
            controller.mostrarMenuPrincipal(usuarioLogueado);
            usuarioLogueado = null;

        } else {
            System.out.println("\n Credenciales incorrectas o usuario inactivo.");
        }

        scanner.close();
    }
}
