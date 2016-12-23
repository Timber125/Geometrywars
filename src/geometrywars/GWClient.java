package geometrywars;


import geometrywars.Rendering.Engine;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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
public class GWClient {
    Socket s ;
    Stage actingStage;
    boolean alive = true;
    public GWClient(Stage actingStage, String ip){
        try {
            s = new Socket(ip, 8085);
        } catch (IOException ex) {
            Logger.getLogger(GWClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.actingStage = actingStage;
        
        Runnable r = new Runnable(){

            @Override
            public void run() {
                while(alive){
                    try {
                        int b = s.getInputStream().read();
                        System.out.println(b);
                    } catch (IOException ex) {
                        Logger.getLogger(GWClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            
        };
        Thread listener = new Thread(r);
        listener.start();
    }
    
    public void init(){
         Pane root = new Pane();
         // Get server's viewpane
         root.getChildren().add(Engine.engine.getViewPane());
         Scene scene = new Scene(root, 800, 800);
         actingStage.setTitle("Geometrywars");
         actingStage.setScene(scene);
         actingStage.show();
    }
    
}
