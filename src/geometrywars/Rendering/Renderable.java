/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import javafx.scene.Node;

/**
 *
 * @author timber
 */
public abstract class Renderable{
    
    public final long ID;
    
    public RenderObject view;
    
    public int xPos;
    public int yPos;
    
    public Renderable(long ID){
        this.xPos = 0;
        this.yPos = 0;
        this.ID = ID;
    }
    
    public Renderable(long ID, int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.ID = ID;
    }
    
    public abstract void update(ViewPane p);
    public abstract void kill();
    public abstract Node getView();
    
}
