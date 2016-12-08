/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Renderable;
import geometrywars.Rendering.ViewPane;
import java.util.Random;

/**
 *
 * @author timber
 */
public class SimpleDirectionPicker extends DirectionManager{
    
    private boolean debug = false;
    
    private long timeSinceLastChange;
    private long cycleTime;
    
    public SimpleDirectionPicker(Renderable subject, long cycleTime){
        this.subject = subject;
        this.cycleTime = cycleTime;
        pickDirection();
        timeSinceLastChange = System.currentTimeMillis();
    }
    private void checkValidity(){
        long now = System.currentTimeMillis();
        if((now - timeSinceLastChange) >= cycleTime) pickDirection();
    }
    private void pickDirection(){
        Random r = new Random();
        int toX = r.nextInt(ViewPane.fieldWidth);
        int toY = r.nextInt(ViewPane.fieldHeight);
        Direction d = new Direction(subject.xPos, subject.yPos, toX, toY);
        this.xVect = d.getXVect();
        this.yVect = d.getYVect();
        timeSinceLastChange = System.currentTimeMillis();
        if(debug) System.out.println("Changed direction: [" + xVect + "] [" + yVect + "] (func: " + test() + ")" );
    }
    
    private double test(){
        return Math.abs(xVect) + Math.abs(yVect);
    }
    
    @Override
    public double getXVect(){
        return xVect;
    }
    
    @Override
    public double getYVect(){
        checkValidity();
        return yVect;
    }

    public void recalculate() {
        pickDirection();
    }
}
