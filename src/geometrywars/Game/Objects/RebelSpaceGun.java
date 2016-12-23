/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Game.Objects;

/**
 *
 * @author admin
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import geometrywars.Game.Logics.Direction;
import geometrywars.Rendering.Collidable;
import java.util.Collection;

/**
 *
 * @author timber
 */
public class RebelSpaceGun extends Gun{
    public RebelSpaceGun(){
        this.att_speed = 3.0;
        this.dmg = 10;
        this.name = "Rebel Space Gun";
    }
     @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, long ownerID){
        return new RebelSpaceGunBullet(ID, xPos, yPos, d, this.dmg, ownerID);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Collection<Class> friendly, long ownerID){
        return new RebelSpaceGunBullet(ID, xPos, yPos, friendly, d, this.dmg, ownerID);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Class friendly, long ownerID){
        return new RebelSpaceGunBullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Collection<Collidable> friendly, Direction d, long ownerID){
        return new RebelSpaceGunBullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Collidable friendly, Direction d, long ownerID){
        return new RebelSpaceGunBullet(ID, xPos, yPos, d, friendly, this.dmg, ownerID);
    }
    
     public class RebelSpaceGunBullet extends Bullet{
        
        private static final String filename = "Bullet3.png";
        
        public RebelSpaceGunBullet(long ID, int xPos, int yPos, Direction d, int dmg, long ownerID) {
            super(ID, xPos, yPos, d, dmg, filename, ownerID);
        }
        public RebelSpaceGunBullet(long ID, int xPos, int yPos, Direction d, Class friendly, int dmg, long ownerID) {
            super(ID, xPos, yPos, d, friendly, dmg, filename, ownerID);
        }
        public RebelSpaceGunBullet(long ID, int xPos, int yPos, Direction d, Collection<Collidable> friendly, int dmg, long ownerID) {
            super(ID, xPos, yPos, d, friendly, dmg, filename, ownerID);
        }
        public RebelSpaceGunBullet(long ID, int xPos, int yPos, Direction d, Collidable friendly, int dmg, long ownerID) {
            super(ID, xPos, yPos, d, friendly, dmg, filename, ownerID);
        }
        public RebelSpaceGunBullet(long ID, int xPos, int yPos, Collection<Class> friendly, Direction d, int dmg, long ownerID) {
            super(ID, xPos, yPos, friendly, d, dmg, filename, ownerID);
        }
        
    }
}
