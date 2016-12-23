/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Game.Objects;

import geometrywars.Game.Logics.AimingIntelligence;
import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Game.Logics.SimpleDirectionPicker;
import geometrywars.Rendering.Collidable;
import geometrywars.Rendering.Engine;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.Point;
import geometrywars.Rendering.ViewPane;

/**
 *
 * @author admin
 */
public class Ally extends MovingCollidableImage{

    long last_shot = System.currentTimeMillis();
    
    public Ally(long ID, Long hpID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, hpID, xPos, yPos, hb, filename, d);
    }
     protected void createDirectionManager() {
        this.setDirection(new SimpleDirectionPicker(this, 3000));
    }
     
    @Override
    public void update(ViewPane p) {
        
        // IF enemy attemts to move out of the bounds, 
        // Give an update to its directionPicker.
        
        int oldX = this.getX();
        int oldY = this.getY();
        
        this.setX(this.getX() + ((Double) (direction.getXVect() * speed)).intValue());
        this.setY(this.getY() + ((Double) (direction.getYVect() * speed)).intValue());
        
        // If not in view, rollback!
        // Deprecated because of generalised objectmargin. 
        if(!p.inView(this)){
            //System.err.println("DAMN DUDE");
            this.setX(oldX);
            this.setY(oldY);
            ((SimpleDirectionPicker)this.getDirection()).recalculate();
        }
        
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.view.getView());
        
        
        // Shooting part:
        
        long now = System.currentTimeMillis();
        long treshold_delta = now - last_shot;
        
        if(treshold_delta > this.getGun().getSpeedAsTresholdTime()){
            // SHOOT!
            last_shot = System.currentTimeMillis();
            
            Point target = Engine.engine.getSafeSpawnCoord(300);
            target = AimingIntelligence.aim(target);
            Engine.engine.spawnBullet(this.getX(), this.getY(), new Direction(this.getX(), this.getY(), target.X, target.Y), Ally.class, this);
        }
        
        
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof Bullet){
            Bullet b = (Bullet) other;
            int dmg = b.getDmg();
            if(getHealth() <= dmg) {
                this.kill();
            }else{
                this.setHealth(this.getHealth() - dmg);
            }
        }else if(other instanceof Enemy){
            int dmg = ((Enemy)other).collide_dmg;
            if(getHealth() <= dmg) {
                this.kill();
            }else{
                this.setHealth(this.getHealth() - dmg);
            };
        }
    }
}
