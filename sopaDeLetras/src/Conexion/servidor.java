/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

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
    byte[] buffer = new byte[400];
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
    //MEDIA
    String sopaDeLetrasPC1 = "DHFÁIWTWINDOWSOF";
    String sopaDeLetrasPC2 = "JYÜVÍIMYÍPÓKEUQÑ";
    String sopaDeLetrasPC3 = "KÍIKHATZKIZGCKFW";
    String sopaDeLetrasPC4 = "JWOVRELIÉOÉÜOVXQ";
    String sopaDeLetrasPC5 = "ÑCZECDPMZPÁUPUMN";
    String sopaDeLetrasPC6 = "PWPLVFHOCÁOÚCÓUÜ";
    String sopaDeLetrasPC7 = "YHANTDPUHRÁAKJKA";
    String sopaDeLetrasPC8 = "YVERHSISIÓMTGDHO";
    String sopaDeLetrasPC9 = "JÍÚHHXXEAÑLQVDSV";
    String sopaDeLetrasPC10 = "AIXPJLÁBJNÍBSHXM";
    String sopaDeLetrasPC11 = "VCBLXTECLADOLDIC";
    String sopaDeLetrasPC12 = "AXZÉROTINOMBLQSC";
    String sopaDeLetrasPC13 = "ÑZUPSAEZEPYJXLÁS";
    String sopaDeLetrasPC14 = "LÓÁNNJONPEXCCSPG";
    String sopaDeLetrasPC15 = "AMCYIADAVÁRÑCAÜR";
    String sopaDeLetrasPC16 = "LZJYGLNOTYHPEPME";
    
    String sopaFinal2=sopaDeLetrasPC1+sopaDeLetrasPC2+sopaDeLetrasPC3+sopaDeLetrasPC4+sopaDeLetrasPC5+sopaDeLetrasPC6+sopaDeLetrasPC7+sopaDeLetrasPC8+sopaDeLetrasPC9+sopaDeLetrasPC10+sopaDeLetrasPC11+
            sopaDeLetrasPC12+sopaDeLetrasPC13+sopaDeLetrasPC14+sopaDeLetrasPC15+sopaDeLetrasPC16;   
    
    //DIFICIL
    String sopaDeLetrasAnagrama1= "EASYBKÍDÑIDDZVCE";
    String sopaDeLetrasAnagrama2= "ÑSILBARCÉDÁYÉXMT";
    String sopaDeLetrasAnagrama3= "WADSÜIJVJDPHRLMN";
    String sopaDeLetrasAnagrama4= "NIQTVÚHÑVORÚÓÓÑA";
    String sopaDeLetrasAnagrama5= "MTLAGZÜCÉEDJLWRC";
    String sopaDeLetrasAnagrama6= "EKPPFCTKÓZÍXÍAUI";
    String sopaDeLetrasAnagrama7= "TVÓACALIENTATZÉL";
    String sopaDeLetrasAnagrama8= "AUIRBIRÜQÑGAJPJA";
    String sopaDeLetrasAnagrama9= "CXZCRCQLFTMRAÓPH";
    String sopaDeLetrasAnagrama10= "ANBAAAÍJAOÚTRMBA";
    String sopaDeLetrasAnagrama11= "RÍIMSOFWJTFCCÓAT";
    String sopaDeLetrasAnagrama12= "PÑKIIRNÜFÚÜYAWIR";
    String sopaDeLetrasAnagrama13= "IDKELCYMÜEPÓIUÑA";
    String sopaDeLetrasAnagrama14= "ALÓNQDWEOVNRCDÓM";
    String sopaDeLetrasAnagrama15= "NIGTISMMNWZSOYSB";
    String sopaDeLetrasAnagrama16= "OLIOFVPNÓÑKÍBZKD";
    
    String sopaFinal3=sopaDeLetrasAnagrama1+sopaDeLetrasAnagrama2+sopaDeLetrasAnagrama3+sopaDeLetrasAnagrama4+sopaDeLetrasAnagrama5+sopaDeLetrasAnagrama6+sopaDeLetrasAnagrama7+sopaDeLetrasAnagrama8+
            sopaDeLetrasAnagrama9+sopaDeLetrasAnagrama10+sopaDeLetrasAnagrama11+sopaDeLetrasAnagrama12+sopaDeLetrasAnagrama13+sopaDeLetrasAnagrama14+sopaDeLetrasAnagrama15+sopaDeLetrasAnagrama16;

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

//    public void recivirPaquete() {//Funcion guia
//
//        while (true) {
//            try {
//                DatagramPacket Paquete = new DatagramPacket(buffer, buffer.length);
//                MiSocket.receive(Paquete);
//                pack = new String(Paquete.getData());
//                System.out.println("Paquete recibido: " + pack);
//            } catch (Exception e) {
//                System.out.println("Error" + e);
//            }
//        }
//    }
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
                        System.out.println("Respuestas evaluadas...");
                        break;
                    case "f":
                        System.out.println("case facil..");
                        buffer = sopaFinal.getBytes();
                        DatagramPacket Paquete2 = new DatagramPacket(buffer,
                                buffer.length, Paquete.getAddress(), Paquete.getPort());
                        MiSocket.send(Paquete2);
                        System.out.println("tam env:" + sopaFinal.length());

                        break;
                    case "d":
                        System.out.println("case difil..");
                        buffer = sopaFinal3.getBytes();
                        DatagramPacket Paquete4 = new DatagramPacket(buffer,
                                buffer.length, Paquete.getAddress(), Paquete.getPort());
                        MiSocket.send(Paquete4);
                        System.out.println("tam env:" + sopaFinal3.length());
                        break;
                    case "r":
                        System.out.println("case reset..");
                        System.out.println("Se ha reiniciado el juego...");
                        break;
                    case "m":
                        System.out.println("case medio");
                        
                        buffer = sopaFinal2.getBytes();
                        DatagramPacket Paquete3 = new DatagramPacket(buffer,
                                buffer.length, Paquete.getAddress(), Paquete.getPort());
                        MiSocket.send(Paquete3);
                        System.out.println("tam env:" + sopaFinal2.length());
                        break;
                    case "a":
                        System.out.println("EL jugador se rindio :(");
                        break;

                }
                //iniciarJuego(pack);
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }

    public static void main(String[] args) {
        servidor q;
        q = new servidor();
        q.recivirOpc();
        //q.recivirPaquete();
        q.cerrarServer();
    }
}
