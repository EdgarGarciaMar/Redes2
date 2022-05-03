/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat8.pkg2;

import javax.swing.JOptionPane;

/**
 *
 * @author edgargarcia
 */
public class inicioApp {

    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog("Ingresa tu nombre por favor");
        if ("".equals(name)) {
            JOptionPane.showMessageDialog(null, "Nombre no aceptado");
            System.exit(0);
        } else {
            Chat82 c = new Chat82();
            c.h(name, "Se ha unido", "(Todos)");
        }
    }

}
