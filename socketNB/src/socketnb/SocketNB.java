package socketnb;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edgargarcia
 */
public class SocketNB {
    private static String FileName;
    private static SocketChannel ch;
    
    public static void main(String[] args) {
        System.out.println("Iniciando Servidor con socket N.B....");
        try{
           int pto=8000;
           InetSocketAddress dir = new InetSocketAddress(pto);
           ServerSocketChannel s = ServerSocketChannel.open();
           s.configureBlocking(false);
           s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
           s.socket().bind(dir);
           Selector sel = Selector.open();
           s.register(sel, SelectionKey.OP_ACCEPT);
           ByteBuffer b = ByteBuffer.allocate(1024);
           System.out.println("Esperando una petición...");
           int n;
           String peticion="";
           while(true){
               sel.select();
               Iterator<SelectionKey>it = sel.selectedKeys().iterator();
               while(it.hasNext()){
                   SelectionKey k = (SelectionKey)it.next();
                   it.remove();
                    if(k.isAcceptable()){//Aceptar nueva conexion
                        ch = s.accept();
                        System.out.println("Cliente conectado desde "+ch.socket().getInetAddress()+ ", Puerto :"+ ch.socket().getPort());
                        ch.configureBlocking(false);
                        ch.register(sel, SelectionKey.OP_READ);
                        continue;
                    }//if
                    
                    if(k.isReadable()){                        
                        ch = (SocketChannel)k.channel(); 
                        b.clear();
                        n = ch.read(b);
                        b.flip();
                        if(b.hasArray())
                            peticion = new String(b.array(), 0, b.limit());
                        
                        if(peticion==null)
			{
                            String sb = "";
                            sb.concat("<html><head><title>Servidor WEB Socket N.B.\n");
                            sb.concat("</title><body bgcolor=\"#FF1300\"<br>Vacio</br>\n");
                            sb.concat("</body></html>\n");
                            ch.write(ByteBuffer.wrap(sb.getBytes()));
                            ch.close();
                            continue;
			}
			
			System.out.println("Petición: "+peticion+"\r\n\r\n");
                        String line="";                
			StringTokenizer st1= new StringTokenizer(peticion,"\n");
                        if( st1.hasMoreTokens())
                            line = st1.nextToken();
                        
                        if(line.toUpperCase().startsWith("GET")){
                            get(line);
                            continue;
                        }else{
                            ByteBuffer msj = ByteBuffer.wrap("HTTP/1.0 501 Not Implemented\r\n".getBytes());
                            ch.write(msj);
                            ch.close();
                            continue;
                        }
                    }     
               }//while
           }//while
           //cl.close();
        }catch(IOException e){
            e.printStackTrace();
        }//catch
    }
    public static void getArch(String line){
	int i = line.indexOf("/");
	int f = line.indexOf(" ",i);
        FileName=line.substring(i+1,f);	
        System.out.println("Nombre del archivo "+ FileName);
    }
    
