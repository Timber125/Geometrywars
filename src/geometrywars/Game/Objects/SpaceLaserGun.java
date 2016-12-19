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
public class SpaceLaserGun extends Gun{
    // Standard Bullet, does not need its own bullet. 
    public SpaceLaserGun(){
        super();
        this.dmg = 40;
        this.name = "space laser";
        this.att_speed = 0.5;
    }
}
