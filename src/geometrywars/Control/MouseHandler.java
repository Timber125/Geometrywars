/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Control;

import geometrywars.Rendering.Engine;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author timber
 */
public class MouseHandler {
    private boolean debugmode = false;

    private MouseLocation location = new MouseLocation(0,0,0,0);
    public MouseHandler(Scene monitored){
        monitored.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent event) {
        String msg =
          "(x: "       + event.getX()      + ", y: "       + event.getY()       + ") -- " +
          "(sceneX: "  + event.getSceneX() + ", sceneY: "  + event.getSceneY()  + ") -- " +
          "(screenX: " + event.getScreenX()+ ", screenY: " + event.getScreenY() + ")";

        if(debugmode) System.out.println(msg);
        
        int sceneX = (int) event.getSceneX();
        int sceneY = (int) event.getSceneY();
        int screenX = (int) event.getScreenX();
        int screenY = (int) event.getScreenY();
        location = new MouseLocation(sceneX, sceneY, screenX, screenY);
      }
    });

    monitored.setOnMouseExited(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent event) {
        if(debugmode) System.out.println("MOUSE OUTSIDE SCENE");
      }
    });
    }
    public MouseLocation getLocation(){
        return location;
    }
}
