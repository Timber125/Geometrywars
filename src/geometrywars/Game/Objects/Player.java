/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

import geometrywars.Game.Logics.ArmorBar;
import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.HealthBar;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Rendering.CircularHitBox;
import geometrywars.Rendering.Collidable;
import geometrywars.Rendering.CollidableImage;
import geometrywars.Rendering.Engine;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.ViewPane;

/**
 *
 * @author timber
 */
public class Player extends MovingCollidableImage{

    private static final String imagelocation = "Shuttle1.png";
    public static int player_diameter = 40;
    private static final HitBox hitbox = new CircularHitBox(player_diameter);
    
    
    
    /*
    
        Properties for the player
    
    */
    
    
    
    public long last_shot = System.currentTimeMillis(); // time when last shot was fired
    private int points = 0;
    
    private ArmorBar armorBar;
    
    public int getPoints(){
        return points;
    }
    public void addPoints(int i){
        points += i;
    }
    public void removePoints(int i){
        points -= i;
    }
    // Implicit : (1000 ms / att_speed) = x ms =? time between System.currentTimeMillis() & last_shot.
    
    
    //private Direction dir = new Direction(0.00, 0.00);
    
    public Player(long ID, long hpID, long armorID, int xPos, int yPos, int xMouse, int yMouse) {
        super(ID, hpID, xPos, yPos, hitbox, imagelocation, new Direction(xPos, yPos, xMouse, yMouse));
        this.speed = 5;
        this.setGun(new SpaceCannon());
        armorBar = new ArmorBar(armorID, 25, 100);
        this.addListener(armorBar);
    }
    
   
    
    @Override
    public void update(ViewPane p) {
        
        // IF player attemts to move out of the bounds, 
        // Deny the attempt!
        
        int oldX = this.getX();
        int oldY = this.getY();
        
        Integer nextX = this.getX() + ((Double)(direction.getXVect() * speed)).intValue();
        Integer nextY = this.getY() + ((Double)(direction.getYVect() * speed)).intValue();
        this.setX(nextX);
        this.setY(nextY);
        
        // If not in view, rollback!
        if(!p.inView(this)){
            //System.err.println("DAMN DUDE");
            this.setX(oldX);
            this.setY(oldY);
        }
        
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.view.getView());
        
    }
    
    public void takeDamage(int dmg){
         if(this.getArmor() > dmg){
                this.setArmor(this.getArmor() - dmg);
                return;
            }
            
            if(this.getArmor() > 0){
                dmg -= this.getArmor();
                this.setArmor(0);
            }
            
            if(this.getHealth() > dmg){
                this.setHealth(this.getHealth()-dmg);
                return;
            }
            
            if(this.getHealth() <= dmg){
                this.setHealth(0);
                Engine.engine.gameOver(false);
                return;
            }
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof Bullet){
            int dmg = ((Bullet)other).getDmg();
            takeDamage(dmg);
           
        }else if(other instanceof Enemy){
            int dmg = ((Enemy)other).getCollideDmg();
            takeDamage(dmg);
        }
    }
    
    @Override
    public void setArmor(int i){
        armorBar.setHP(i);
    }
    
    @Override
    public int getArmor(){
        return armorBar.getHP();
    }
}
