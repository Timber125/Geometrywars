/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game.Logics;

import geometrywars.Rendering.RenderableLabel;
import javafx.scene.control.Label;

/**
 *
 * @author timber
 */
public class GameStats {
    
    private RenderableLabel p1Points = new RenderableLabel(3L, 20, 20, "P1 Points: " + 0);
    private RenderableLabel p1Health = new RenderableLabel(4L, 170, 20, "P1 Health: " + 100 + "%");
    private RenderableLabel p1Armor = new RenderableLabel(5L, 170, 40, "P1 Armor : " + 0 + "%");
    private RenderableLabel p1Speed = new RenderableLabel(9L, 320, 20, "P1 Speed: " + 5);
    private RenderableLabel p1Gun = new RenderableLabel(6L, 470, 20, "P1 Gun: " + "space laser");
    private RenderableLabel p1GunDmg = new RenderableLabel(7L, 470, 40, "P1 Gun: " + "10 dmg / bullet");
    private RenderableLabel p1GunSpeed = new RenderableLabel(8L, 470, 60, "P1 Gun: " + "2.5 bullets / second");
    public GameStats(){
        
    }
    
    public RenderableLabel getP1Points(){return p1Points;}
    public RenderableLabel getP1Health(){return p1Health;}
    public RenderableLabel getP1Armor(){return p1Armor;}
    public RenderableLabel getP1Gun(){return p1Gun;}
    public RenderableLabel getP1GunDmg(){return p1GunDmg;}
    public RenderableLabel getP1GunSpeed(){return p1GunSpeed;}
    public RenderableLabel getP1Speed(){return p1Speed;}
    
    public RenderableLabel updateP1Points(int points){
        p1Points.getView().setText("P1 Points: " + points);
        return p1Points;
    }
    public RenderableLabel updateP1Health(int healthPercentage){
        p1Health.getView().setText("P1 Health: " + healthPercentage + "%");
        return p1Health;
    }
    public RenderableLabel updateP1Armor(int armorPercentage){
        p1Armor.getView().setText("P1 Armor :" + armorPercentage + "%");
        return p1Armor;
    }
    public RenderableLabel updateP1Gun(String gun){
        p1Gun.getView().setText("P1 Gun: " + gun);
        return p1Gun;
    }
    public RenderableLabel updateP1GunDmg(int dmg){
        p1GunDmg.getView().setText("P1 Gun: " + dmg + " dmg / bullet");
        return p1GunDmg;
    }
    public RenderableLabel updateP1GunSpeed(double gunspeed){
        p1GunSpeed.getView().setText("P1 Gun: " + gunspeed + " bullets / second");
        return p1GunSpeed;
    }
    public RenderableLabel updateSpeed(double speed){
        p1Speed.getView().setText("P1 Speed: " + speed);
        return p1Speed;
    }
}
