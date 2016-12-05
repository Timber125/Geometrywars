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

    private static final String filename = "Bullet.png";
    public static final int size = 7;
    public final int bullet_speed = 7;
    
    private HashMap<Long, Collidable> friendly = new HashMap<>();
    
    public Bullet(long ID, int xPos, int yPos, Direction d) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.speed = bullet_speed;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collidable friendly) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        this.friendly.put(friendly.ID, friendly);
        this.speed = bullet_speed;
    }
    
    public Bullet(long ID, int xPos, int yPos, Direction d, Collection<Collidable> friendly) {
        super(ID, xPos, yPos, new RectangularHitBox(size, size), filename, d);
        for(Collidable c : friendly){
            this.friendly.put(c.ID, c);
        }
        this.speed = bullet_speed;
    }
    
    public boolean isfriendly(Collidable c){
        return friendly.values().contains(c);
    }
    
    public boolean isfriendly(long id){
        return friendly.keySet().contains(id);
    }
    
}
