/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

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
 * @author timber
 */
public abstract class Enemy extends MovingCollidableImage{

    private static final Long standard_cycletime = 3000L;
    
    private Long cycleTime;
    public long last_shot = System.currentTimeMillis(); // time when last shot was fired
    protected int points_on_kill = 1;
    protected int collide_dmg = 50;
    public int getCollideDmg(){
        return collide_dmg;
    }
    public void setCollideDmg(int collideDmg){
        this.collide_dmg = collideDmg;
    }
    
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename, long cycleTime) {
        super(ID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = cycleTime;
        createDirectionManager();
    }
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename) {
        super(ID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = standard_cycletime;
        createDirectionManager();
    }
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename, long cycleTime, int speed, Gun gun) {
        super(ID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = cycleTime;
        this.setGun(gun);
        this.speed = speed;
        createDirectionManager();
    }
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, xPos, yPos, hb, filename, d);
        this.cycleTime = standard_cycletime;
    }
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename, Direction d, long cycleTime) {
        super(ID, xPos, yPos, hb, filename, d);
        this.cycleTime = cycleTime;
    }
    public Enemy(long ID, int xPos, int yPos, HitBox hb, String filename, Direction d, long cycleTime, int speed, Gun gun) {
        super(ID, xPos, yPos, hb, filename, d);
        this.cycleTime = cycleTime;
        this.setGun(gun);
        this.speed = speed;
    }
    public Enemy(long ID, HitBox hb, String filename) {
        super(ID, 0, 0, hb, filename, new Direction());
        this.cycleTime = standard_cycletime;
        Point p = Engine.engine.getSafeSpawnCoord(150);
        this.xPos = p.X;
        this.yPos = p.Y;
        createDirectionManager();
    }

    private void createDirectionManager() {
        this.setDirection(new SimpleDirectionPicker(this, 3000));
    }
    @Override
    public void update(ViewPane p) {
        
        // IF enemy attemts to move out of the bounds, 
        // Give an update to its directionPicker.
        
        int oldX = this.xPos;
        int oldY = this.yPos;
        
        this.xPos += (direction.getXVect() * speed);
        this.yPos += (direction.getYVect() * speed);
        
        // If not in view, rollback!
        // Deprecated because of generalised objectmargin. 
        if(!p.inView(this)){
            //System.err.println("DAMN DUDE");
            this.xPos = oldX;
            this.yPos = oldY;
            ((SimpleDirectionPicker)this.getDirection()).recalculate();
        }
        
        p.getChildren().remove(this.view.getView());
        this.view.getView().relocate(this.xPos, this.yPos);
        p.getChildren().add(this.view.getView());
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof Bullet){
            Engine.engine.grantPoints(points_on_kill);
            this.kill();
        }
    }
}
