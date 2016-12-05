/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.CollidableImage;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.ViewPane;
import javafx.scene.image.Image;

/**
 *
 * @author timber
 */
public class MovingCollidableImage extends CollidableImage{

    public Direction direction;
    public double speed = 2.00;
    
    public MovingCollidableImage(long ID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, xPos, yPos, hb, filename);
        this.direction = d;
    }
    public MovingCollidableImage(long ID, int xPos, int yPos, HitBox hb, Image image, Direction d) {
        super(ID, xPos, yPos, hb, image);
        this.direction = d;
    }
    
    
    @Override
    public void update(ViewPane p) {
        this.xPos += (direction.getXVect() * speed);
        this.yPos += (direction.getYVect() * speed);
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.xPos, this.yPos);
        p.getChildren().add(this.view.getView());
    }
    
    public void setDirection(Direction dir){
        this.direction = dir;
    }
    public Direction getDirection(){
        return this.direction;
    }
    
    
    
    public double getSpeed(){
        return this.speed;
    }
    public void setSpeed(double spd){
        this.speed = spd;
    }
}
