/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import geometrywars.Control.InputBuffer;
import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Game.Logics.SimpleDirectionPicker;
import geometrywars.Game.Objects.Bullet;
import geometrywars.Game.Objects.Player;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 *
 * @author timber
 */
public class Engine {
    
    
    private boolean global_debugmode = false;
    /* 
    
        Ideally we don't want to check for collisions with non-collideable objects.
        We also do not need to check background images (moving stars) or foreground images (score & game information) agains collisions. 
        This is why we will use 4 "layers" of rendering. 
        
        Layer 0: Background only
        Layer 1: Game objects non-collideable
        Layer 2: Game objects collideable ==> Will be checked for collision
        Layer 3: Foreground game information
    
    */
    
    
    
    public HashMap<Long, Renderable> background_layer0 = new HashMap<>();
    public HashMap<Long, Renderable> game_nocollision_layer1 = new HashMap<>();
    public HashMap<Long, Renderable> game_collision_layer2 = new HashMap<>();
    public HashMap<Long, Renderable> foreground_layer3 = new HashMap<>();
    
    private boolean paused = false;
    
    // Look in all hashmaps with or without suspected layer given
    public Renderable find(Long id){
        return find(id, 2);
    }
    public Renderable find(Long id, int suspected_layer){
        for(int i = 0; i < 4; i++){
            Renderable test = search((i+suspected_layer)%4, id);
            if(test != null) return test;
        }
        return null;
    }
    
    private Renderable search(int layer, long id){
        switch(layer){
            case(0): return background_layer0.get(id);
            case(1): return game_nocollision_layer1.get(id);
            case(2): return game_collision_layer2.get(id);
            case(3): return foreground_layer3.get(id);
            default: return null;
        }
    }
    
    private HashMap locate(Long id){
        if(background_layer0.containsKey(id)) return background_layer0;
        if(game_nocollision_layer1.containsKey(id)) return game_nocollision_layer1;
        if(game_collision_layer2.containsKey(id)) return game_collision_layer2;
        if(foreground_layer3.containsKey(id)) return foreground_layer3;
        return null;
    }
    
    public enum RenderLevel {
        BACKGROUND(0), 
        NO_COLLIDE(1), 
        COLLIDE(2), 
        FOREGROUND(3)
        ;
        private int numVal;

    RenderLevel(int numVal) {
        this.numVal = numVal;
    }

    public int value() {
        return numVal;
    }
    };
    
    private long idcounter = 11; // leaves some space for at least 10 static (test) instances e.g. walls, doesnt hurt at the very least. 
    /*
        RESERVED IDSLOTS
    
        ID 1L = player1
        ID 2L = player2
    */
    
    
    private Thread gameloopthread;            
    private boolean active = false;
    
    public static Engine engine = new Engine();
    
    public ViewPane view = new ViewPane();
    
    public ViewPane getViewPane(){
        return view;
    }
    
    private InputHandler inputhandler;
    private MouseHandler mousehandler;
    
    public void setControls(InputHandler ih, MouseHandler mh){
        this.inputhandler = ih;
        this.mousehandler = mh;
    }
    
    private Player findPlayer() {
        Renderable player = find(1L, 2);
        if(player instanceof Player){
            Player p1 = (Player) player;
            return p1;
        }else{
            System.err.println("[FATAL] ID 1L IS NOT AN INSTANCE OF PLAYER!");
            System.err.flush();
            System.exit(2);
            return null;
        }
    }
    
    
    private void actOnInput(Player p1){
         InputBuffer buff = inputhandler.getBuffer();
         if(global_debugmode) System.out.println("Moving player in direction [" + buff.toString() + "]");
         p1.setDirection(Direction.create(buff.up, buff.right, buff.down, buff.left));
         if(buff.enter) {
             paused = !paused;
             System.out.println("enter pressed");
         }
    }
    
