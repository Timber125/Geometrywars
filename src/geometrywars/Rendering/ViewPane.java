/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author timber
 */
public class ViewPane extends AnchorPane{
    
    public static final int fieldWidth = 700;
    public static final int fieldHeight = 700;
    
    private final int killObjectMargin = 100; // margin in which the objects virtually fly through before being picked up by the "garbage collector" in the engine.
    
    public ViewPane(){
        this.setPrefSize(fieldWidth, fieldHeight);
        
        /* Remove to "prove" that the engine's garbagecollector works */
        this.setWidth(fieldWidth);
        this.setHeight(fieldHeight);
        this.setMaxHeight(fieldHeight);
        this.setMaxWidth(fieldWidth);
        this.setMinHeight(fieldHeight);
        this.setMinWidth(fieldWidth);
        
        this.setStyle("-fx-background-color:#000000");
    }
    
    public boolean inBounds(Renderable r){
        int x = r.xPos;
        int y = r.yPos;
        
        return (((x > (0-killObjectMargin)) && (x < (fieldWidth+killObjectMargin))) && ((y > (0-killObjectMargin))&&(y < (fieldHeight+killObjectMargin))));
    }
    // No killObjectMargin!
    public boolean inView(Renderable r){
        int x = r.xPos;
        int y = r.yPos;
        
        return (((x > (0)) && (x < (fieldWidth))) && ((y > (0))&&(y < (fieldHeight))));
    }
}
