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
public class recivirMsj {

    public static void main(String[] args) throws IOException {
        // El mismo puerto que se uso en la parte de enviar.
        MulticastSocket escucha = new MulticastSocket(55557);

// Nos ponemos a la escucha de la misma IP de Multicast que se uso en la parte de enviar.
        escucha.joinGroup(InetAddress.getByName("230.0.0.1"));

// Un array de bytes con tamaño suficiente para recoger el mensaje enviado, 
// bastaría con 4 bytes.
        byte[] dato = new byte[1024];

// Se espera la recepción. La llamada a receive() se queda
// bloqueada hasta que llegue un mesnaje.
        DatagramPacket dgp = new DatagramPacket(dato, dato.length);
        escucha.receive(dgp);

// Obtención del dato ya relleno.
        byte[] dato2 = dgp.getData();
        System.out.println("Dato recibido: "+dato2+", tam: "+dato2.length);
    }

}
