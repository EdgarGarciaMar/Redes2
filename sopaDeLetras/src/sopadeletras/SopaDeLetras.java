/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopadeletras;

import Conexion.Cliente;
import java.awt.Color;

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
    private int lamborghini[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int tesla[] = {22, 23, 24, 25, 26};
    private int porshe[] = {37, 54, 71, 88, 105, 122, 139};
    private int nissan[] = {58, 59, 60, 61, 62, 63};
    private int renault[] = {55, 70, 85, 100, 115, 130, 145};
    private int ford[] = {99, 82, 65, 48};
    private int audi[] = {146, 131, 116, 101};
    private int volkswagen[] = {192, 177, 162, 147, 132, 117, 102, 87, 72, 57};
    private int pegeot[] = {208, 193, 178, 163, 148, 133};
    private int kia[] = {152, 167, 182};
    private int bmw[] = {198, 213, 228};
    private int honda[] = {185, 200, 215, 230, 245};
    private int hyundai[] = {175, 174, 173, 172, 171, 170, 169};
    private int jeep[] = {231, 232, 233, 234};
    private int toyota[] = {255, 254, 253, 252, 251, 250};
    private int contador = 0;
    private int contarPalabras = 0;

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
        reset.setBackground(Color.magenta);
        reset.addActionListener(this);
        inferior.add(facil = new JButton("Facil"));
        facil.setBackground(Color.green);
        facil.addActionListener(this);
        inferior.add(dificil = new JButton("Dificil"));
        dificil.setBackground(Color.red);
        dificil.addActionListener(this);
        inferior.add(enviar = new JButton("Enviar"));
        enviar.setBackground(Color.blue);
        enviar.addActionListener(this);
    }

    private void buscaPalabras(int[] arr) {
        int i;
        for (i = 0; i < arr.length; i++) {
            if (botones[arr[i]].isEnabled() == false) {
                System.out.println("Buscapalabras-" + arr[i]);
                contador += 1;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String Mensaje;
        Cliente q;
        int i = 0;

        if (btn == facil) {
            Mensaje = "f";
            q = new Cliente();
            q.enviarOpc(Mensaje);
            //System.out.println("enviar");
            sopaFinal = q.recivirPaquete();
            letra = sopaFinal.toCharArray();
            for (i = 0; i < letra.length; i++) {
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
            //System.out.println("boton-habilitado"+botones[0].isEnabled());

//            for (i = 0; i < lamborghini.length; i++) {
//                if (botones[lamborghini[i]].isEnabled() == false) {
//                    System.out.println("Lambo-"+lamborghini[i]);
//                    contador+=1;
//                }
//            }
            buscaPalabras(lamborghini);
            if (contador == lamborghini.length) {
                System.out.println("lambo esta");
            }

        }
        for (i = 0; i < 256; i++) {
            if (btn == botones[i]) {
                botones[i].setBackground(Color.blue);
                botones[i].setEnabled(false);
                System.out.println("Boton:" + i);
            }
        }

    }

    public static void main(String[] args) {
        new SopaDeLetras();
    }

}
