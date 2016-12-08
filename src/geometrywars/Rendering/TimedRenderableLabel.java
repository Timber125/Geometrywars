/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author timber
 */
public class TimedRenderableLabel extends RenderableLabel{

    
    public TimedRenderableLabel(long ID, int xPos, int yPos, String text, Long millisToStay) {
        super(ID, xPos, yPos, text);
        Thread t = new Thread(timer(millisToStay));
        t.start();
    }
    
    private Runnable timer(long millis){
        return new Runnable(){

            @Override
            public void run() {
                try {
                    Thread.sleep(millis);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TimedRenderableLabel.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Threading issues workaround
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        Engine.engine.removeObject(ID);
                    }
                    
                });
            }
        
        };
        
    }
    
}
