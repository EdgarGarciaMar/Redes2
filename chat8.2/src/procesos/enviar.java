/*
Hilo de prueba, es la base para la clase que tiene la GUI
 */
package procesos;


import info.mensajeDeUsuario;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgargarcia
 */
public class enviar extends Thread {
    // El dato que queramos enviar en el mensaje, como array de bytes.

    byte buffer[] = new byte[6500];
    MulticastSocket enviador;
    String name, mensaje, destino;
    int tipo;
    public enviar(String mensaje, String name, String destino, int tipo){
        this.mensaje =mensaje;
        this.name=name;
        this.destino=destino;
        this.tipo=tipo;
    }

    @Override
    public void run() {
        mensajeDeUsuario c;
        c = new mensajeDeUsuario(mensaje,name, destino,tipo);
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ObjectOutputStream os;

        try {
            os = new ObjectOutputStream(bs);
            os.writeObject(c);  
            buffer = bs.toByteArray();
            os.close();
            
            enviador = new MulticastSocket();
            // Usamos la direccion Multicast 230.0.0.1, por poner alguna dentro del rango
            // y el puerto 55557, uno cualquiera que esté libre.
            DatagramPacket dgp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("230.0.0.1"), 4000);
            // Envío
            enviador.send(dgp);
            System.out.println(c.getUsuarioOrigen() + ":" + c.getMensaje());
            
        } catch (IOException ex) {
            Logger.getLogger(escuchar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
