/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat8.pkg2;

import info.mensajeDeUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextPane;


/**
 *
 * @author edgargarcia
 */
public class Chat82 extends JFrame implements ActionListener {

    private JButton enviar, emoji1, emoji2, emoji3;
    private JTextField entrada;
    private JTextPane editor;
    private String nombreGuardado;
    private enviar hiloEnvia;

    public Chat82() {
        super("Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null); //Layout absoluto
        editor = new JTextPane(); //Incilaizamos el JTexpane
        editor.setBounds(5, 5, 790, 600);
        this.add(editor);
        editor.setText("Chat Online con MulticastSocket");
        entrada = new JTextField();
        entrada.setBounds(5, 650, 670, 50);
        this.add(entrada);
        enviar = new JButton("Enviar");
        enviar.setBounds(675, 650, 115, 50);
        enviar.addActionListener(this);
        this.add(enviar);
        emoji1 = new JButton(":)");
        emoji1.setBounds(5, 700, 100, 50);
        emoji1.addActionListener(this);
        this.add(emoji1);
        emoji2 = new JButton(":(");
        emoji2.setBounds(105, 700, 100, 50);
        emoji2.addActionListener(this);
        this.add(emoji2);
        emoji3 = new JButton("<3");
        emoji3.setBounds(205, 700, 100, 50);
        emoji3.addActionListener(this);
        this.add(emoji3);
//        setMensaje("Hola");
        escuchar hiloEscucha = new escuchar();
        hiloEscucha.start();

    }

    public void setMensaje(String nombre, String nuevoMensaje, String para) {
        String mensajeAnterior;
        mensajeAnterior = editor.getText();
        System.out.println(mensajeAnterior);
        editor.setText(mensajeAnterior + "\n" + nombre + ":" + nuevoMensaje + " " + "(" + para + ")");

    }

    public void h(String nameI, String mensajeI, String paraI) {
        hiloEnvia = new enviar(mensajeI, nameI, paraI);
        hiloEnvia.start();
        nombreGuardado = nameI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == enviar) {
            String message = entrada.getText();
            hiloEnvia = new enviar(message, nombreGuardado, "Todos");
            hiloEnvia.start();
            entrada.setText("");
            
        }
        if (btn == emoji1) {

        }
        if (btn == emoji2) {

        }
        if (btn == emoji3) {

        }
    }

//    public static void main(String[] args) {
//        enviar hiloEnvia = new enviar();
//        escuchar hiloEscucha = new escuchar();
//
//        hiloEscucha.start();
//        hiloEnvia.start();
//    }
    public class enviar extends Thread {
        // El dato que queramos enviar en el mensaje, como array de bytes.

        byte buffer[] = new byte[6500];
        MulticastSocket enviador;
        String name, mensaje, destino;

        public enviar(String mensaje, String name, String destino) {
            this.mensaje = mensaje;
            this.name = name;
            this.destino = destino;
        }

        @Override
        public void run() {
            mensajeDeUsuario c;
            c = new mensajeDeUsuario(mensaje, name, destino);
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
                //System.out.println(c.getUsuarioOrigen() + ":" + c.getMensaje()+"ENVIA");
                //setMensaje(c.getUsuarioOrigen(), c.getMensaje(), "Todos");

            } catch (IOException ex) {
                Logger.getLogger(escuchar.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

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
                        //System.out.println(r.getUsuarioOrigen() + " para " + r.getUsuarioDestino() + ":" + r.getMensaje());
                        setMensaje(r.getUsuarioOrigen(), r.getMensaje(), r.getUsuarioDestino());

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
}
