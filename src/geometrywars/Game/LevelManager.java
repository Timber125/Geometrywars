/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Rendering.Engine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 *
 * @author timber
 */
public class LevelManager {
    private boolean debug = false;
    private int current_level = 1;
    
    private final ArrayList<String>[] enemies = new ArrayList[3];
    
    public long[] levelTimes = new long[3];
    
    public LevelManager(){
        enemies[0] = new ArrayList<>();
        enemies[0].add("ShuttleTwo");
        enemies[0].add("ShuttleThree");
        
        enemies[1] = new ArrayList<>();
        enemies[1].add("ShuttleTwo");
        
        enemies[2] = new ArrayList<>();
        enemies[2].add("ShuttleThree");
        
        levelTimes[0] = 60000L;
        levelTimes[1] = 60000L;
        levelTimes[2] = 120000L;
        
        if(debug){
            levelTimes[0] = 20000L;
            levelTimes[1] = 20000L;
            levelTimes[2] = 30000L;
        }
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
    
    public int spawnChance_oneInX(){
         switch(current_level){
            case 1: return (90);
            case 2: return (60);
            case 3: return (30);
            default: {
                System.err.println("[SEVERE] levelmanager level unknown: " + current_level);
                return (60);
            }
        }
    }
    
    public String spawn(){
        Random r = new Random();
        int succes = 1;
        int picked = r.nextInt(spawnChance_oneInX());
        if(picked == succes){
            int pickedEnemy = r.nextInt(possibleEnemies().size());
            return possibleEnemies().get(pickedEnemy);
        }else{
            return "none";
        }
    }
    public String spawn(boolean needs_enemy_forsure){
        if(!needs_enemy_forsure) return spawn();
        Random r = new Random();
        int pickedEnemy = r.nextInt(possibleEnemies().size());
        return possibleEnemies().get(pickedEnemy);
    }
    
    public ArrayList<String> possibleEnemies(){
        if(current_level > 3){
            System.out.println("[SEVERE] levelmanager level unknown: " + current_level);
            return null;
        }else if(current_level < 0){
            System.out.println("[SEVERE] levelmanager level unknown: " + current_level);
            return null;
        }else return enemies[current_level-1];
    }
    
    public synchronized void levelUp(){
        this.current_level ++;
        if(this.current_level > 3) {
            Engine.engine.gameOver(true);
            current_level = 1;
        }
        
    }
    
    public long getLevelTime(){
        return levelTimes[current_level-1];
    }
    public long getLevelTime(int level){
        return levelTimes[level-1];
    }
    
    public int getLevel(){
        return current_level;
    }
}
