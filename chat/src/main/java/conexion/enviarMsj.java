/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import datos.Cliente;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgargarcia
 */
public class enviarMsj {

    byte buffer[] = new byte[6500];
    MulticastSocket enviador;
    DatagramPacket dgp;

    public enviarMsj() {
        try {
            enviador = new MulticastSocket();
        } catch (IOException ex) {
            Logger.getLogger(enviarMsj.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cerrarCliente() {
        enviador.close();
    }

    public void enviar() throws IOException {
        Cliente c;
        c = new Cliente("Hola, como estas?", "Edgar", "Todos");

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bs);
        os.writeObject(c);  // this es de tipo DatoUdp
        os.close();

        buffer = bs.toByteArray();
        // Usamos la direccion Multicast 230.0.0.1, por poner alguna dentro del rango
        // y el puerto 55557, uno cualquiera que esté libre.

        try {
            dgp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("230.0.0.1"), 4000);//55557 puerto privado
            // Envío
            enviador.send(dgp);
            System.out.println(c.getUsuarioOrigen() + ":" + c.getMensaje());
        } catch (UnknownHostException ex) {
            Logger.getLogger(recibirMsj.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        enviarMsj c;

        c = new enviarMsj();

        c.enviar();
        c.cerrarCliente();
    }
}
