/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

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
    
}
