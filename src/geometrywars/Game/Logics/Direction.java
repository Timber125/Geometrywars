/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Renderable;

/**
 *
 * @author timber
 */
public class Direction {
    protected double xVect = 0.00;
    protected double yVect = 0.00;
    public Direction(){
        
    }
    public Direction(double dX, double dY){
        this.xVect = dX;
        this.yVect = dY;
    }
    
    public Direction(int xFrom, int yFrom, int xTo, int yTo){
        // get Delta
        
        int dX = xTo - xFrom;
        int dY = yTo - yFrom;
        
        // Normalize
        if(Math.pow(dX, 2) + Math.pow(dY, 2) <= 0) {
            xVect = 0.00;
            yVect = 0.00;
        }else{
            double magnitude = Math.sqrt(Math.pow(dX, 2) + Math.pow(dY, 2));
        
            // Assign
        
            //xVect = dX / magnitude;
            //yVect = dY / magnitude;
            double xd = dX / magnitude;
            double yd = dY / magnitude;
            double total = Math.abs(xd) + Math.abs(yd);
            
            xVect = xd / total;
            yVect = yd / total;
            
        }
        
    }
    
    public static Direction update(Renderable r, int xTo, int yTo){
        return new Direction(r.xPos, r.yPos, xTo, yTo);
    }
    
    public static Direction create(boolean NORTH, boolean EAST, boolean SOUTH, boolean WEST){
        return new Direction(0, 0, ((EAST) ? (50) : ((WEST) ? (-50) : 0)), ((SOUTH) ? (50) : ((NORTH) ? (-50) : 0)));
    }
    
    public double getXVect(){
        return xVect;
    }
    public double getYVect(){
        return yVect;
    }
   
}