    private void shootPlayer(Player pl){
         long now = System.currentTimeMillis();
         long lastShot = pl.last_shot;
         long treshold = Math.round(1000 / pl.att_speed);
         long delta = now - pl.last_shot; // > 0
         
         if(treshold < delta){
             // SHOOT
             pl.last_shot = now;
             Point player_center = pl.getCenter();
             if(global_debugmode) System.out.println("PLAYER X Y [" + pl.xPos + "] [" + pl.yPos + "]");
             if(global_debugmode) System.out.println("CENTER X Y [" + player_center.X + "] [" + player_center.Y + "]");
             spawnBullet(player_center.X, player_center.Y, new Direction(player_center.X, player_center.Y, mousehandler.getLocation().sceneX, mousehandler.getLocation().sceneY), pl);
         }else{
             // DONT SHOOT, WAIT
         }
    }
    
    /*
        Always access the next id this way!
        Lock is NOT necessary: since synchronized & oneliner -> assembly can't be interrupted
        writing a lock here is overhead.
    */
    private synchronized long getNextIdSafely(){
        return idcounter ++;
    }
    
    /*
        Return runnable from the engine itself - creating a runnable in an inner instance
        does not permit access to the engine functions itself without a forced reference.
    
    */
    
    private Runnable createGameLoop(int fps){
        Runnable r = new Runnable(){

            @Override
            public void run() {
                while(active){
                    
                    Platform.runLater(new Runnable(){

                        @Override
                        public void run() {
                            Player p = findPlayer();
                            actOnInput(p);
                            
                            if(!paused){
                                shootPlayer(p);
                                checkCollisions();
                                updateAllObj();
                            }
                        }

                       
                        
                    });
                    try {
                        Thread.sleep((1000 / fps));
                    } catch (InterruptedException ex) {
                        System.err.println(ex);
                    }
                }
            }
            
        };
        return r;
    }
    
    
    
    /*
        update all instances on the screen   
    */
    
    private void updateAllObj(){
        boolean debugmode_updates = global_debugmode;
        Collection<Renderable>[] layers = new Collection[4];
        layers[0] = new ArrayList<>();
        layers[0].addAll(background_layer0.values());
        layers[1] = new ArrayList<>();
        layers[1].addAll(game_nocollision_layer1.values());
        layers[2] = new ArrayList<>();
        layers[2].addAll(game_collision_layer2.values());
        layers[3] = new ArrayList<>();
        layers[3].addAll(foreground_layer3.values());
        
        for(int i = 0; i < 4; i++){
            for (Renderable r : layers[i]){
            
                if(view.inBounds(r)) r.update(view);
                else removeObject(r);
            
                if(debugmode_updates){
                    System.out.println("[renderlvl " + i + "] Updated " + r.getClass() + " with id " + r.ID);
                    System.out.flush();
                }
            
            }   
        }
        
    }
    
    
    private void removeObject(Renderable r){
        Long id = r.ID;
        r.kill();
        view.getChildren().remove(r.getView());
        locate(id).remove(id);        
    }
    
    private void checkCollisions(){
        ArrayList<Renderable> collidelayer = new ArrayList<>();
        collidelayer.addAll(game_collision_layer2.values());
        
        int size = collidelayer.size();
        
        for(int i = 0; i < size; i++){
            Renderable r1 = collidelayer.get(i);
            Collidable c1 = null;
            if(r1 instanceof Collidable) c1 = (Collidable) r1;
            else continue;
            for(int j = i+1; j < size; j++){
                Renderable r2 = collidelayer.get(j);
                Collidable c2 = null;
                if(r2 instanceof Collidable) c2 = (Collidable) r2;
                else continue;
                
                // Special dodges? 
                
                if(c1 instanceof Bullet){
                    Bullet b = (Bullet) c1;
                    if(b.isfriendly(c2.ID)) continue;
                }
                
                if(c2 instanceof Bullet){
                    Bullet b = (Bullet) c2;
                    if(b.isfriendly(c1.ID)) continue;
                }
                
                
                if(c1.collidesWith(c2)){
                    c1.onCollide();
                    c2.onCollide();
                }
            }
        }
    }
    /*
    private synchronized void updateAllObjects(){
        // Render background
        boolean debugmode_updates = global_debugmode;
        
        for (Renderable r : background_layer0.values()){
            
            if(view.inBounds(r)) r.update(view);
            else removeObject(r);
            
            if(debugmode_updates){
                System.out.println("[renderlvl 0] Updated " + r.getClass() + " with id " + r.ID);
                System.out.flush();
            }
            
        }
        
        // Render game objects that do not collide 
        for (Renderable r : game_nocollision_layer1.values()){
            if(view.inBounds(r)) r.update(view);
            else game_nocollision_layer1.remove(r.ID);
            
            if(debugmode_updates){
                System.out.println("[renderlvl 1] Updated " + r.getClass() + " with id " + r.ID);
                System.out.flush();
            }
        }
        
        // Render game objects that do collide (after the non-collides, so messages e.g. won't overlay the important collideables. 
        for (Renderable r : game_collision_layer2.values()){
            if(view.inBounds(r)) r.update(view);
            else game_collision_layer2.remove(r.ID);
            if(debugmode_updates){
                System.out.println("[renderlvl 2] Updated " + r.getClass() + " with id " + r.ID);
                System.out.flush();
            }
        }
        
        // Render game foreground
        for (Renderable r : foreground_layer3.values()){
            if(view.inBounds(r)) r.update(view);
            else foreground_layer3.remove(r.ID);
            
            if(debugmode_updates){
                System.out.println("[renderlvl 3] Updated " + r.getClass() + " with id " + r.ID);
                System.out.flush();
            }
        }
    */
        /*for(Renderable r : objects.values()){
            r.update();
            System.out.println("Updated " + r.getClass() + " with id " + r.ID);
            System.out.flush();
        }*/
        /*System.out.println("updated");
    }*/
    
