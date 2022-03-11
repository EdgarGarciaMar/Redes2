/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopadeletras;

import Conexion.Cliente;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alumno
 */
public class SopaDeLetras extends JFrame implements ActionListener {

    private JPanel superior, inferior;
    private JButton botones[] = new JButton[256];
    private JButton reset, facil, dificil, enviar;
    private char letra[] = new char[256];
    private String sopaFinal;
    private JLabel instrucciones;

    public SopaDeLetras() {
        super("Sopa de Letras");
        ventanaSuperior();
        ventanaInferior();
        ventana();
    }

    private void ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1300, 800);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(superior);
        this.add(inferior);
    }

    private void ventanaSuperior() {
        superior = new JPanel();
        superior.setLayout(null);
        instrucciones = new JLabel("Selecciona una dificultad y encuentra todas las palabras en la sopa.");
        instrucciones.setBounds(0, 0, 600, 100);
        superior.add(instrucciones);

    }

    private void ventanaInferior() {
        inferior = new JPanel();
        inferior.setLayout(new GridLayout(17, 17, 3, 3));
        for (int i = 0; i < 256; i++) {
            inferior.add(botones[i] = new JButton(""));
            botones[i].addActionListener(this);
        }
        inferior.add(reset = new JButton("Reset"));
        reset.addActionListener(this);
        inferior.add(facil = new JButton("Facil"));
        facil.addActionListener(this);
        inferior.add(dificil = new JButton("Dificil"));
        dificil.addActionListener(this);
        inferior.add(enviar = new JButton("Enviar"));
        enviar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String Mensaje;
        Cliente q;

        if (btn == facil) {
            Mensaje = "f";
            q = new Cliente();
            q.enviarOpc(Mensaje);
            //System.out.println("enviar");
            sopaFinal = q.recivirPaquete();
            letra = sopaFinal.toCharArray();
            for (int i = 0; i < letra.length; i++) {
                botones[i].setText(letra[i] + "");
                //System.out.println(letra[i]);
            }
            q.cerrarCliente();
        }
        if (btn == dificil) {
            Mensaje = "d";
            q = new Cliente();
            q.enviarOpc(Mensaje);
            q.cerrarCliente();
            //System.out.println("enviar");
        }

        if (btn == reset) {
            Mensaje = "r";
            q = new Cliente();
            q.enviarOpc(Mensaje);
            q.cerrarCliente();
            //System.out.println("enviar");
        }
        if (btn == enviar) {
            //this.dispose();
            Mensaje = "e";
            q = new Cliente();
            q.enviarOpc(Mensaje);
            q.cerrarCliente();
            //System.out.println("enviar");
        }
    }

    public static void main(String[] args) {
        new SopaDeLetras();
    }

}
