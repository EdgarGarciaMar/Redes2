import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Inicio extends javax.swing.JFrame {
    Cliente clt;
    private ArrayList<String> carpetas;
    private ArrayList<String> archivos;
    private ArrayList<String> direccion;
    
    //Contiene los archivos y carpetas seleccionadas con checkbox
    private ArrayList<String> archivosSeleccionados;
    private ArrayList<String> carpetasSeleccionadas;
    
    public Inicio() {
        clt = new Cliente();
        //A direccion se anadira un nombre de carpeta cuando piquemos sobre cualquier carpeta
        //A direccion se eliminara a su ultimo elemento cuando se pique el botón regresar, en donde si direccion esta vacia no hara nada
        direccion = new ArrayList<String>();
        carpetas = new ArrayList<String>();
        archivos = new ArrayList<String>();
        carpetas = clt.getCarpetas("");
        archivos = clt.getArchivos("");
        initComponents();
        //A archivosSeleccionados se le insertaran los nombres de los archivos que se marquen con chekbox
        archivosSeleccionados = new ArrayList<String>();
        carpetasSeleccionadas = new ArrayList<String>();
        cargarArchivosyCarpetas();
    }
    
    public void abrirCarpeta(String carp){
        direccion.add(carp);
        carpetaActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ubicacion(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30)));
        getCarpyAr();
        cargarArchivosyCarpetas();
    }
    public String ubicacion(){
        int i,tam = direccion.size();
        String ub = "Mi Unidad";
        for(i=0;i<tam;i++){
            ub = ub+" -> "+direccion.get(i);
        }
        return ub;
    }
    private void cargarArchivosyCarpetas(){
        //Limpiar Panel
        MainPanel.removeAll();
        
        archivosSeleccionados.clear();
        carpetasSeleccionadas.clear();
        
        //CARGAR CARPETAS
        for(String carpeta: carpetas){            
            //Contenerdor General
            JPanel contenedor = new JPanel();
            BorderLayout layout = new BorderLayout();
            layout.setVgap(5);//Separacion Vert. entre elementos del contenedor
            contenedor.setLayout(layout);  
            //Contenedor de BOTON  
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(200,300));
            //boton.setBackground(Color.WHITE);
            boton.setContentAreaFilled(false); //QUITAR FONDO
            boton.setBorder(null);
            
            boton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirCarpeta(carpeta);
                    //System.out.println("Abrir Carpeta: "+carpeta);
                }                
            });
            //Colocar el icono al boton del directorio
            boton.setIcon(new ImageIcon("Imagenes/folder4.png"));

            JPanel contenedorBoton = new JPanel();
            FlowLayout  layoutBtn = new FlowLayout();layoutBtn.setVgap(2);
            contenedorBoton.setLayout(layoutBtn);
            contenedorBoton.add(boton);


            //Contendor CHECKBOX: https://www.javatpoint.com/java-jcheckbox
            JCheckBox  checkRadio = new JCheckBox(carpeta);//NOMBRE CARPETA
            checkRadio.setFont(new Font("Dialog",1,15));
            checkRadio.addItemListener(new ItemListener() {    
                @Override
                public void itemStateChanged(ItemEvent e) {                 
                   //label.setText("C++ Checkbox: "+ (e.getStateChange()==1?"checked":"unchecked"));
                   if(e.getStateChange()==1){
                       //System.out.println("Carpeta Seleccionada: "+carpeta);
                       carpetasSeleccionadas.add(carpeta);
                   }else{
                       //System.out.println("Carpeta Deseleccionada: "+carpeta);
                       carpetasSeleccionadas.remove(carpetasSeleccionadas.indexOf(carpeta));
                   }
                   //System.out.println(carpetasSeleccionadas);
                }    
             });
            
            JPanel contenedorCB = new JPanel();
            FlowLayout  layoutCB = new FlowLayout();layoutBtn.setVgap(2);
            contenedorCB.setLayout(layoutCB);
            contenedorCB.add(checkRadio);
            
            contenedor.add(contenedorBoton, BorderLayout.CENTER);
            contenedor.add(contenedorCB, BorderLayout.SOUTH); 
            MainPanel.add(contenedor);
        }
        
        //CARGAR ARCHIVOS
        for(String archivo: archivos){            
            //Contenerdor General
            JPanel contenedor = new JPanel();
            BorderLayout layout = new BorderLayout();
            layout.setVgap(5);//Separacion Vert. entre elementos del contenedor
            contenedor.setLayout(layout);     
            //Contenedor de BOTON  
            JButton boton = new JButton();
            boton.setPreferredSize(new Dimension(200,300));
            boton.setContentAreaFilled(false);
            boton.setBorder(null);

            JPanel contenedorBoton = new JPanel();
            FlowLayout  layoutBtn = new FlowLayout();layoutBtn.setVgap(2);
            contenedorBoton.setLayout(layoutBtn);
            contenedorBoton.add(boton);

            //Contendor CHECKBOX
            JCheckBoxMenuItem checkRadio = new JCheckBoxMenuItem(archivo);//NOMBRE CARPETA
            checkRadio.setFont(new Font("Dialog",1,15));
            checkRadio.addItemListener(new ItemListener() {    
                @Override
                public void itemStateChanged(ItemEvent e) {                 
                   //label.setText("C++ Checkbox: "+ (e.getStateChange()==1?"checked":"unchecked"));
                   if(e.getStateChange()==1){
                       //System.out.println("Archivo Seleccionado: "+archivo);
                       archivosSeleccionados.add(archivo);
                   }else{
                       //System.out.println("Archivo Deseleccionado: "+archivo);
                       archivosSeleccionados.remove(archivosSeleccionados.indexOf(archivo));
                   }
                   //System.out.println(archivosSeleccionados);
                }    
             });
            //Colocar el icono al boton del Archivo
            //https://icons8.com/, pagina de iconos
            String ext = archivo.substring(archivo.length()-4);
            switch (ext) {
                case ".png":
                    boton.setIcon(new ImageIcon("Imagenes/png1.png"));
                    break;
                case ".pdf":
                    boton.setIcon(new ImageIcon("Imagenes/pdf2.png"));
                    break;
                case ".jpg":
                    boton.setIcon(new ImageIcon("Imagenes/jpg1.png"));
                    break;
                case "docx":
                    boton.setIcon(new ImageIcon("Imagenes/docx1.png"));
                    break;
                case ".txt":
                    boton.setIcon(new ImageIcon("Imagenes/txt1.png"));
                    break;
                case ".mp3":
                    boton.setIcon(new ImageIcon("Imagenes/mp31.png"));
                    break;
                case ".mp4":
                    boton.setIcon(new ImageIcon("Imagenes/mp41.png"));
                    break;
                case ".ppt":
                    boton.setIcon(new ImageIcon("Imagenes/ppt1.png"));
                    break;
                case ".java":
                    boton.setIcon(new ImageIcon("Imagenes/ce.png"));
                    break;
                case ".exe":
                    boton.setIcon(new ImageIcon("Imagenes/exe1.png"));
                    break;
                default:
                    boton.setIcon(new ImageIcon("Imagenes/desconocido.png"));
                    break;
            }
            
            JPanel contenedorCB = new JPanel();
            FlowLayout  layoutCB = new FlowLayout();layoutBtn.setVgap(2);
            contenedorCB.setLayout(layoutCB);
            contenedorCB.add(checkRadio);


            contenedor.add(contenedorBoton, BorderLayout.CENTER);
            contenedor.add(contenedorCB, BorderLayout.SOUTH); 

            MainPanel.add(contenedor);
        }        
        MainPanel.updateUI();
    }
    
    private void getCarpyAr(){
        String dir = dirActual();
        carpetas.clear();
        archivos.clear();
        carpetas = clt.getCarpetas(dir);
        archivos = clt.getArchivos(dir);
    }
    private String dirActual(){
        String dir = "";
        int i,tam;
        tam = direccion.size();
        i = 0;
        while(i<tam){
            dir = dir+direccion.get(i)+"\\";
            i++;
        }
        return dir;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        salir = new javax.swing.JButton();
        carpetaActual = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();
        subirArchivos = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        btnDescargar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));

        salir.setBackground(new java.awt.Color(255, 0, 51));
        salir.setForeground(new java.awt.Color(255, 255, 255));
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        carpetaActual.setBackground(new java.awt.Color(204, 255, 204));
        carpetaActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mi Unidad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30))); // NOI18N
        carpetaActual.setToolTipText("");

        MainPanel.setBackground(new java.awt.Color(204, 204, 255));
        MainPanel.setBorder(new javax.swing.border.MatteBorder(null));
        MainPanel.setLayout(new java.awt.GridLayout(3, 0, 30, 30));
        carpetaActual.setViewportView(MainPanel);
        MainPanel.getAccessibleContext().setAccessibleName("Mi Unidad");
        MainPanel.getAccessibleContext().setAccessibleDescription("");

        subirArchivos.setBackground(new java.awt.Color(255, 255, 153));
        subirArchivos.setText("Cargar Archivos");
        subirArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subirArchivosActionPerformed(evt);
            }
        });

        regresar.setBackground(new java.awt.Color(204, 0, 204));
        regresar.setText("Anterior");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });

        btnDescargar.setBackground(new java.awt.Color(153, 255, 102));
        btnDescargar.setText("Descargar");
        btnDescargar.setToolTipText("");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(255, 0, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Borrar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 102, 102));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel2.setText("Sistema de archivos Drive");

        jButton1.setBackground(new java.awt.Color(255, 204, 0));
        jButton1.setText("Nueva Carpeta");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(carpetaActual, javax.swing.GroupLayout.PREFERRED_SIZE, 1194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(subirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(btnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)))
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(259, 259, 259)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(carpetaActual, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subirArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        carpetaActual.getAccessibleContext().setAccessibleName("My Unidad 2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void subirArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subirArchivosActionPerformed
        
        String dir = dirActual();
        JFileChooser jf = new JFileChooser();
        jf.setMultiSelectionEnabled(true);
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int r = jf.showOpenDialog(null);        
        
        if(r==JFileChooser.APPROVE_OPTION){
            File[] f = jf.getSelectedFiles();//, pero también necesitamos un arreglo de nombres, direcciones y de largos, además de necesitar enviar archivo por archivo y ¿cómo mandar carpetas?
  
            
            clt.subirArchivosyCarpetas(f,dir);
        }
        
        this.getCarpyAr();
        cargarArchivosyCarpetas();
    }//GEN-LAST:event_subirArchivosActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this,"Esta seguro de que desea eliminar los archivos y/o carpetas seleccionados?","Confirmar eliminacion",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.YES_OPTION){
            boolean b = false;
            if(this.carpetasSeleccionadas.size()>0)
                b = clt.eliminarCarpetas(carpetasSeleccionadas,dirActual());
            else
                b = true;
            if(b==false)
                //Imprime error al eliminar carpetas
                JOptionPane.showMessageDialog(null,"Ha habido un error al tratar de eliminar las carpetas seleccionadas, no se podra continuar con la operacion","Error",JOptionPane.INFORMATION_MESSAGE);
            else if(this.archivosSeleccionados.size()>0){
                b = clt.eliminarArchivos(archivosSeleccionados,dirActual());
                    if(b==false)
                        //Imprime error al eliminar archivos
                        JOptionPane.showMessageDialog(null,"Ha habido un error al tratar de eliminar los archivos seleccionados, no se podra continuar con la operacion","Error",JOptionPane.INFORMATION_MESSAGE);
            }
            if(b==true)
                //Imprime exito
                JOptionPane.showMessageDialog(null,"La operacion para eliminar las carpetas y/o archivos seleccionados resulto exitosa","Exito",JOptionPane.INFORMATION_MESSAGE);
        }
        this.getCarpyAr();
        cargarArchivosyCarpetas();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        if(this.archivosSeleccionados.size()>0)
            clt.descargarArchivos(archivosSeleccionados,dirActual());
        if(this.carpetasSeleccionadas.size()>0)
            clt.descargarCarpetas(carpetasSeleccionadas,dirActual());
        JOptionPane.showMessageDialog(null,"Operacion completada, los archivos y/o carpetas seleccionados se descargaron","Exito",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        int i=direccion.size();
        if(i>0)
            this.direccion.remove(i-1);
        carpetaActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ubicacion(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30)));
        getCarpyAr();
        cargarArchivosyCarpetas();
    }//GEN-LAST:event_regresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       //CREAMOS CARPETA 
       String crear;
       int i=0;
       String nombre = JOptionPane.showInputDialog(null, "Ingresa el nombre de la carpeta nueva");
       if(nombre.equals("")){
           JOptionPane.showMessageDialog(null, "Carpeta no creada", "Error", JOptionPane.WARNING_MESSAGE);
       }else{
           crear = Paths.get(".").toAbsolutePath().normalize().toString() +"\\archivos";
           
           for(i=0;i<direccion.size();i++){
               crear+="\\"+direccion.get(i);
           }
           crear+="\\"+nombre;
           //System.out.println(crear);
           File c = new File(crear);
          
           if(!c.exists()){
           if(c.mkdirs()){
                     //System.out.println("Carpeta creada");
                carpetaActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ubicacion(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 30)));
                getCarpyAr();
                cargarArchivosyCarpetas();
                }else{
                     System.out.println("Carpeta No creada");
                }
           }else{
               JOptionPane.showMessageDialog(null, "Esta carpeta ya existe", "Error", JOptionPane.WARNING_MESSAGE);
           }
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane carpetaActual;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton regresar;
    private javax.swing.JButton salir;
    private javax.swing.JButton subirArchivos;
    // End of variables declaration//GEN-END:variables
}

