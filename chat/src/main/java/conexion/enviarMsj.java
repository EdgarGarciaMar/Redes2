/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgargarcia
 */
public class enviarMsj {

    public static void main(String[] args) throws IOException {
        MulticastSocket enviador = new MulticastSocket();

// El dato que queramos enviar en el mensaje, como array de bytes.
        byte[] dato = new byte[]{1, 2, 3, 4};

// Usamos la direccion Multicast 230.0.0.1, por poner alguna dentro del rango
// y el puerto 55557, uno cualquiera que esté libre.
        DatagramPacket dgp;
        try {
            dgp = new DatagramPacket(dato, dato.length, InetAddress.getByName("230.0.0.1"), 55557);
            // Envío
            enviador.send(dgp);
            System.out.println("Se envio el dato:"+dato+", su tam: "+dato.length);
        } catch (UnknownHostException ex) {
            Logger.getLogger(recivirMsj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

