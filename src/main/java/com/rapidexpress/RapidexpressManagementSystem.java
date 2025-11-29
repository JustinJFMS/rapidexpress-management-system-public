/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.rapidexpress;

import com.rapidexpress.config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author USUARIO
 */
public class RapidexpressManagementSystem {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO SISTEMA RAPIDEXPRESS ---");
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("CONEXIÓN EXITOSA CON LA BASE DE DATOS");
        } catch (SQLException e) {
            System.out.println("ERROR DE CONEXIÓN: " + e.getMessage());
        }
    }
}
