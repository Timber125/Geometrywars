/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Game.Objects.Gun;
import geometrywars.Game.Objects.SpaceLaserGun;
import geometrywars.Rendering.CollidableImage;
import geometrywars.Rendering.Engine;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.ViewPane;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.image.Image;

/**
 *
 * @author timber
 */
public class MovingCollidableImage extends CollidableImage {

    public Direction direction;
    public double speed = 2.00;
    private HealthBar hp ;
    private int armor = 0;
    private Gun gun = new SpaceLaserGun();
    public void setGun(Gun g){
        this.gun = g;
    }
    
    public void setHealth(int i){
        this.hp.setHP(i);
    }
    
    public void setArmor(int i){
        this.armor = i;
    }
    
    public Gun getGun(){return gun;}
    public int getHealth(){return this.hp.getHP();}
    public int getArmor(){return armor;}
    
    public MovingCollidableImage(long ID, Long hpID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, xPos, yPos, hb, filename);
        this.direction = d;
        this.hp = new HealthBar(hpID, 100);
        this.addListener(hp);
    }
    public MovingCollidableImage(long ID, Long hpID, int xPos, int yPos, HitBox hb, Image image, Direction d) {
        super(ID, xPos, yPos, hb, image);
        this.direction = d;
        this.hp = new HealthBar(hpID, 100);
        this.addListener(hp);
    }
     public MovingCollidableImage(long ID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, xPos, yPos, hb, filename);
        this.direction = d;
        this.hp = new HealthBar(100);
    }
    public MovingCollidableImage(long ID, int xPos, int yPos, HitBox hb, Image image, Direction d) {
        super(ID, xPos, yPos, hb, image);
        this.direction = d;
        this.hp = new HealthBar(100);
    }
    
    
    @Override
    public void update(ViewPane p) {
         
        this.setX(this.getX() + ((Double) (direction.getXVect() * speed)).intValue());
        this.setY(this.getY() + ((Double) (direction.getYVect() * speed)).intValue());
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.view.getView());
       
        
    }
    
  
    @Override
    public void kill(){
        Engine.engine.removeObject(this);
        if(hp.ID != 0L) Engine.engine.removeObject(hp);
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
