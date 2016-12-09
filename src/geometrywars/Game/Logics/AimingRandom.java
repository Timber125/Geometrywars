/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Point;
import geometrywars.Rendering.ViewPane;
import java.util.Random;

/**
 *
 * @author timber
 */
public class AimingRandom extends AimingIntelligence{

    @Override
    public Point getAimPoint(Point target) {
        Random r = new Random();
        Point p = new Point(r.nextInt(ViewPane.fieldWidth), r.nextInt(ViewPane.fieldHeight));
        return p;
    }
    
}
