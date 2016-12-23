/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

import geometrywars.Game.Logics.Direction;
import geometrywars.Rendering.Collidable;
import java.util.Collection;

/**
 *
 * @author timber
 */
public abstract class Gun {
    protected int dmg = 10;
    protected String name = "space laser";
    protected double att_speed = 2.5; // bullets persecond
    public int getDmg(){
        return dmg;
    }
    public String getName(){
        return name;
    } 
    public double getSpeed(){
        return att_speed;
    }
    public Long getSpeedAsTresholdTime(){
        Double asDouble = 1000 / att_speed;
        Long asLong = asDouble.longValue();
        return asLong;
    }
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, long ownerID){
        return new Bullet(ID, xPos, yPos, d, this.dmg, ownerID);
    }
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Collection<Class> friendly, long ownerID){
        return new Bullet(ID, xPos, yPos, friendly, d, this.dmg, ownerID);
    }
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Class friendly, long ownerID){
        return new Bullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    public Bullet createBullet(long ID, int xPos, int yPos, Collection<Collidable> friendly, Direction d, long ownerID){
        return new Bullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    public Bullet createBullet(long ID, int xPos, int yPos, Collidable friendly, Direction d, long ownerID){
        return new Bullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    
}
