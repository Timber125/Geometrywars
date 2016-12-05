/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Control;

/**
 *
 * @author timber
 */
public class MouseLocation {
    public int sceneX;
    public int sceneY;
    public int screenX;
    public int screenY;
    public MouseLocation(int sceneX, int sceneY, int screenX, int screenY){
        this.sceneX = sceneX;
        this.sceneY = sceneY;
        this.screenX = screenX;
        this.screenY = screenY;
    }
}
