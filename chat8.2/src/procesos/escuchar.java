/*
 Hilo de prueba, es la base para la clase que tiene la GUI
 */
package procesos;

import info.mensajeDeUsuario;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgargarcia
 */
public class escuchar extends Thread {

    // El mismo puerto que se uso en la parte de enviar.
    byte[] buffer2 = new byte[6500];
    MulticastSocket escucha;

    @Override
    public void run() {
        while (true) {
            System.out.println("Escuchando....");
            try {
                escucha = new MulticastSocket(4000);
                // Nos ponemos a la escucha de la misma IP de Multicast que se uso en la parte de enviar.
                escucha.joinGroup(InetAddress.getByName("230.0.0.1"));

                // Un array de bytes con tamaño suficiente para recoger el mensaje enviado, 
                // Se espera la recepción. La llamada a receive() se queda
                // bloqueada hasta que llegue un mesnaje.
                DatagramPacket dgp = new DatagramPacket(buffer2, buffer2.length);
                escucha.receive(dgp);

                // Obtención del dato ya relleno.
                ByteArrayInputStream bs = new ByteArrayInputStream(buffer2); // bytes es el byte[]
                ObjectInputStream is = new ObjectInputStream(bs);
                try {
                    mensajeDeUsuario r = (mensajeDeUsuario) is.readObject();
                    System.out.println(r.getUsuarioOrigen() + " para " + r.getUsuarioDestino() + ":" + r.getMensaje());
                    
                    
                    is.close();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(escuchar.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException ex) {
                Logger.getLogger(escuchar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