    public static void get(String line){
        int cod;
        if(line.indexOf("?")==-1){
            cod = 0;
            getArch(line);
            if(FileName.compareTo("")==0){ //No hay nombre del recurso
                cod = 200;
                FileName = "index.htm";
                SendA("index.htm");
            }else{
                File f = Paths.get(".").toFile();
                File[] files = f.listFiles();
                System.out.println("Buscando archivo...");
                for(File file : files){
                    if(file.getName().equals(FileName)){
                        cod = 200;
                        SendA(FileName);
                        break;
                    }
                }
            }
            
            if(cod == 0){
                try {
                    String msj = "HTTP:/1.0 404 Not Found \n";
                    String date = "Date: "+ new Date()+"\n";
                    msj = msj+date;
                    msj = msj + ("Content-Type: text/html\n\n");
                    msj = msj + ("<html><head><title>Servidor WEB socket N.B.</title></head>\n");
                    msj = msj + ("<body bgcolor=\"#FF1300\">\n<br><h2><center>ERROR 404 NOT FOUND</h2></center></br>\n");
                    msj = msj + ("</body></html>\n");
                    System.out.println("Respuesta: "+msj);
                    ch.write(ByteBuffer.wrap(msj.getBytes()));
                    ch.close();
                } catch (IOException ex) {
                    Logger.getLogger(SocketNB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{//Se enviaron parametros
            try {
                System.out.println("Envio de parametros");
                StringTokenizer tokens=new StringTokenizer(line,"?");
                String req_a=tokens.nextToken();
                String req=tokens.nextToken();
                System.out.println("Token1: "+req_a);
                System.out.println("Token2: "+req);
                String parametros = req.substring(0, req.indexOf(" "))+"\n";
                System.out.println("Parametros: "+parametros);
                String respuesta= "";
                respuesta = respuesta.concat("HTTP/1.0 200 Okay \n");
                String fecha= "Date: " + new Date()+" \n";
                respuesta = respuesta.concat(fecha);
                String tipo_mime = "Content-Type: text/html \n\n";
                respuesta = respuesta.concat(tipo_mime);
                respuesta = respuesta.concat("<html><head><title>SERVIDOR WEB N.B.</title></head>\n");
                respuesta = respuesta.concat("<body bgcolor=\"#00FF51\"><center><h1><br>Parametros Obtenidos</br></h1><h3><b>\n");
                respuesta = respuesta.concat(parametros);
                respuesta = respuesta.concat("</b></h3>\n");
                respuesta = respuesta.concat("</center></body></html>\n\n");
                System.out.println("Respuesta: "+respuesta);
                ch.write(ByteBuffer.wrap(respuesta.getBytes()));  
                ch.close();
            } //else
            
            catch (IOException ex) {
                Logger.getLogger(SocketNB.class.getName()).log(Level.SEVERE, null, ex);               
            }
            
            
        }
    }     
    
    public static String extension (String extension){
        if( extension.equals("jpg"))    return "image/jpeg";        
        if( extension.equals("pdf"))    return "application/pdf";
        if( extension.equals("rar"))    return "application/x-rar-compressed";        
        if( extension.equals("mp3"))    return "audio/mpeg";        
        if( extension.equals("jpeg"))   return "image/jpeg";        
        if( extension.equals("png"))    return "image/png";        
        if( extension.equals("html"))   return "text/html";        
        if( extension.equals("htm"))    return "text/html";        
        if( extension.equals("c"))      return "text/plain";       
        if( extension.equals("txt"))    return "text/plain";        
        if( extension.equals("java"))   return "text/plain";        
        if( extension.equals("doc"))    return "application/msword";        
        return "text/html";
    }
    
    public static void SendA(String arg) {
        try{
            File ff = new File(arg);			
            long tam_archivo=ff.length();
            /***********************************************/
            String sb = ""; 
            int inicio = FileName.indexOf("."), fin = FileName.length() ;            
            //Obtener extension del archivo
            if( inicio == -1){
                System.out.println("Error en el archivo");
                ch.close();                
            }            
            String extension = FileName.substring(inicio+1, fin);
            
            sb = sb+"HTTP/1.0 200 ok\n";
            sb = sb +"Server: Servidor Web Socket N.B./1.1 \n";
            sb = sb +"Date: " + new Date()+" \n";
            sb = sb +"Content-Type: " +extension(extension)+ " \n";
            sb = sb +"Content-Length: "+tam_archivo+" \n";
            sb = sb +"\n";     
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(sb.getBytes());
            ch.write(ByteBuffer.wrap(os.toByteArray()));
            
            ByteBuffer bf = ByteBuffer.allocate(10240);
            FileInputStream fis = new FileInputStream(ff.getAbsolutePath());
            FileChannel source =  fis.getChannel();
            int r, count = 0;
            
            bf.clear();
            while( (r = source.read(bf)) != -1){
                System.out.println("Leyendo "+r+" bytes");
                count += r;
                bf.flip();
                ch.write(bf);
                bf.clear();
            }
            System.out.println(""+count+" bytes leídos");
            source.close();
            ch.close();
            fis.close();
	}
	catch(Exception e){
            System.out.println(e.getMessage());
	}
				
}
}
               

