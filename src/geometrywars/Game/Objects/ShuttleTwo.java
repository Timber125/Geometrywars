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
import geometrywars.Rendering.Collidable;
import geometrywars.Rendering.Engine;
import geometrywars.Rendering.HitBox;
import geometrywars.Rendering.ViewPane;

/**
 *
 * @author timber
 */
public class ShuttleTwo extends Enemy{

    private static final String imagelocation = "Shuttle8.png";
    public static int shuttle_diameter = 40;
    private static final HitBox hitbox = new CircularHitBox(shuttle_diameter);
    
    // Implicit : (1000 ms / att_speed) = x ms =? time between System.currentTimeMillis() & last_shot.
    
    
    //private Direction dir = new Direction(0.00, 0.00);
    
    public ShuttleTwo(long ID, int xPos, int yPos) {
        super(ID, xPos, yPos, hitbox, imagelocation);
        this.points_on_kill = 100;
        this.speed = 4;
    }
    public ShuttleTwo(long ID) {
        super(ID, hitbox, imagelocation);
        this.points_on_kill = 100;
        this.speed = 4;
    }
    
    
}