    /*
        Controlled access?
    */
    
    public void start(){
        active = true;
        gameloopthread = new Thread(createGameLoop(60));
        gameloopthread.start();
    }
    public void stop(){
        active = false;
    }
    
    /*
        Called from outer sources
    */
    public void spawnCollidableCircleDummy(int xPos, int yPos){
        long nextID = getNextIdSafely();
        MovingCollidableImage ci = new MovingCollidableImage(nextID, xPos, yPos, new CircularHitBox(40), "Shuttle1.png", new Direction());
        ci.direction = new SimpleDirectionPicker(ci, 3000);
        addToRenderLevel(RenderLevel.COLLIDE, nextID, ci);
        view.getChildren().add(ci.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d);
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d, Collidable friendly){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, friendly);
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d, Collection<Collidable> friendly){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, friendly);
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnLabel(RenderLevel render_level, int xPos, int yPos, String text){
        long nextID = getNextIdSafely();
        RenderableLabel rl = new RenderableLabel(nextID, xPos, yPos, text);
        addToRenderLevel(render_level, nextID, rl);
        view.getChildren().add(rl.getView());
    }
    
    public void spawnImage(RenderLevel render_level, int xPos, int yPos, Image i){
        long nextID = getNextIdSafely();
        RenderableImage ri = new RenderableImage(nextID, xPos, yPos, i);
        addToRenderLevel(render_level, nextID, ri);
        view.getChildren().add(ri.getView());
    }
    
    public void spawnImage(RenderLevel render_level, int xPos, int yPos, String filename){
        long nextID = getNextIdSafely();
        RenderableImage ri = new RenderableImage(nextID, xPos, yPos, filename);
        addToRenderLevel(render_level, nextID, ri);
        view.getChildren().add(ri.getView());
    }
    
    public void spawnPlayer1(){
        Long PLAYER1_ID = 1L;
        Player p1 = new Player(PLAYER1_ID, 300, 300, 300, 300);
        Engine.engine.addToRenderLevel(RenderLevel.COLLIDE, PLAYER1_ID, p1);
        view.getChildren().add(p1.getView());
    }

    
    /* 
        Re-used function to add a given renderable object to a specific render-level. 
    */
    private void addToRenderLevel(RenderLevel render_level, long nextID, Renderable r) {
        switch(render_level){
            case BACKGROUND: {
                background_layer0.put(nextID, r);
                break;
            }
            case NO_COLLIDE: {
                game_nocollision_layer1.put(nextID, r);
                break;
            }
            case COLLIDE: {
                game_collision_layer2.put(nextID, r);
                break;
            }
            case FOREGROUND: {
                foreground_layer3.put(nextID, r);
                break;
            }
            default : {
                System.err.println("Render level [" + render_level + "] not recognized");
                System.err.flush();
            }
        }
    }
    
}
