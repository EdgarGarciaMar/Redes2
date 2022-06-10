/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author edgargarcia
 */
public class ServerThread extends Thread {
    private ServerSocket serverSocket;
    private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();
    public ServerThread (String portNumb) throws IOException{
        serverSocket = new ServerSocket(Integer.valueOf(portNumb));
    }
    @Override
    public void run(){
        try{
            while(true){
                ServerThreadThread serverThreadThread = new ServerThreadThread(serverSocket.accept(),this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        }catch(Exception e){e.printStackTrace();}
        
    }
    public void sendMenssage ( String  message){
        try{
            serverThreadThreads.forEach(t-> t.getPrintWriter().println(message));
        }catch(Exception e){e.printStackTrace();}
    }
    public Set<ServerThreadThread> getServerThreadThreads(){
        Set<ServerThreadThread> serverThreadThread;
        return serverThreadThreads;
    }
}
