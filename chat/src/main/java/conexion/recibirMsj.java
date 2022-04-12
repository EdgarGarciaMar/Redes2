/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import datos.Cliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
public class recibirMsj {

    MulticastSocket escucha;
    // Un array de bytes con tamaño suficiente para recoger el mensaje enviado, 
    // bastaría con 4 bytes
    byte[] buffer2 = new byte[6500];
    DatagramPacket dgp;
    String pack;

    public recibirMsj() { // El mismo puerto que se uso en la parte de enviar.
        try {
            escucha = new MulticastSocket(4000);//privado 55557
        } catch (IOException ex) {
            Logger.getLogger(recibirMsj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarServer() {
        escucha.close();
    }

    public void unirGrupo() throws IOException {
        // Nos ponemos a la escucha de la misma IP de Multicast que se uso en la parte de enviar.
        escucha.joinGroup(InetAddress.getByName("230.0.0.1"));
    }

    public String recibir() throws IOException, ClassNotFoundException {
        try {
            // Nos ponemos a la escucha de la misma IP de Multicast que se uso en la parte de enviar.
            //escucha.joinGroup(InetAddress.getByName("230.0.0.1"));
            // Se espera la recepción. La llamada a receive() se queda
            // bloqueada hasta que llegue un mesnaje.

            dgp = new DatagramPacket(buffer2, buffer2.length);
            // Obtención del dato ya relleno.
            escucha.receive(dgp);
//            pack = new String(dgp.getData());
//            System.out.println("pack recibido :"+pack);
            // System.out.println(usuario + ":" + pack);
            ByteArrayInputStream bs = new ByteArrayInputStream(buffer2); // bytes es el byte[]
            ObjectInputStream is = new ObjectInputStream(bs);
            Cliente r = (Cliente) is.readObject();

            System.out.println(r.getUsuarioOrigen() + " para " + r.getUsuarioDestino() + ":" + r.getMensaje());
            is.close();
        } catch (UnknownHostException ex) {
            Logger.getLogger(recibirMsj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pack;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        recibirMsj r;

        r = new recibirMsj();
        r.unirGrupo();

        while (true) {
            r.recibir();

        }

    }

}
