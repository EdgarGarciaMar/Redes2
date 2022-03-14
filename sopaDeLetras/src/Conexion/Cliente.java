/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alumno
 */
public class Cliente {

    int pto = 1400;
    byte[] buffer = new byte[400];
    byte[] buffer2 = new byte[1];
    DatagramSocket MiSocket;
    String host = "localhost";
    String pack;

    public Cliente() {
        try {
            MiSocket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarCliente() {
        MiSocket.close();
    }

//    public void enviarPaquete(String Mensaje) {//Funcion de ejemplo
//        try {
//            buffer = Mensaje.getBytes();
//            DatagramPacket Paquete = new DatagramPacket(buffer,
//                    Mensaje.length(), InetAddress.getByName(host), pto);
//            MiSocket.send(Paquete);
//
//        } catch (IOException exc) {
//            System.out.println("Error" + exc);
//        }//try
//    }

    public void enviarOpc(String Mensaje) {
        try {
            buffer2 = Mensaje.getBytes();
            DatagramPacket Paquete = new DatagramPacket(buffer2,
                    Mensaje.length(), InetAddress.getByName(host), pto);
            MiSocket.send(Paquete);

        } catch (IOException exc) {
            System.out.println("Error" + exc);
        }//try
    }

        public String recivirPaquete() {

        //while (pack.length()!=256) {
            try {
                DatagramPacket Paquete = new DatagramPacket(buffer, buffer.length);
                MiSocket.receive(Paquete);
                pack = new String(Paquete.getData());
                System.out.println("Paquete recibido Cliente: " + pack);
                System.out.println("Tam:"+pack.length());
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
            
        //}
       return pack;
    }



    public static void main(String[] args) {
//        Cliente c;
//        c = new Cliente();
//        c.enviarPaquete();
//        c.cerrarCliente();
    }

}
