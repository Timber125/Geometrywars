/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import geometrywars.Geometrywars;
import java.net.URL;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author timber
 */
public class ViewPane extends AnchorPane{
    private static final String backgroundImage = "SpaceBG1.jpg";
    public static final int fieldWidth = 700;
    public static final int fieldHeight = 700;
    
    @Deprecated    
    private final int objectMargin = 40; // margin in which the xPos / yPos will exceed the viewpane bounds
    // Note that this should be variable for each object, this should change.
    @Deprecated 
    public Point getObjectBounds(){
        return new Point(fieldWidth - objectMargin, fieldHeight - objectMargin);
    }
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
        URL resource = Geometrywars.class.getResource("/geometrywars/Resources/" + backgroundImage);
        String image = resource.toExternalForm();
        this.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");
        
        

    }
    
    public boolean inBounds(Renderable r){
        int x = r.xPos;
        int y = r.yPos;
        
        return (((x > (0-killObjectMargin)) && (x < (fieldWidth+killObjectMargin))) && ((y > (0-killObjectMargin))&&(y < (fieldHeight+killObjectMargin))));
    }
    // No killObjectMargin!
    @Deprecated
    public boolean inView(Renderable r){
        int x = r.xPos;
        int y = r.yPos;
        
        return (((x > (0)) && (x < (fieldWidth - objectMargin))) && ((y > (0))&&(y < (fieldHeight - objectMargin))));
    }
}
