/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Rendering;

import java.util.ArrayList;
import javafx.scene.Node;

/**
 *
 * @author admin
 */
public abstract class ObservedRenderable extends Renderable{

    private ArrayList<Renderable> listeners;
    
    
    
    public ObservedRenderable(long ID, int xPos, int yPos) {
        super(ID, xPos, yPos);
        listeners = new ArrayList<>();
    }
    
    public boolean addListener(Renderable lr){
        return listeners.add(lr);
    }
    
    public boolean removeListener(Renderable lr){
        return listeners.remove(lr);
    }
   
    @Override
    public int setX(int x){
        this.xPos.setValue(x);
        fireXChange(x);
        return this.xPos.getValue();
    }
    
    @Override
    public int setY(int y){
        this.yPos.setValue(y);
        fireYChange(y);
        return this.yPos.getValue();
    }
    
    private void fireXChange(int newXPos){
        for(Renderable lr : listeners){
            lr.setX(newXPos);
        }
    }
    
    private void fireYChange(int newYPos){
        for(Renderable lr : listeners){
            lr.setY(newYPos);
        }
    }
  
}
