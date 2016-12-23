/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Rendering.Collidable;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.RectangularHitBox;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author timber
 */
public class Bullet extends MovingCollidableImage{

    private static String filename = "Bullet.png";
    public static final int size = 7;
    public final int bullet_speed = 7;
    private int dmg = 0;
    private long ownerID;
    public int getDmg(){
        return dmg;
    }
    
    private HashMap<Long, Collidable> friendly = new HashMap<>();
    private ArrayList<Class> friendlyClasses = new ArrayList<>();
    
    public Bullet(long ID, int xPos, int yPos, Direction d, int dmg, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collidable friendly, int dmg, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendly.put(friendly.ID, friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collection<Collidable> friendly, int dmg, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        for(Collidable c : friendly){
            this.friendly.put(c.ID, c);
        }
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    public Bullet(long ID, int xPos, int yPos, Direction d, Class friendly, int dmg, long ownerID){
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendlyClasses.add(friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    public Bullet(long ID, int xPos, int yPos, Collection<Class> friendly, Direction d, int dmg, long ownerID){
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendlyClasses.addAll(friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    public Bullet(long ID, int xPos, int yPos, Direction d, int dmg, String filename, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collidable friendly, int dmg, String filename, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendly.put(friendly.ID, friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collection<Collidable> friendly, int dmg, String filename, long ownerID) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        for(Collidable c : friendly){
            this.friendly.put(c.ID, c);
        }
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    public Bullet(long ID, int xPos, int yPos, Direction d, Class friendly, int dmg, String filename, long ownerID){
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendlyClasses.add(friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    public Bullet(long ID, int xPos, int yPos, Collection<Class> friendly, Direction d, int dmg, String filename, long ownerID){
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendlyClasses.addAll(friendly);
        this.speed = bullet_speed;
        this.dmg = dmg;
        this.ownerID = ownerID;
    }
    
    public long getOwnerID(){
        return ownerID;
    }
    
    public boolean isfriendly(Collidable c){
        if (friendly.values().contains(c)) return true;
        for(Class cl : friendlyClasses){
            if(cl.isAssignableFrom(c.getClass())){
                return true;
            }
        }
        return false;
        
    }
    // Bullets kill bullets. 
    @Override
    public void onCollide(Collidable other){
        this.kill();
    }
    /*
    public boolean isfriendly(long id){
        return friendly.keySet().contains(id);
    }*/
    
}
