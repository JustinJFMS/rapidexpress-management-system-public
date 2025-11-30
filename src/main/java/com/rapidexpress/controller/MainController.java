/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rapidexpress.controller;

import com.rapidexpress.model.Usuario;
import java.lang.classfile.instruction.SwitchCase;
import java.util.Scanner;

/**
 *
 * @author Pc
 */
public class MainController {
    
    private final Scanner scanner;
    
    public MainController() {
        this.scanner = new Scanner(System.in);
    }
    
    // metodo que decide que menu mostrar
    public void mostrarMenuPrincipal(Usuario usuario) {
        System.out.println("\n ************************** ");
        System.out.println(" PANEL DE CONTROL: "+ usuario.getRol());
        System.out.println("**************************");
    }
    //Switch que muestra el menu dependiendo de la situacion
    switch (usuario.getRol()) {
        case ADMIN:
            menuAdmin();
            break;
        case OPERADOR:
            menuOperador();
            break;
        case AUXILIAR:
            menuAuxiliar();
            break;
        default:
            System.out.println("ERROR: Rol no encontrado");
    }
}
