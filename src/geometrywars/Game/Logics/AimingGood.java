/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Point;
import java.util.Random;

/**
 *
 * @author timber
 */
public class AimingGood extends AimingIntelligence{

    
    private int diversion = 40;
    @Override
    public Point getAimPoint(Point target) {
        Random r = new Random();
        
        int randX = r.nextInt(diversion);
        int randY = r.nextInt(diversion);
        
        randX -= diversion/2;
        randY -= diversion/2;
        
        int x = randX + target.X;
        int y = randY + target.Y;
        
        return new Point(x,y);
    }
    
    
}
