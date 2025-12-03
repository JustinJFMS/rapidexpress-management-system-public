package com.rapidexpress.controller;

import com.rapidexpress.dao.UsuarioDAO;
import com.rapidexpress.model.Rol;
import com.rapidexpress.model.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioController {
    private final UsuarioDAO usuarioDAO;
    private final Scanner scanner;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
        this.scanner = new Scanner(System.in);
    }
    
    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- GESTIÓN DE USUARIOS DEL SISTEMA ---");
            System.out.println("1. Registrar Nuevo Usuario (Admin/Operador/Auxiliar)");
            System.out.println("2. Listar Usuarios Existentes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
    
    private void registrarUsuario() {
        System.out.println("\n>> NUEVO USUARIO");
        
        System.out.print("Username (Usuario para login): ");
        String username = scanner.nextLine().trim();
        
        if (usuarioDAO.existeUsername(username)) {
            System.out.println("Error: Ese usuario ya existe.");
            return;
        }

        System.out.print("Password Temporal: ");
        String password = scanner.nextLine();
        
        System.out.print("Nombre Completo: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Email Corporativo: ");
        String email = scanner.nextLine();

        // Selección de ROL con menú para evitar errores
        System.out.println("Seleccione el ROL:");
        System.out.println("1. ADMIN");
        System.out.println("2. OPERADOR");
        System.out.println("3. AUXILIAR");
        System.out.print("Opción: ");
        
        int opRol = 0;
        try {
            opRol = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            opRol = 0;
        }

        Rol rolSeleccionado = null;
        if (opRol == 1) rolSeleccionado = Rol.ADMIN;
        else if (opRol == 2) rolSeleccionado = Rol.OPERADOR;
        else if (opRol == 3) rolSeleccionado = Rol.AUXILIAR;
        else {
            System.out.println("Rol inválido. Registro cancelado.");
            return;
        }

        // Crear objeto usando el constructor que no tiene Id
        Usuario nuevo = new Usuario(username, password, nombre, email, rolSeleccionado);

        if (usuarioDAO.registrar(nuevo)) {
            System.out.println("Usuario " + username + " creado exitosamente con rol " + rolSeleccionado);
        } else {
            System.out.println("Error al guardar en base de datos.");
        }
    }
    
    private void listarUsuarios() {
        System.out.println("\n>> LISTA DE ACCESO AL SISTEMA");
        List<Usuario> lista = usuarioDAO.listarTodos();
        
        System.out.printf("%-5s %-15s %-25s %-15s %-10s%n", "ID", "USERNAME", "NOMBRE", "ROL", "ESTADO");
        System.out.println("---------------------------------------------------------------------------");
        
        for (Usuario u : lista) {
            System.out.printf("%-5d %-15s %-25s %-15s %-10s%n", 
                    u.getId(), u.getUsername(), u.getNombreCompleto(), u.getRol(), u.getEstado());
        }
    }
}
