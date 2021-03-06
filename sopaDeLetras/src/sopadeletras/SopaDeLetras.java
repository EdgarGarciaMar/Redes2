/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sopadeletras;

import Conexion.Cliente;
import java.awt.Color;
import java.awt.Desktop;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Alumno
 */
public class SopaDeLetras extends JFrame implements ActionListener {

    private JPanel superior, inferior;
    private JButton botones[] = new JButton[256];
    private JButton reset, facil, dificil, enviar, medio, respuestas;
    private char letra[] = new char[256];
    private String sopaFinal;
    private JLabel instrucciones;
    private int lamborghini[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private int tesla[] = {22, 23, 24, 25, 26};
    private int porshe[] = {37, 54, 71, 88, 105, 122, 139};
    private int nissan[] = {58, 59, 60, 61, 62, 63};
    private int renault[] = {55, 70, 85, 100, 115, 130, 145};
    private int ford[] = {99, 82, 65, 48};
    private int audi[] = {146, 131, 116, 101};
    private int volkswagen[] = {192, 177, 162, 147, 132, 117, 102, 87, 72, 57};
    private int pegeot[] = {208, 193, 178, 163, 148, 133};
    private int kia[] = {152, 167, 182};
    private int bmw[] = {198, 213, 228};
    private int honda[] = {185, 200, 215, 230, 245};
    private int hyundai[] = {175, 174, 173, 172, 171, 170, 169};
    private int jeep[] = {231, 232, 233, 234};
    private int toyota[] = {255, 254, 253, 252, 251, 250};
    private int contadorLamborghini = 0;
    private int contadorTesla = 0;
    private int contadorPorshe = 0;
    private int contadorNissan = 0;
    private int contadorRenault = 0;
    private int contadorFord = 0;
    private int contadorAudi = 0;
    private int contadorVolkswagen = 0;
    private int contadorPegeot = 0;
    private int contadorKia = 0;
    private int contadorBmw = 0;
    private int contadorHonda = 0;
    private int contadorHyundai = 0;
    private int contadorJeep = 0;
    private int contadorToyota = 0;
    //Contador de palabras
    private int contarPalabras = 0;
    private int verificador = 0;
    //MEDIA
    private int pc[] = {253, 236};
    private int ram[] = {52, 37, 22};
    private int teclado[] = {165, 166, 167, 168, 169, 170, 171};
    private int mouse[] = {71, 87, 103, 119, 135};
    private int tecla[] = {38, 53, 68, 83, 98};
    private int ssd[] = {207, 190, 173};
    private int hdd[] = {157, 141, 125};
    private int monitor[] = {186, 185, 184, 183, 182, 181, 180};
    private int windows[] = {7, 8, 9, 10, 11, 12, 13};
    private int mac[] = {254, 237, 220};
    private int linux[] = {245, 228, 211, 194, 177};
    private int java[] = {128, 144, 160, 176};//java esta en caso dificl
    private int phyton[] = {251, 250, 249, 248, 247, 246};
    private int php[] = {70, 86, 102};

    private int contadorPc = 0;
    private int contadorRam = 0;
    private int contadorTeclado = 0;
    private int contadorMouse = 0;
    private int contadorTecla = 0;
    private int contadorSsd = 0;
    private int contadorHdd = 0;
    private int contadorMonitor = 0;
    private int contadorWindows = 0;
    private int contadorMac = 0;
    private int contadorLinux = 0;
    private int contadorJava = 0;
    private int contadorPhyton = 0;
    private int contadorPhp = 0;

    //Dificil
    private int anagrama1[] = {17, 18, 19, 20, 21, 22};//silvar
    private int anagrama2[] = {64, 80, 96, 112, 128, 144, 160, 176, 192, 208, 224, 240};//metacarpiano
    private int anagrama3[] = {67, 83, 99, 115, 131, 147, 163, 179, 195, 211, 227, 243};//aparcamiento
    private int anagrama4[] = {116, 132, 148, 164, 180, 196};//brasil
    private int anagrama5[] = {100, 101, 102, 103, 104, 105, 106, 107};//calienta
    private int anagrama6[] = {197, 181, 165, 149, 133, 117, 101};//coracia
    private int anagrama7[] = {138, 123, 108, 93, 78};//matar
    private int anagrama8[] = {127, 111, 95, 79, 63, 47, 31, 15};//alicante
    private int anagrama9[] = {140, 156, 172, 188, 204, 220, 236};//arcaico
    private int anagrama10[] = {223, 207, 191, 175, 159};//marta

    private int contadorAnagrama1 = 0;
    private int contadorAnagrama2 = 0;
    private int contadorAnagrama3 = 0;
    private int contadorAnagrama4 = 0;
    private int contadorAnagrama5 = 0;
    private int contadorAnagrama6 = 0;
    private int contadorAnagrama7 = 0;
    private int contadorAnagrama8 = 0;
    private int contadorAnagrama9 = 0;
    private int contadorAnagrama10 = 0;

    public SopaDeLetras() {
        super("Sopa de Letras");
        ventanaSuperior();
        ventanaInferior();
        ventana();
    }

    private void ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1300, 800);
        this.setVisible(true);
        this.setLayout(new GridLayout(2, 1));
        this.add(superior);
        this.add(inferior);
    }

    private void ventanaSuperior() {
        superior = new JPanel();
        superior.setLayout(null);
        superior.setBackground(Color.BLUE);
        instrucciones = new JLabel("Selecciona una dificultad y encuentra todas las palabras en la sopa.");
        instrucciones.setBounds(0, 0, 1000, 100);
        superior.add(instrucciones);

    }

    private void ventanaInferior() {
        inferior = new JPanel();
        inferior.setLayout(new GridLayout(17, 17, 3, 3));
        inferior.setBackground(new java.awt.Color(15,129,237));
        for (int i = 0; i < 256; i++) {
            inferior.add(botones[i] = new JButton(""));
            botones[i].addActionListener(this);
        }
        inferior.add(reset = new JButton("Reset"));
        reset.setBackground(Color.BLUE);
        reset.addActionListener(this);
        inferior.add(facil = new JButton("Facil"));
        facil.setBackground(Color.GREEN);
        facil.addActionListener(this);
        inferior.add(medio = new JButton("Medio"));
        medio.setBackground(Color.yellow);
        medio.addActionListener(this);
        inferior.add(dificil = new JButton("Dificil"));
        dificil.setBackground(Color.RED);
        dificil.addActionListener(this);
        inferior.add(enviar = new JButton("Enviar"));
        enviar.setBackground(Color.GRAY);
        enviar.addActionListener(this);
        inferior.add(respuestas = new JButton("Respuestas"));
        respuestas.setBackground(Color.ORANGE);
        respuestas.addActionListener(this);
    }

    private int buscaPalabras(int[] arr) {
        int i, contador = 0;
        for (i = 0; i < arr.length; i++) {
            if (botones[arr[i]].isEnabled() == false) {
                System.out.println("Buscapalabras-" + arr[i]);
                contador += 1;
            }
        }
        return contador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        String Mensaje;
        Cliente q;
        int i = 0;

        if (btn == facil) {
            Mensaje = "f";
            verificador = 1;
            superior.setBackground(Color.GREEN);
            instrucciones.setText("Palabras a encontrar: TESLA,VOLKSWAGEN,TOYOTA,BMW,HYUNDAI,AUDI,HONDA,FORD,PORSCHE,LAMBORG HINI,KIA,RENAULT,PEGEOT,NISSAN,JEEP");
            q = new Cliente();
            q.enviarOpc(Mensaje);
            //System.out.println("enviar");
            sopaFinal = q.recivirPaquete();
            letra = sopaFinal.toCharArray();
            for (i = 0; i < 256; i++) {
                botones[i].setText(letra[i] + "");
                //System.out.println(letra[i]);
            }
            q.cerrarCliente();
        }
        if (btn == medio) {
            Mensaje = "m";
            verificador = 2;
            superior.setBackground(Color.YELLOW);
            instrucciones.setText("Palabras parciales a encontrar: WIND , R M, TECL, P P,M SE,H D,S D,J VA,TEC ,MON  OR,L  UX,PC,M C,PHY   ");
            q = new Cliente();
            q.enviarOpc(Mensaje);
            sopaFinal = q.recivirPaquete();
            letra = sopaFinal.toCharArray();
            for (i = 0; i < 256; i++) {
                botones[i].setText(letra[i] + "");
                //System.out.println(letra[i]);
            }
            q.cerrarCliente();
            //System.out.println("enviar");
        }

        if (btn == dificil) {
            Mensaje = "d";
            verificador = 3;
            superior.setBackground(Color.RED);
            instrucciones.setText("Longitud de las palabras a encontrar: 5c,5c,6c,6c,7c,7c,12c,12c,8c,8c, son anagramas");
            q = new Cliente();
            q.enviarOpc(Mensaje);
            sopaFinal = q.recivirPaquete();
            letra = sopaFinal.toCharArray();
            for (i = 0; i < 256; i++) {
                botones[i].setText(letra[i] + "");
                //System.out.println(letra[i]);
            }
            q.cerrarCliente();
            //System.out.println("enviar");
        }

        if (btn == reset) {
            Mensaje = "r";
            instrucciones.setText("Selecciona una dificultad y encuentra todas las palabras en la sopa.");
            superior.setBackground(Color.BLUE);
            q = new Cliente();
            q.enviarOpc(Mensaje);
            q.cerrarCliente();
            //System.out.println("enviar");
            for (i = 0; i < 256; i++) {
                botones[i].setText("");
                botones[i].setEnabled(true);
                //System.out.println(letra[i]);
            }
            contadorLamborghini = 0;
            contadorTesla = 0;
            contadorPorshe = 0;
            contadorNissan = 0;
            contadorRenault = 0;
            contadorFord = 0;
            contadorAudi = 0;
            contadorVolkswagen = 0;
            contadorPegeot = 0;
            contadorKia = 0;
            contadorBmw = 0;
            contadorHonda = 0;
            contadorHyundai = 0;
            contadorJeep = 0;
            contadorToyota = 0;

            contarPalabras = 0;

            contadorPc = 0;
            contadorRam = 0;
            contadorTeclado = 0;
            contadorMouse = 0;
            contadorTecla = 0;
            contadorSsd = 0;
            contadorHdd = 0;
            contadorMonitor = 0;
            contadorWindows = 0;
            contadorMac = 0;
            contadorLinux = 0;
            contadorJava = 0;
            contadorPhyton = 0;
            contadorPhp = 0;

            contadorAnagrama1 = 0;
            contadorAnagrama2 = 0;
            contadorAnagrama3 = 0;
            contadorAnagrama4 = 0;
            contadorAnagrama5 = 0;
            contadorAnagrama6 = 0;
            contadorAnagrama7 = 0;
            contadorAnagrama8 = 0;
            contadorAnagrama9 = 0;
            contadorAnagrama10 = 0;
        }
        if (btn == enviar) {
            //this.dispose();
            Mensaje = "e";
            superior.setBackground(Color.GRAY);
            q = new Cliente();
            q.enviarOpc(Mensaje);

            contadorLamborghini = buscaPalabras(lamborghini);
            if (contadorLamborghini == lamborghini.length) {
                System.out.println("lambo esta");
                contarPalabras += 1;
            }
            contadorTesla = buscaPalabras(tesla);
            if (contadorTesla == tesla.length) {
                System.out.println("tesla esta");
                contarPalabras += 1;
            }

            contadorPorshe = buscaPalabras(porshe);
            if (contadorPorshe == porshe.length) {
                System.out.println("Porshe esta");
                contarPalabras += 1;
            }

            contadorNissan = buscaPalabras(nissan);
            if (contadorNissan == nissan.length) {
                System.out.println("nissan esta");
                contarPalabras += 1;
            }

            contadorRenault = buscaPalabras(renault);
            if (contadorRenault == renault.length) {
                System.out.println("renault esta");
                contarPalabras += 1;
            }

            contadorFord = buscaPalabras(ford);
            if (contadorFord == ford.length) {
                System.out.println("ford esta");
                contarPalabras += 1;
            }

            contadorAudi = buscaPalabras(audi);
            if (contadorAudi == audi.length) {
                System.out.println("audi esta");
                contarPalabras += 1;
            }

            contadorVolkswagen = buscaPalabras(volkswagen);
            if (contadorVolkswagen == volkswagen.length) {
                System.out.println("Volkswagen esta");
                contarPalabras += 1;
            }

            contadorPegeot = buscaPalabras(pegeot);
            if (contadorPegeot == pegeot.length) {
                System.out.println("pegeot esta");
                contarPalabras += 1;
            }

            contadorKia = buscaPalabras(kia);
            if (contadorKia == kia.length) {
                System.out.println("kia esta");
                contarPalabras += 1;
            }

            contadorBmw = buscaPalabras(bmw);
            if (contadorBmw == bmw.length) {
                System.out.println("Bmw esta");
                contarPalabras += 1;
            }

            contadorHonda = buscaPalabras(honda);
            if (contadorHonda == honda.length) {
                System.out.println("Honda esta");
                contarPalabras += 1;
            }

            contadorHyundai = buscaPalabras(hyundai);
            if (contadorHyundai == hyundai.length) {
                System.out.println("Hyundai esta");
                contarPalabras += 1;
            }

            contadorJeep = buscaPalabras(jeep);
            if (contadorJeep == jeep.length) {
                System.out.println("Jeep esta");
                contarPalabras += 1;
            }

            contadorToyota = buscaPalabras(toyota);
            if (contadorToyota == toyota.length) {
                System.out.println("toyota esta");
                contarPalabras += 1;
            }
            //Medio
            contadorPc = buscaPalabras(pc);
            if (contadorPc == pc.length) {
                System.out.println("pc esta");
                contarPalabras += 1;
            }
            contadorRam = buscaPalabras(ram);
            if (contadorRam == ram.length) {
                System.out.println("ram esta");
                contarPalabras += 1;
            }
            contadorTeclado = buscaPalabras(teclado);
            if (contadorTeclado == teclado.length) {
                System.out.println("Teclado esta");
                contarPalabras += 1;
            }
            contadorMouse = buscaPalabras(mouse);
            if (contadorMouse == mouse.length) {
                System.out.println("Mouse esta");
                contarPalabras += 1;
            }
            contadorTecla = buscaPalabras(tecla);
            if (contadorTecla == tecla.length) {
                System.out.println("tecla esta");
                contarPalabras += 1;
            }
            contadorSsd = buscaPalabras(ssd);
            if (contadorSsd == ssd.length) {
                System.out.println("SSD esta");
                contarPalabras += 1;
            }
            contadorHdd = buscaPalabras(hdd);
            if (contadorHdd == hdd.length) {
                System.out.println("HDD esta");
                contarPalabras += 1;
            }
            contadorMonitor = buscaPalabras(monitor);
            if (contadorMonitor == monitor.length) {
                System.out.println("Monitor esta");
                contarPalabras += 1;
            }
            contadorWindows = buscaPalabras(windows);
            if (contadorWindows == windows.length) {
                System.out.println("Windows esta");
                contarPalabras += 1;
            }
            contadorMac = buscaPalabras(mac);
            if (contadorMac == mac.length) {
                System.out.println("Mac esta");
                contarPalabras += 1;
            }
            contadorLinux = buscaPalabras(linux);
            if (contadorLinux == linux.length) {
                System.out.println("Linux esta");
                contarPalabras += 1;
            }
            if (verificador == 2) {
                contadorJava = buscaPalabras(java);
                if (contadorJava == java.length) {
                    System.out.println("Java esta");
                    contarPalabras += 1;
                }
            }
            contadorPhyton = buscaPalabras(phyton);
            if (contadorPhyton == phyton.length) {
                System.out.println("phyton esta");
                contarPalabras += 1;
            }
            contadorPhp = buscaPalabras(php);
            if (contadorPhp == php.length) {
                System.out.println("Php esta");
                contarPalabras += 1;
            }
            //Dificil
            contadorAnagrama1 = buscaPalabras(anagrama1);
            if (contadorAnagrama1 == anagrama1.length) {
                System.out.println("Anagrama1 esta");
                contarPalabras += 1;
            }
            contadorAnagrama2 = buscaPalabras(anagrama2);
            if (contadorAnagrama2 == anagrama2.length) {
                System.out.println("Anagrama2 esta");
                contarPalabras += 1;
            }
            contadorAnagrama3 = buscaPalabras(anagrama3);
            if (contadorAnagrama3 == anagrama3.length) {
                System.out.println("Anagrama3 esta");
                contarPalabras += 1;
            }
            contadorAnagrama4 = buscaPalabras(anagrama4);
            if (contadorAnagrama4 == anagrama4.length) {
                System.out.println("Anagrama4 esta");
                contarPalabras += 1;
            }
            contadorAnagrama5 = buscaPalabras(anagrama5);
            if (contadorAnagrama5 == anagrama5.length) {
                System.out.println("Anagrama5 esta");
                contarPalabras += 1;
            }
            contadorAnagrama6 = buscaPalabras(anagrama6);
            if (contadorAnagrama6 == anagrama6.length) {
                System.out.println("Anagrama6 esta");
                contarPalabras += 1;
            }
            contadorAnagrama7 = buscaPalabras(anagrama7);
            if (contadorAnagrama7 == anagrama7.length) {
                System.out.println("Anagrama7 esta");
                contarPalabras += 1;
            }
            contadorAnagrama8 = buscaPalabras(anagrama8);
            if (contadorAnagrama8 == anagrama8.length) {
                System.out.println("Anagrama8 esta");
                contarPalabras += 1;
            }
            contadorAnagrama9 = buscaPalabras(anagrama9);
            if (contadorAnagrama9 == anagrama9.length) {
                System.out.println("Anagrama9 esta");
                contarPalabras += 1;
            }
            contadorAnagrama10 = buscaPalabras(anagrama10);
            if (contadorAnagrama10 == anagrama10.length) {
                System.out.println("Anagrama10 esta");
                contarPalabras += 1;
            }

            if (verificador == 1) {
                if (contarPalabras == 15) {
                    JOptionPane.showMessageDialog(null, "Felicidades encontraste todas las " + contarPalabras + " palabras");
                } else {
                    JOptionPane.showMessageDialog(null, "Palabras encontradas en la sopa: " + contarPalabras);
                }
            }

            if (verificador == 2) {
                if (contarPalabras == 14) {
                    JOptionPane.showMessageDialog(null, "Felicidades encontraste todas las " + contarPalabras + " palabras");
                } else {
                    JOptionPane.showMessageDialog(null, "Palabras encontradas en la sopa: " + contarPalabras + " Algunas palabras que te pueden faltar son: php, mac, linux, ram");
                }
            }
            if (verificador == 3) {
            if (contarPalabras == 10 && verificador == 3) {
                JOptionPane.showMessageDialog(null, "Felicidades encontraste todas las " + contarPalabras + " palabras");
            } else {
                JOptionPane.showMessageDialog(null, "Palabras encontradas en la sopa: " + contarPalabras + " Algunas palabras que te pueden faltar son Anagramas: Silvar, Croacia, Alicate");
            }
            }
            System.out.println("contar palabras:" + contarPalabras);
            q.cerrarCliente();
        }
        for (i = 0; i < 256; i++) {
            if (btn == botones[i]) {
                botones[i].setBackground(Color.blue);
                botones[i].setEnabled(false);
                System.out.println("Boton:" + i);
            }
        }

        if (btn == respuestas) {
            Mensaje = "a";
            superior.setBackground(Color.ORANGE);
            q = new Cliente();
            q.enviarOpc(Mensaje);
            q.cerrarCliente();
            //System.out.println("enviar");
            JOptionPane.showMessageDialog(null, "Te has rendido :(");
            try {
                File path = new File("/Users/edgargarcia/Redes2/sopaDeLetras/src/pdf/facil.pdf");//poner la ruta del paquete pdf para que la abra el so
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new SopaDeLetras();
    }

}
