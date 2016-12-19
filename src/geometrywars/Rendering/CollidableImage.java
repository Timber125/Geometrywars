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
public class CollidableImage extends Collidable{

    public CollidableImage(long ID, int xPos, int yPos, HitBox hb, Image i) {
        super(ID, xPos, yPos, hb);
        this.view = new RenderObject();
        this.view.view = new ImageView(i);
    }
    
    public CollidableImage(long ID, int xPos, int yPos, HitBox hb, String filename){
        super(ID, xPos, yPos, hb);
        this.view = new RenderObject();
        this.view.view = new ImageView();
        
        URL resource = Geometrywars.class.getResource("/geometrywars/Resources/" + filename);
        Image i = new Image(resource.toString());

        ((ImageView) this.view.getView()).setImage(i);
    }
    
    @Override
    public void update(ViewPane p) {
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.view.getView());
    }
    
    @Override
    public ImageView getView(){
        return (ImageView) view.getView();
    }

    @Override
    public void onCollide(Collidable other) {
        System.out.println("CollidableImage [" + ID + "] collided with [" + other.ID + "]");
    }

    
    
     @Override
    public Point getCenter() {
        int x = this.getX() + (this.getHitBox().getXCenter());
        int y = this.getY() + (this.getHitBox().getYCenter());
        return new Point(x,y);
    }
    
    
    
}
