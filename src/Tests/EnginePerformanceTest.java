/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import geometrywars.Game.LevelManager;
import java.util.Random;

/**
 *
 * @author timber
 */
public class EnginePerformanceTest extends LevelManager{
    /*
    USAGE:
        in Engine, make the LevelManager an EnginePerformanceTest. 
        Engine will recognize this by 'instanceof', and will start counting objects. 
    
    */
    
    /*
    FINDINGS:
    
        Engine will handle up to 700 collidable moving objects per second without dropping FPS.
    */
    
    public static int counter = 0;
    
    @Override
    public String spawn(){
        counter ++;
        return "ShuttleTwo";
    }
    
   
}
