/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

/**
 *
 * @author timber
 */
public abstract class HitBox {
    public abstract int getXCenter();
    public abstract int getYCenter();
    public Point getCenter(){
        return new Point(getXCenter(), getYCenter());
    }
}
