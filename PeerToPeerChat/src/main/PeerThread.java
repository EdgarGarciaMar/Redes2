/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.json.Json;
import static javax.json.Json.createReader;
import static javax.json.Json.createReader;
import javax.json.JsonObject;
import javax.json.JsonReader;



/**
 *
 * @author edgargarcia
 */
public class PeerThread extends Thread{
    private BufferedReader bufferedReader;
    public PeerThread(Socket socket) throws IOException{
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    @Override
    public void run(){
        boolean flag = true;
        while(flag){
            try{
            JsonObject jsonObject = (JsonObject) createReader(bufferedReader).readObject();
            if(jsonObject.containsKey("username"))
                System.out.println("["+jsonObject.getString("username")+"]: "+jsonObject.getString("message"));
            }catch(Exception e){
                flag = false;
                interrupt();
            }
        }
    }
}
