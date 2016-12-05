/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import geometrywars.Game.Logics.Direction;

/**
 *
 * @author timber
 */
public class CircularHitBox extends HitBox{
    // Since parent positions X and Y will be the most upperleft coordinate, 
    // we put in the "mathematics" to handle this easily. 
    
    // We ask for diameter, which whill be equal to both width and height, if the parent is defined correctly.
    private int diameter;
    public CircularHitBox(int diameter){
        this.diameter = diameter;
    }
    public int getDiameter(){
        return diameter;
    }
    public int getR(){
        return (int) Math.ceil(diameter/2);
    }

    @Override
    public int getXCenter() {
        return ((Double) Math.floor(diameter / 2)).intValue() ;
    }

    @Override
    public int getYCenter() {
        return ((Double) Math.floor(diameter / 2)).intValue()  ;
    }
    
    
}
