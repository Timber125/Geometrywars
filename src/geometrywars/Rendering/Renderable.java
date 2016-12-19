/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;

/**
 *
 * @author timber
 */
public abstract class Renderable{
    
    public final long ID;
    
    public RenderObject view;
    
    public SimpleIntegerProperty xPos;
    public SimpleIntegerProperty yPos;
    
    public int setX(int xPos){
        this.xPos.setValue(xPos);
        return this.xPos.getValue();
    }
    public int getX(){
        return this.xPos.getValue();
    }
    public int setY(int yPos){
        this.yPos.setValue(yPos);
        return this.yPos.getValue();
    }
    public int getY(){
        return this.yPos.getValue();
    }
    
    public Renderable(long ID){
        xPos = new SimpleIntegerProperty(0);
        yPos = new SimpleIntegerProperty(0);
        this.ID = ID;
    }
    
    public Renderable(long ID, int xPos, int yPos){
        this.xPos = new SimpleIntegerProperty(xPos);
        this.xPos.setValue(xPos);
        this.yPos = new SimpleIntegerProperty(yPos);
        this.yPos.setValue(yPos);
        this.ID = ID;
    }
    
    public abstract void update(ViewPane p);
    public void kill(){
        Engine.engine.removeObject(this);
    }
    public Node getView(){
        return view.getView();
    }
    
}
