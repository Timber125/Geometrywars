/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.Engine;
import geometrywars.Rendering.Point;

/**
 *
 * @author timber
 */
public abstract class AimingIntelligence {
    public abstract Point getAimPoint(Point target);
    
    private static AimingIntelligence get(){
        switch(Engine.engine.getDifficulty()){
            case 0: return new AimingRandom();
            case 1: return new AimingAllright();
            case 2: return new AimingGood();
            case 3: return new AimingPerfect();
            default: {
                System.err.println("[SEVERĒ] difficulty not recognized : " + Engine.engine.getDifficulty());
                return new AimingAllright();
            }
        }
    }
    
    public static Point aim(Point target){
        return get().getAimPoint(target);
    }
}
