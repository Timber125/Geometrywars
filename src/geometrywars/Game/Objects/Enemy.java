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
    
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename, long cycleTime) {
        super(ID, hpID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = cycleTime;
        createDirectionManager();
    }
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename) {
        super(ID, hpID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = standard_cycletime;
        createDirectionManager();
    }
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename, long cycleTime, int speed, Gun gun) {
        super(ID, hpID, xPos, yPos, hb, filename, new Direction());
        this.cycleTime = cycleTime;
        this.setGun(gun);
        this.speed = speed;
        createDirectionManager();
    }
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename, Direction d) {
        super(ID, hpID, xPos, yPos, hb, filename, d);
        this.cycleTime = standard_cycletime;
    }
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename, Direction d, long cycleTime) {
        super(ID, hpID, xPos, yPos, hb, filename, d);
        this.cycleTime = cycleTime;
    }
    public Enemy(long ID, long hpID, int xPos, int yPos, HitBox hb, String filename, Direction d, long cycleTime, int speed, Gun gun) {
        super(ID, hpID, xPos, yPos, hb, filename, d);
        this.cycleTime = cycleTime;
        this.setGun(gun);
        this.speed = speed;
    }
    public Enemy(long ID, long hpID, HitBox hb, String filename) {
        super(ID, hpID, 0, 0, hb, filename, new Direction());
        this.cycleTime = standard_cycletime;
        Point p = Engine.engine.getSafeSpawnCoord(150);
        this.setX(p.X);
        this.setY(p.Y);
        createDirectionManager();
    }

    private void createDirectionManager() {
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
            Player pl = (Player) Engine.engine.find(1L, 3);
            Point target = new Point(pl.getX(), pl.getY());
            target = AimingIntelligence.aim(target);
            Engine.engine.spawnBullet(this.getX(), this.getY(), new Direction(this.getX(), this.getY(), target.X, target.Y), Enemy.class, this);
        }
        
        
    }
    
    @Override
    public void onCollide(Collidable other){
        if(other instanceof Bullet){
            Bullet b = (Bullet) other;
            int dmg = b.getDmg();
            if(getHealth() <= dmg) {
                Engine.engine.grantPoints(points_on_kill, ((Bullet) other).getOwnerID());
                this.kill();
            }else{
                this.setHealth(this.getHealth() - dmg);
            }
        }else if(other instanceof Ally){
            this.kill();
        }
    }
}
