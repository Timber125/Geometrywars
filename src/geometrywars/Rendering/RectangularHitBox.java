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
public class RectangularHitBox extends HitBox{
    
    private int width;
    private int height;
    
    public RectangularHitBox(int x, int y){
        this.width=x;
        this.height=y;
    }
    
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    @Override
    public int getXCenter() {
        return (Integer) width/2;
    }

    @Override
    public int getYCenter() {
        return (Integer) height/2;
    }
}
