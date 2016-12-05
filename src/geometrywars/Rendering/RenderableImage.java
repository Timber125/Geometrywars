/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import geometrywars.Geometrywars;
import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author timber
 */
public class RenderableImage extends Renderable{
    

    public RenderableImage(long ID, int xPos, int yPos, Image i) {
        super(ID, xPos, yPos);
        this.view = new RenderObject();
        this.view.view = new ImageView(i);
    }
    
    public RenderableImage(long ID, int xPos, int yPos, String filename){
        super(ID, xPos, yPos);
        this.view = new RenderObject();
        this.view.view = new ImageView();
        
        URL resource = Geometrywars.class.getResource("/geometrywars/Resources/" + filename);
        Image i = new Image(resource.toString());

        ((ImageView) this.view.getView()).setImage(i);
    }

    @Override
    public void update(ViewPane p) {
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.xPos, this.yPos);
        p.getChildren().add(this.view.getView());
    }
    
    @Override
    public void kill() {
        view.getView().setVisible(false);
    }
    
    @Override
    public ImageView getView(){
        return (ImageView) view.getView();
    }

    
    // No width is specified for Renderables, only Collidables will have
    // a meaningful getCenter function. 
    // The reason this is implemented in Renderables,
    // Is to not having to cast all renderables we get in the engine to collidable before
    // Trying to get a center function. 
    
    // It is only collidables that will need this function anyway. 
    // Then again, we COULD check if an object is renderable or collidable by checking the center 
    // against xPos and yPos.
    
}
