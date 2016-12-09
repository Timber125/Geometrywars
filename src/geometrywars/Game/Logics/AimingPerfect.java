/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Point;

/**
 *
 * @author timber
 */
public class AimingPerfect extends AimingIntelligence{
    @Override
    public Point getAimPoint(Point target) {
        return target;
    }
}
