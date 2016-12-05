/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Control;

import java.beans.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author timber
 */
public class InputHandler {

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;
    private boolean enter = false;
    
    public InputHandler(Scene sc){
       
        
        sc.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            switch(key.getCode()){
                case ENTER :{
                    // link to pause?
                    enter = true;
                    break;
                }
                case UP:{
                    //uff.UP();
                    down=false;
                    up=true;
                    break;
                }
                case DOWN:{
                    //buff.DOWN();
                    up=false;
                    down=true;
                    break;
                }
                case LEFT:{
                    right=false;
                    left=true;
                    break;
                }
                case RIGHT:{
                    left=false;
                    right=true;
                    break;
                }
                default:{
                    break;
                }
            }
            
        });
        sc.addEventHandler(KeyEvent.KEY_RELEASED, (key) -> {
            switch(key.getCode()){
                case ENTER :{
                    // link to pause?
                    enter = false;
                    break;
                }
                case UP:{
                    up=false;
                    break;
                }
                case DOWN:{
                    down=false;
                    break;
                }
                case LEFT:{
                    left=false;
                    break;
                }
                case RIGHT:{
                    right=false;
                    break;
                }
                default:{
                    break;
                }
            }
            
        });
    }
    
    public synchronized InputBuffer getBuffer(){
        boolean pause = enter;
        enter = false;
        return new InputBuffer(up,down,left,right,pause);
    }
    
}
