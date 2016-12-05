/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Control;


/**
 *
 * @author timber
 */
public class InputBuffer {
    
    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;
    public boolean enter = false;
    
    public InputBuffer(){
        
    }
    public InputBuffer(boolean up, boolean down, boolean left, boolean right, boolean enter){
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.enter = enter;
    }
    
    public void UP(){
        down = false;
        up = true;
    }
    
    public void DOWN(){
        up = false;
        down = true;
    }
    
    public void LEFT(){
        right = false;
        left = true;
    }
    
    public void RIGHT(){
        left = false;
        right = true;
    }
    
    public void ENTER(){
        enter = true;
    }
    
    public String toString(){
        String txt = "";
        if(up) txt+="N";
        else if(down) txt+="S";
        
        if(left) txt+="W";
        else if(right) txt+="E";
        
        if(enter) txt += " [paused]";
        return txt;
    }
}
