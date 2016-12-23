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
    
    private RenderableLabel P2Points = new RenderableLabel(13L, 20, 20, "P2 Points: " + 0);
    private RenderableLabel P2Health = new RenderableLabel(14L, 170, 20, "P2 Health: " + 100 + "%");
    private RenderableLabel P2Armor = new RenderableLabel(15L, 170, 40, "P2 Armor : " + 0 + "%");
    private RenderableLabel P2Speed = new RenderableLabel(19L, 320, 20, "P2 Speed: " + 5);
    private RenderableLabel P2Gun = new RenderableLabel(16L, 470, 20, "P2 Gun: " + "space laser");
    private RenderableLabel P2GunDmg = new RenderableLabel(17L, 470, 40, "P2 Gun: " + "10 dmg / bullet");
    private RenderableLabel P2GunSpeed = new RenderableLabel(18L, 470, 60, "P2 Gun: " + "2.5 bullets / second");
    public GameStats(){
        
    }
    
    public RenderableLabel getP1Points(){return p1Points;}
    public RenderableLabel getP1Health(){return p1Health;}
    public RenderableLabel getP1Armor(){return p1Armor;}
    public RenderableLabel getP1Gun(){return p1Gun;}
    public RenderableLabel getP1GunDmg(){return p1GunDmg;}
    public RenderableLabel getP1GunSpeed(){return p1GunSpeed;}
    public RenderableLabel getP1Speed(){return p1Speed;}
    
    public RenderableLabel getP2Points(){return P2Points;}
    public RenderableLabel getP2Health(){return P2Health;}
    public RenderableLabel getP2Armor(){return P2Armor;}
    public RenderableLabel getP2Gun(){return P2Gun;}
    public RenderableLabel getP2GunDmg(){return P2GunDmg;}
    public RenderableLabel getP2GunSpeed(){return P2GunSpeed;}
    public RenderableLabel getP2Speed(){return P2Speed;}
    
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
    public RenderableLabel updateP1Speed(double speed){
        p1Speed.getView().setText("P1 Speed: " + speed);
        return p1Speed;
    }
    
     public RenderableLabel updateP2Points(int points){
        P2Points.getView().setText("P2 Points: " + points);
        return P2Points;
    }
    public RenderableLabel updateP2Health(int healthPercentage){
        P2Health.getView().setText("P2 Health: " + healthPercentage + "%");
        return P2Health;
    }
    public RenderableLabel updateP2Armor(int armorPercentage){
        P2Armor.getView().setText("P2 Armor :" + armorPercentage + "%");
        return P2Armor;
    }
    public RenderableLabel updateP2Gun(String gun){
        P2Gun.getView().setText("P2 Gun: " + gun);
        return P2Gun;
    }
    public RenderableLabel updateP2GunDmg(int dmg){
        P2GunDmg.getView().setText("P2 Gun: " + dmg + " dmg / bullet");
        return P2GunDmg;
    }
    public RenderableLabel updateP2GunSpeed(double gunspeed){
        P2GunSpeed.getView().setText("P2 Gun: " + gunspeed + " bullets / second");
        return P2GunSpeed;
    }
    public RenderableLabel updateP2Speed(double speed){
        P2Speed.getView().setText("P2 Speed: " + speed);
        return P2Speed;
    }
}
