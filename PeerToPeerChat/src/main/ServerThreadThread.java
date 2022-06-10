/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author edgargarcia
 */
public class ServerThreadThread extends Thread{
    private ServerThread serverThread;
    private Socket socket;
    private PrintWriter printwriter;
    public ServerThreadThread (Socket socket, ServerThread serverThread){
        this.serverThread = serverThread;
        this.socket=socket;
        
    }
    @Override
    public void run(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.printwriter = new PrintWriter(socket.getOutputStream(),true);
            while(true) serverThread.sendMenssage(bufferedReader.readLine());
        }catch(Exception e){
            serverThread.getServerThreadThreads().remove(this);
        }
    }
    public PrintWriter getPrintWriter () {
        return printwriter;
    }
    
}
