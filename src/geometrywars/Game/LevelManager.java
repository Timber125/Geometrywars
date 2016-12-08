/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Rendering.Engine;
import java.util.Collection;

/**
 *
 * @author timber
 */
public class LevelManager {
    
    private int current_level = 1;
    
    private final Collection[] enemies = new Collection[3];
    
    
    public LevelManager(){
    }
    
    public double spawnChance(){
        switch(current_level){
            case 1: return (1/90);
            case 2: return (1/60);
            case 3: return (1/30);
            default: {
                System.err.println("[SEVERE] levelmanager level unknown: " + current_level);
                return (1/60);
            }
        }
    }
    
    public Collection<MovingCollidableImage> possibleEnemies(){
        switch(current_level){
            case 1:return null;
            case 2:return null;
            case 3:return null;
            default: {
                System.err.println("[SEVERE] levelmanager level unknown: " + current_level);
                return null;
            }
        }
    }
    
}
