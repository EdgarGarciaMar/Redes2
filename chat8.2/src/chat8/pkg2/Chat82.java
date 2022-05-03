/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat8.pkg2;

import info.mensajeDeUsuario;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


/**
 *
 * @author edgargarcia
 */
public class Chat82 extends JFrame implements ActionListener {

    private JButton enviar, emoji1, emoji2, emoji3, emoji4, emoji5, emoji6, emoji7, emoji8, emoji9, emoji10;
    private JTextField entrada;
    private JEditorPane editor;
    public String nombreGuardado;
    private enviar hiloEnvia;
    private JScrollPane scrollPane;
    private JComboBox<String> combo1;
    private String usuarioOnline;
    private String[] nombres = new String[100];
    private int numNombres = 0;
    private int privado = 0;
    private String seleccionado = "";
    //private int tipo=0;// 0 es publico ,1 es privado
    //img=0 no img, si es 1 es img
    String mensajeAnterior = "<b>Chat Online con MulticastSocket</b>", mensajeGuardado;

    public Chat82() {
        super("Chat");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(null); //Layout absoluto
        this.getContentPane().setBackground(Color.BLUE);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 0, 790, 600);
        editor = new JEditorPane("text/html", null); //Incilaizamos el JTexpane
        editor.setPreferredSize(new Dimension(755, 600));
        editor.setEditable(false);
        scrollPane.setViewportView(editor);
        this.add(scrollPane);
        editor.setText("<b>Chat Online con MulticastSocket</b>");
        entrada = new JTextField();
        entrada.setBounds(5, 620, 670, 50);
        this.add(entrada);

        combo1 = new JComboBox<String>();
        combo1.setBounds(5, 710, 150, 50);
        this.add(combo1);

        combo1.addItem("Todos");
        combo1.addActionListener((ActionEvent e) -> {

            seleccionado = (String) combo1.getSelectedItem();
            if (seleccionado == "Todos") {
                privado = 0;
            }
            if (seleccionado != "Todos") {
                privado = 1;
            }
            //System.out.println(seleccionado);
            //System.out.println("se ha unido:"+usuarioOnline);
        });
        enviar = new JButton("Enviar");
        enviar.setBounds(675, 620, 115, 50);
        enviar.addActionListener(this);
        this.add(enviar);
        emoji1 = new JButton("❤");
        emoji1.setBounds(5, 670, 50, 40);
        emoji1.addActionListener(this);
        this.add(emoji1);
        emoji2 = new JButton("◕‿◕");
        emoji2.setBounds(55, 670, 50, 40);
        emoji2.addActionListener(this);
        this.add(emoji2);
        emoji3 = new JButton("༼  ͡° ͜ʖ ͡° ༽");
        emoji3.setBounds(105, 670, 80, 40);
        emoji3.addActionListener(this);
        this.add(emoji3);
        emoji4 = new JButton("ó_ò");
        emoji4.setBounds(190, 670, 50, 40);
        emoji4.addActionListener(this);
        this.add(emoji4);
        emoji5 = new JButton("(ノಠ益ಠ)");
        emoji5.setBounds(240, 670, 80, 40);
        emoji5.addActionListener(this);
        this.add(emoji5);
        emoji6 = new JButton("(▀̿̿Ĺ̯̿▀̿ ̿)");
        emoji6.setBounds(320, 670, 80, 40);
        emoji6.addActionListener(this);
        this.add(emoji6);
        emoji7 = new JButton("(ಥ﹏ಥ)");
        emoji7.setBounds(400, 670, 80, 40);
        emoji7.addActionListener(this);
        this.add(emoji7);

        emoji8 = new JButton("٩(♡ε♡ )۶");
        emoji8.setBounds(480, 670, 80, 40);
        emoji8.addActionListener(this);
        this.add(emoji8);
        emoji9 = new JButton("[̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅]");
        emoji9.setBounds(560, 670, 80, 40);
        emoji9.addActionListener(this);
        this.add(emoji9);
        emoji10 = new JButton(" (◉ω◉)");
        emoji10.setBounds(640, 670, 80, 40);
        emoji10.addActionListener(this);
        this.add(emoji10);

