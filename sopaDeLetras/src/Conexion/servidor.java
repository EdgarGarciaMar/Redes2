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
 * 
 * http://azsopadeletras.com/
 * https://mexico.as.com/mexico/2022/02/05/masdeporte/1644031759_489071.html
 */
public class servidor {

    int pto = 1400;
    String host = "localhost";
    DatagramSocket MiSocket;
    byte[] buffer = new byte[256];
    byte[] buffer2 = new byte[1];
    String pack;
    String sopaDeLetrasAutos1 = "INIHGROBMALTÍCQI";
    String sopaDeLetrasAutos2 = "EOÁPBCALSETOFMIK";
    String sopaDeLetrasAutos3 = "GRHÁXEKÉÍUÁÚNUYH";
    String sopaDeLetrasAutos4 = "DÓDFIÜHRRNNISSAN";
    String sopaDeLetrasAutos5 = "DRÑVRUECEZOTLSYÓ";
    String sopaDeLetrasAutos6 = "QEOGXNQGSRUCXYQW";
    String sopaDeLetrasAutos7 = "ÜLPFAIAMBRVQALAÓ";
    String sopaDeLetrasAutos8 = "WROUDWMPNQOUÚIDI";
    String sopaDeLetrasAutos9 = "UELUSTÍYIÓÓPSWWJ";
    String sopaDeLetrasAutos10 = "ZTAKOTWÁKMÍÍBNVD";
    String sopaDeLetrasAutos11 = "FVLEPINIVIADNUYH";
    String sopaDeLetrasAutos12 = "KOGIÚÓAKZHGKNÉYV";
    String sopaDeLetrasAutos13 = "VEHRÁJBTOYQRXQJÍ";
    String sopaDeLetrasAutos14 = "PSÜSSMÁNKFÓGIVOÁ";
    String sopaDeLetrasAutos15 = "PÜHÍWÓDJEEPWMBHM";
    String sopaDeLetrasAutos16 = "UVOLCACQKÓATOYOT";
    String sopaFinal = sopaDeLetrasAutos1 + sopaDeLetrasAutos2 + sopaDeLetrasAutos3 + sopaDeLetrasAutos4 + sopaDeLetrasAutos5 + sopaDeLetrasAutos6 + sopaDeLetrasAutos7
            + sopaDeLetrasAutos8 + sopaDeLetrasAutos9 + sopaDeLetrasAutos10 + sopaDeLetrasAutos11 + sopaDeLetrasAutos12 + sopaDeLetrasAutos13 + sopaDeLetrasAutos14
            + sopaDeLetrasAutos15 + sopaDeLetrasAutos16;

    public servidor() {
        try {
            MiSocket = new DatagramSocket(pto);
            System.out.println("Servidor Iniciado, esperando una conexion....");
            //System.out.println(sopaFinal.length());
        } catch (SocketException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarServer() {
        MiSocket.close();
    }

    public void recivirPaquete() {

        while (true) {
            try {
                DatagramPacket Paquete = new DatagramPacket(buffer, buffer.length);
                MiSocket.receive(Paquete);
                pack = new String(Paquete.getData());
                System.out.println("Paquete recibido: " + pack);
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }


    public void recivirOpc() {

        while (true) {
            try {
                DatagramPacket Paquete = new DatagramPacket(buffer2, buffer2.length);
                MiSocket.receive(Paquete);
                pack = new String(Paquete.getData());
                System.out.println("opc recibida: " + pack);

                switch (pack) {
                    case "e":
                        System.out.println("case enviar..");
                        break;
                    case "f":
                        System.out.println("case facil..");
                        buffer = sopaFinal.getBytes();
                        DatagramPacket Paquete2 = new DatagramPacket(buffer,
                                sopaFinal.length(), Paquete.getAddress(), Paquete.getPort());
                        MiSocket.send(Paquete2);
                        System.out.println("tam env:"+sopaFinal.length());

                        break;
                    case "d":
                        System.out.println("case difil..");
                        break;
                    case "r":
                        System.out.println("case reset..");
                        break;

                }
                //iniciarJuego(pack);
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }

//    public void iniciarJuego(String pack) {
//        // System.out.println(pack + ",iniciar juego");
//
//        switch (pack) {
//            case "e":
//                System.out.println("case enviar..");
//                break;
//            case "f":
//                System.out.println("case facil..");
//                buffer = sopaFinal.getBytes();
//                DatagramPacket Paquete = new DatagramPacket(buffer,
//                        sopaFinal.length(), InetAddress.getByName(host), pto);
//                MiSocket.send(Paquete);
//
//                break;
//            case "d":
//                System.out.println("case difil..");
//                break;
//            case "r":
//                System.out.println("case reset..");
//                break;
//
//        }
//    }
    public static void main(String[] args) {
        servidor q;
        q = new servidor();
        q.recivirOpc();
        //q.recivirPaquete();
        q.cerrarServer();
    }
}