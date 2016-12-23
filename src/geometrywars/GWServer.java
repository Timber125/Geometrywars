package geometrywars;


import geometrywars.Rendering.Engine;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class GWServer {

    
   ServerSocket ss ;
   boolean active = true;
   ArrayList<Socket> clients = new ArrayList<>();
   
   public GWServer(){
        try {
            // Launch de engine
            // Geef aan player direct de inputhandler mee
            // Zorg dat de player zich volledig zelfstandig registreert
            // Laat vervolgens de 2 spelers als clienten met de server connectie maken, elk met een eigen inputhandler
            // Laat de server 2 players spawnen met de meegegeven inputhandlers
            // Geef class Player mee als friendly met elke bullet die de spelers spawnen
            ss = new ServerSocket(8085);
        } catch (IOException ex) {
            Logger.getLogger(GWServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        Runnable r = new Runnable() {

            @Override
            public void run() {
                while(active){
                    try {
                        Socket client_connecting = ss.accept();
                        clients.add(client_connecting);
                        System.out.println("Connected");
                    } catch (IOException ex) {
                        Logger.getLogger(GWServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if(clients.size() == 2){
                        active = false;
                        for(Socket c : clients){
                            try {
                                c.getOutputStream().write(1);
                                System.out.println("written 1");
                            } catch (IOException ex) {
                                Logger.getLogger(GWServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        };
        
        Thread t = new Thread(r);
        t.start();
        
    }
   
    public GWClient start(Stage s){
        GWClient me = new GWClient(s, "localhost");
    
        return me;
    }
    
    
}
