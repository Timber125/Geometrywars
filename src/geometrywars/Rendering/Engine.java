/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Rendering;

import geometrywars.Control.InputBuffer;
import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.Game.LevelManager;
import geometrywars.Game.Logics.Direction;
import geometrywars.Game.Logics.GameStats;
import geometrywars.Game.Logics.MovingCollidableImage;
import geometrywars.Game.Logics.SimpleDirectionPicker;
import geometrywars.Game.Objects.Bullet;
import geometrywars.Game.Objects.Enemy;
import geometrywars.Game.Objects.Gun;
import geometrywars.Game.Objects.Player;
import geometrywars.Game.Objects.ShuttleThree;
import geometrywars.Game.Objects.ShuttleTwo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
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
    
    private LevelManager levelManager;
    private Long timeOfLevelStart;
    private GameStats gamestats;
    public Engine (){
        levelManager = new LevelManager();
        gamestats = new GameStats();
        initGameStats();
    }
    
    
    
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

    public void gameOver(boolean won) {
        String text = (won ? ("Victory!! ") : ("Defeated! "));
        spawnLabel(RenderLevel.BACKGROUND, 300, 300, text + "You scored " + findPlayer().getPoints() + " points.");
        try {
            // Leave 50 ms to let the engine have one more loop, so health can be updated to zero. 
            // This would otherwise leave some confusion to the player, as he would never be sure he actually hit 0 hitpoins.
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        active = false;
    }

    private int difficulty = 0;
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public int getDifficulty(){return difficulty;}

   
    
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
        ID 3L = Label for player1 points.
        ID 4L = Label for player1 health.
        ID 5L = Label for player1 armor.
        ID 6L = Label for player1 gun (& dmg / bullet) 
        ID 7L = Label for alltime highscore (wait for database to implement)
        
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
         long treshold = Math.round(1000 / pl.getGun().getSpeed());
         long delta = now - pl.last_shot; // > 0
         
         if(treshold < delta){
             // SHOOT
             pl.last_shot = now;
             Point player_center = pl.getCenter();
             if(global_debugmode) System.out.println("PLAYER X Y [" + pl.xPos + "] [" + pl.yPos + "]");
             if(global_debugmode) System.out.println("CENTER X Y [" + player_center.X + "] [" + player_center.Y + "]");
             spawnBullet(player_center.X, player_center.Y, new Direction(player_center.X, player_center.Y, mousehandler.getLocation().sceneX, mousehandler.getLocation().sceneY), Player.class, pl.getGun());
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
        Get a point on the screen where it is safe to spawn an enemy, 
        Fair to say noone would like an enemy to spawn on top of the player, killing him/her instantly based on randomness of spawnpoints. 
        
    */
    
    public Point getSafeSpawnCoord(double mindistance){
        Point p = generateRandomCoord();
        while(!isSafeSpawnCoord(p, mindistance)){
            p = generateRandomCoord();
        }
        return p;
    }
    private Point generateRandomCoord(){
        Random r = new Random();
        return new Point(r.nextInt(view.getObjectBounds().X), r.nextInt(view.getObjectBounds().Y));
    }
    private boolean isSafeSpawnCoord(Point p, double minDistance){
        Player player = findPlayer();
        int xp = player.xPos;
        int yp = player.yPos;
        
        double distance = Math.sqrt(Math.pow(Math.abs(p.X - xp), 2) + Math.pow(Math.abs(p.Y - yp), 2));
        if(distance > minDistance) return true;
        else return false;
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
                            updateGameStats(p);
                            updateLevelIfNeeded();
                            if(!paused){
                                spawnEnemy(levelManager.spawn());
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
    
    // in the case of a bullet hitting 2 enemies at the exact moment, it will try to remove itself twice. 
    // The "wanted" behaviour is that it kills both enemies before killing the bullet.
    // We have this behaviour, but the problem is that the engine will try to remove the bullet twice. 
    // This will actually not cause any problems! So we easily solve this by throwing nullpointerexceptions
    // This improves the computational time for removeObject, 
    // as it does not need to check whether the object is actually in the list..
    // Compare this to a list.remove -> we don't need to check if the function returned true, 
    // If it returns false the object will not be in the list anyway, which is the wanted result. 
    // Here, we do exactly the same. Except, it will not return false, but will throw a nullpointer. 
    // This explains the throwing of nullpointerexceptions.
    public void removeObject(Renderable r) throws NullPointerException{
        Long id = r.ID;
        view.getChildren().remove(r.getView());
        locate(id).remove(id);        
    }
    public void removeObject(long ID) throws NullPointerException{
        Renderable r = find(ID);
        view.getChildren().remove(r.getView());
        locate(ID).remove(ID);   
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
                    if(b.isfriendly(c2)) continue;
                }
                
                if(c2 instanceof Bullet){
                    Bullet b = (Bullet) c2;
                    if(b.isfriendly(c1)) continue;
                }
                
                
                if(c1.collidesWith(c2)){
                    c1.onCollide(c2);
                    c2.onCollide(c1);
                }
            }
        }
    }
    
    /*
        Controlled access
    */
    
    public void start(){
        active = true;
        gameloopthread = new Thread(createGameLoop(60));
        gameloopthread.start();
        timeOfLevelStart = System.currentTimeMillis();
        spawnTimedLabel(RenderLevel.BACKGROUND, 320, 100, "Level 1, Good luck!", 5000L);
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
    public void spawnBullet(int xPos, int yPos, Direction d, Gun spawner){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, spawner.getDmg());
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d, Collidable friendly, Gun spawner){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, friendly, spawner.getDmg());
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d, Collection<Collidable> friendly, Gun spawner){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, friendly, spawner.getDmg());
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnBullet(int xPos, int yPos, Direction d, Class friendly, Gun spawner){
        long nextID = getNextIdSafely();
        Bullet b = new Bullet(nextID, xPos, yPos, d, friendly, spawner.getDmg());
        addToRenderLevel(RenderLevel.COLLIDE, nextID, b);
        view.getChildren().add(b.getView());
    }
    public void spawnLabel(RenderLevel render_level, int xPos, int yPos, String text){
        long nextID = getNextIdSafely();
        RenderableLabel rl = new RenderableLabel(nextID, xPos, yPos, text);
        addToRenderLevel(render_level, nextID, rl);
        view.getChildren().add(rl.getView());
    }
    public void spawnTimedLabel(RenderLevel render_level, int xPos, int yPos, String text, Long millis){
        long nextID = getNextIdSafely();
        TimedRenderableLabel rl = new TimedRenderableLabel(nextID, xPos, yPos, text, millis);
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
    
    public void spawnEnemy(String enemy){
        if(enemy.equals("none")) return;
        else switch (enemy){
            case("ShuttleTwo"):{
                long nextID = getNextIdSafely();
                Point p = getSafeSpawnCoord(150);
                ShuttleTwo en = new ShuttleTwo(nextID);
                addToRenderLevel(RenderLevel.COLLIDE, nextID, en);
                view.getChildren().add(en.getView());
                return;
            }
            case("ShuttleThree"):{
                long nextID = getNextIdSafely();
                Point p = getSafeSpawnCoord(150);
                ShuttleThree en = new ShuttleThree(nextID);
                addToRenderLevel(RenderLevel.COLLIDE, nextID, en);
                view.getChildren().add(en.getView());
                return;
            }
            default:{
                System.out.println("[SEVERE] Engine.spawnEnemy(String enemy) -> enemy not recognized: [" + enemy + "]");
                return;
            }
        }
    }

    public void grantPoints(int points_on_kill) {
        Player p = findPlayer();
        p.addPoints(points_on_kill);
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
    
    private void initGameStats(){
        long IDPoints = 3L;
        long IDHealth = 4L;
        long IDArmor = 5L;
        long IDGun = 6L;
        long IDGunDmg = 7L;
        long IDGunSpeed = 8L;
        long IDSpeed = 9L;
        addToRenderLevel(RenderLevel.BACKGROUND, IDPoints, gamestats.getP1Points());
        addToRenderLevel(RenderLevel.BACKGROUND, IDHealth, gamestats.getP1Health());
        addToRenderLevel(RenderLevel.BACKGROUND, IDArmor, gamestats.getP1Armor());
        addToRenderLevel(RenderLevel.BACKGROUND, IDGun, gamestats.getP1Gun());
        addToRenderLevel(RenderLevel.BACKGROUND, IDGunDmg, gamestats.getP1GunDmg());
        addToRenderLevel(RenderLevel.BACKGROUND, IDGunSpeed, gamestats.getP1GunSpeed());
        addToRenderLevel(RenderLevel.BACKGROUND, IDSpeed, gamestats.getP1Speed());
        view.getChildren().add(gamestats.getP1Points().getView());
        view.getChildren().add(gamestats.getP1Health().getView());
        view.getChildren().add(gamestats.getP1Armor().getView());
        view.getChildren().add(gamestats.getP1Gun().getView());
        view.getChildren().add(gamestats.getP1GunDmg().getView());
        view.getChildren().add(gamestats.getP1GunSpeed().getView());
        view.getChildren().add(gamestats.getP1Speed().getView());
    }
    private void updateGameStats(Player p) {
        gamestats.updateP1Points(p.getPoints());
        gamestats.updateP1Health(p.getHealth());
        gamestats.updateP1Armor(p.getArmor());
        gamestats.updateP1Gun(p.getGun().getName());
        gamestats.updateP1GunDmg(p.getGun().getDmg());
    }
    
    private void updateLevelIfNeeded() {
        long level_busy = System.currentTimeMillis() - timeOfLevelStart;
        if(level_busy > levelManager.getLevelTime()){
            timeOfLevelStart = System.currentTimeMillis();
            levelManager.levelUp();
            spawnTimedLabel(RenderLevel.BACKGROUND, 320, 80, "LEVEL " + levelManager.getLevel(), 5000L);
        }
        
    }
}
