/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 *
 * @author timber
 */
public class RenderableLabel extends Renderable{
    
    /*public RenderableLabel(long ID){
        super(ID);
        view = new RenderObject<Label>() {};
    }*/
    
    public RenderableLabel(long ID, int xPos, int yPos, String text){
        super(ID, xPos, yPos);
        this.view = new RenderObject();
        this.view.setView(new Label(text));
        ((Label)this.view.getView()).setTextFill(Color.web("#ffff00"));
    }
    
    @Override
    public void update(ViewPane p) {
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.view.getView());
    }
    
    @Override
    public Label getView(){
        return (Label) this.view.getView();
    }
    
    @Override
    public void kill() {
        view.getView().setVisible(false);
    }
    
}
