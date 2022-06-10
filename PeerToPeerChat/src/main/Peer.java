/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.Socket;
import javax.json.Json;

/**
 *
 * @author edgargarcia
 */
public class Peer {

    public static void main(String[] args) throws Exception {
        System.out.println("Chat p2p");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingreasa tu nombre de usuario & el puerto separados por un espacio: ");
        String[] setupValues = bufferedReader.readLine().split(" ");
        ServerThread serverThread = new ServerThread(setupValues[1]);
        serverThread.start();
        new Peer().updateListenToPeers(bufferedReader, setupValues[0], serverThread);
    }

    public void updateListenToPeers(BufferedReader bufferedReader, String username, ServerThread serverThread) throws Exception {
        System.out.println("Ingresa (separado por un espacio) hostname: numero de puerto");
        System.out.println("Ingresa s para saltar:");
        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");
        if(!input.equals("s"))for(int i = 0; i< inputValues.length;i++){
            String[] address = inputValues[i].split(":");
            Socket socket = null;
            try{
                socket = new Socket (address [0],Integer.valueOf(address[1]));
                new PeerThread(socket).start();
            }
            catch (Exception e){
                if(socket != null) socket.close();
                else System.out.println("Entrada invalida, pasando al siguiente paso.");
            }
        }
        communicate(bufferedReader, username, serverThread);
    }
    
    public void communicate (BufferedReader bufferedReader, String username, ServerThread serverThread){
        try{
            System.out.println("Ahora puedes comunicarte (e para exit, c para change)");
            boolean flag = true;
            while(flag){
                String message = bufferedReader.readLine();
                if(message.equals("e")){
                    flag=false;
                    break;
                }else if(message.equals("c")){
               updateListenToPeers(bufferedReader, username, serverThread); 
                }
                else{
                    StringWriter stringwriter = new StringWriter();
                    Json.createWriter(stringwriter).writeObject(Json.createObjectBuilder().add("username", username).add("message", message).build());
                    serverThread.sendMenssage(stringwriter.toString());
                }
            }
            System.exit(0);
        }catch(Exception e){
            
        }
    }
}
