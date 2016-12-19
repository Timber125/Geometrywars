/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Game.Logics;

/**
 *
 * @author admin
 */
public class ArmorBar extends HealthBar{

    private int xDeviation = 0;
    private int yDeviation = -10;
   
    public ArmorBar(Long ID, int currentHP, int maxHP){
        super(ID, maxHP);
        this.view.setStyle("-fx-background-color:TRANSPARENT;");
        this.green.setStyle("-fx-background-color:GRAY;");
        this.setHP(currentHP);
    }
    
    @Override
    public int setX(int x){
        xPos.setValue(x + xDeviation);
        return xPos.getValue();
    }
    
    @Override
    public int setY(int y){
        yPos.setValue(y + yDeviation);
        return yPos.getValue();
    }
    
}
