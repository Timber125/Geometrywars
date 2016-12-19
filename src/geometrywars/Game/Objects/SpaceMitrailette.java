/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Objects;

import geometrywars.Game.Logics.Direction;
import geometrywars.Rendering.Collidable;
import java.util.Collection;

/**
 *
 * @author timber
 */
public class SpaceMitrailette extends Gun{
    public SpaceMitrailette(){
        this.att_speed = 4.0;
        this.dmg = 5;
        this.name = "Space Mitrailette";
    }
     @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d){
        return new SpaceMitrailette.SpaceMitrailetteBullet(ID, xPos, yPos, d, this.dmg);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Collection<Class> friendly){
        return new SpaceMitrailette.SpaceMitrailetteBullet(ID, xPos, yPos, friendly, d, this.dmg);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Direction d, Class friendly){
        return new SpaceMitrailette.SpaceMitrailetteBullet(ID, xPos, yPos, d, friendly, this.dmg);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Collection<Collidable> friendly, Direction d){
        return new SpaceMitrailette.SpaceMitrailetteBullet(ID, xPos, yPos, d, friendly, this.dmg);
    }
    @Override
    public Bullet createBullet(long ID, int xPos, int yPos, Collidable friendly, Direction d){
        return new SpaceMitrailette.SpaceMitrailetteBullet(ID, xPos, yPos, d, friendly, this.dmg);
    }
    
     public class SpaceMitrailetteBullet extends Bullet{
        
        private static final String filename = "Bullet3.png";
        
        public SpaceMitrailetteBullet(long ID, int xPos, int yPos, Direction d, int dmg) {
            super(ID, xPos, yPos, d, dmg, filename);
        }
        public SpaceMitrailetteBullet(long ID, int xPos, int yPos, Direction d, Class friendly, int dmg) {
            super(ID, xPos, yPos, d, friendly, dmg, filename);
        }
        public SpaceMitrailetteBullet(long ID, int xPos, int yPos, Direction d, Collection<Collidable> friendly, int dmg) {
            super(ID, xPos, yPos, d, friendly, dmg, filename);
        }
        public SpaceMitrailetteBullet(long ID, int xPos, int yPos, Direction d, Collidable friendly, int dmg) {
            super(ID, xPos, yPos, d, friendly, dmg, filename);
        }
        public SpaceMitrailetteBullet(long ID, int xPos, int yPos, Collection<Class> friendly, Direction d, int dmg) {
            super(ID, xPos, yPos, friendly, d, dmg, filename);
        }
        
    }
}