        escuchar hiloEscucha = new escuchar();
        hiloEscucha.start();

    }

    public void setMensaje(String nombre, String nuevoMensaje, String para) {

        //mensajeAnterior = editor.getText();
        //System.out.println(mensajeAnterior);
        mensajeGuardado = "<b>" + mensajeAnterior + "<br>" + nombre + ":" + nuevoMensaje + " " + "(" + para + ")" + "</b>";
        editor.setText(mensajeGuardado);
        mensajeAnterior = mensajeGuardado;

    }

    public void h(String nameI, String mensajeI, String paraI) {
        hiloEnvia = new enviar(mensajeI, nameI, paraI, 0, 0);
        hiloEnvia.start();
        nombreGuardado = nameI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (btn == enviar) {
//            System.out.println("pRIVADO: "+privado);
            System.out.println("tam selc:" + seleccionado.length());
            if (privado == 1) {
                String message = entrada.getText();
                hiloEnvia = new enviar(message, nombreGuardado, seleccionado, 1, 0);
                hiloEnvia.start();
                entrada.setText("");
                setMensaje(nombreGuardado,message,seleccionado);
            }
            if (privado == 0) {
                String message = entrada.getText();
                hiloEnvia = new enviar(message, nombreGuardado, seleccionado, 0, 0);
                hiloEnvia.start();
                entrada.setText("");
            }

        }
        if (btn == emoji1) {
            
            String imgsrc = Chat82.class.getClassLoader().getSystemResource("./img/corazon.png").toString();
            String emojiCorazon  = "<img src = '" + imgsrc + "' width = 16 height = 16>";
            
            if (privado == 1) {
                hiloEnvia = new enviar(emojiCorazon, nombreGuardado, seleccionado, 1, 1);
                setMensaje(nombreGuardado,emojiCorazon,seleccionado);

            } else {
                hiloEnvia = new enviar(emojiCorazon, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji2) {
            String emojiEnamorado = "◕‿◕";
            if (privado == 1) {
                hiloEnvia = new enviar(emojiEnamorado, nombreGuardado, seleccionado, 1, 1);
            } else {
                hiloEnvia = new enviar(emojiEnamorado, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();

        }
        if (btn == emoji3) {
            String emojiLenny = "༼  ͡° ͜ʖ ͡° ༽";
            if (privado == 1) {
                hiloEnvia = new enviar(emojiLenny, nombreGuardado, seleccionado, 1, 1);
            } else {
                hiloEnvia = new enviar(emojiLenny, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji4) {
            String emoji4 = "ó_ò";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji4, nombreGuardado, seleccionado, 1, 1);
            } else {
                hiloEnvia = new enviar(emoji4, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji5) {
            String emoji5 = "(ノಠ益ಠ)";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji5, nombreGuardado, seleccionado, 1, 1);
            } else {
                hiloEnvia = new enviar(emoji5, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }

        if (btn == emoji6) {
            String emoji6 = " (▀̿̿Ĺ̯̿▀̿ ̿)";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji6, nombreGuardado, seleccionado, 1, 1);
            } else {

                hiloEnvia = new enviar(emoji6, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji7) {
            String emoji7 = "(ಥ﹏ಥ)";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji7, nombreGuardado, seleccionado, 1, 1);
            } else {

                hiloEnvia = new enviar(emoji7, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji8) {
            String emoji8 = "٩(♡ε♡ )۶";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji8, nombreGuardado, seleccionado, 1, 1);
            } else {

                hiloEnvia = new enviar(emoji8, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji9) {
            String emoji9 = "[̲̅$̲̅(̲̅ιοο̲̅)̲̅$̲̅]";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji9, nombreGuardado, seleccionado, 1, 1);
            } else {

                hiloEnvia = new enviar(emoji9, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
        }
        if (btn == emoji10) {
            String emoji10 = " (◉ω◉)";
            if (privado == 1) {
                hiloEnvia = new enviar(emoji10, nombreGuardado, seleccionado, 1, 1);
            } else {

                hiloEnvia = new enviar(emoji10, nombreGuardado, "Todos", 0, 1);

            }
            hiloEnvia.start();
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
        int tipo;
        int img;

        public enviar(String mensaje, String name, String destino, int tipo, int img) {
            this.mensaje = mensaje;
            this.name = name;
            this.destino = destino;
            this.tipo = tipo;
            this.img = img;
        }

        @Override
        public void run() {
            mensajeDeUsuario c;
            c = new mensajeDeUsuario(mensaje, name, destino, tipo, img);
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

        public int busquedaBinariaRecursiva(String[] arreglo, String busqueda, int izquierda, int derecha) {
            // Si izquierda es mayor que derecha significa que no encontramos nada
            int resultadoDeLaComparacion = -2;
            if (izquierda > derecha) {
                return -1;
            }

            // Calculamos las mitades...
            int indiceDelElementoDelMedio = (int) Math.floor((izquierda + derecha) / 2);
            String elementoDelMedio = arreglo[indiceDelElementoDelMedio];

            // Primero vamos a comparar y luego vamos a ver si el resultado es negativo,
            // positivo o 0
            if (elementoDelMedio == null) {
                resultadoDeLaComparacion = -1;
            } else {
                resultadoDeLaComparacion = busqueda.compareTo(elementoDelMedio);
            }

            // Si el resultado de la comparación es 0, significa que ambos elementos son iguales
            // y por lo tanto quiere decir que hemos encontrado la búsqueda
            if (resultadoDeLaComparacion == 0) {
                return indiceDelElementoDelMedio;
            }

            // Si no, entonces vemos si está a la izquierda o derecha
            if (resultadoDeLaComparacion < 0) {
                derecha = indiceDelElementoDelMedio - 1;
                return busquedaBinariaRecursiva(arreglo, busqueda, izquierda, derecha);
            } else {
                izquierda = indiceDelElementoDelMedio + 1;
                return busquedaBinariaRecursiva(arreglo, busqueda, izquierda, derecha);
            }
        }

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
                        //System.out.println("comprobacion name en publico: " + nombreGuardado + "=" + seleccionado);
                        if (r.getTipo() == 0) {
                            //System.out.println("Tipo publico");
                            if (r.getImg() == 0) {
                                setMensaje(r.getUsuarioOrigen(), r.getMensaje(), r.getUsuarioDestino());
                            }
                            if (r.getImg() == 1) {
                                System.out.println("img");

                                setMensaje(r.getUsuarioOrigen(), r.getMensaje(), r.getUsuarioDestino());
                                //editor.insertIcon(new ImageIcon(r.getMensaje()));
                            }
                        }

                        if (nombreGuardado.equals(r.getUsuarioDestino()) && r.getTipo() == 1) {
                            if (r.getImg() == 0) {
                                setMensaje(r.getUsuarioOrigen(), r.getMensaje(), r.getUsuarioDestino());
                            }
                            if (r.getImg() == 1) {
                                System.out.println("img");
                                setMensaje(r.getUsuarioOrigen(), r.getMensaje(), r.getUsuarioDestino());
                                //editor.insertIcon(new ImageIcon(r.getMensaje()));
                            }
                        }

                        usuarioOnline = r.getUsuarioOrigen();

                        if (numNombres == 100) {
                            System.out.println("usuarios maximos alcanzados");
                            System.exit(0);
                        }

                        int resultadoBusqueda = busquedaBinariaRecursiva(nombres, usuarioOnline, 0, 99);

                        if (resultadoBusqueda == -1) {//el usuario no se encontro, por lo que se agrega
                            System.out.println("el usuario se ha agregado: " + usuarioOnline);
                            nombres[numNombres] = usuarioOnline;
                            combo1.addItem(nombres[numNombres]);
                            //System.out.println(nombres[numNombres]);
                            numNombres++;
                        } else {//el usuario ya esta
                            System.out.println("El usuario " + usuarioOnline + " Ya esta.");
                        }

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
