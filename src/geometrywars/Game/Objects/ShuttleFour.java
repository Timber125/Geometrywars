/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Game.Logics.SimpleDirectionPicker;
import geometrywars.Rendering.CircularHitBox;
import geometrywars.Rendering.HitBox;

/**
 *
 * @author timber
 */
public class ShuttleFour extends Ally{
    private static final String imagelocation = "Shuttle11.png";
    public static int shuttle_diameter = 40;
    private static final HitBox hitbox = new CircularHitBox(shuttle_diameter);
    
    // Implicit : (1000 ms / att_speed) = x ms =? time between System.currentTimeMillis() & last_shot.
    
    
    //private Direction dir = new Direction(0.00, 0.00);
    
    public ShuttleFour(long ID, long hpID, int xPos, int yPos) {
        super(ID, hpID, xPos, yPos, hitbox, imagelocation, new Direction());
        this.speed = 4;
        this.setGun(new RebelSpaceGun());
        this.setHealth(1000);
        this.createDirectionManager();
    }
    
    
    
    
    
}
