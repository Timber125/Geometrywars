/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package geometrywars.Game.Logics;

import geometrywars.Rendering.Engine;
import geometrywars.Rendering.Engine.RenderLevel;
import geometrywars.Rendering.RenderObject;
import geometrywars.Rendering.Renderable;
import geometrywars.Rendering.ViewPane;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author admin
 */
public class HealthBar extends Renderable{
    
    protected int maxHP;
    protected int currHP;
    protected int width = 40;
    protected int height = 5;
    protected AnchorPane view;
    protected AnchorPane green;
    public HealthBar(Long ID, int maxHP){
        super(ID);
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.view = (forge());
        registerMe();
    }
    public HealthBar(int maxHP){
        super(0L);
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.view = (forge());
    }
    public void refresh(){
        reforge();
    }
    public void setHP(int newHP){
        this.currHP = newHP;
        if(this.currHP > this.maxHP) this.maxHP = this.currHP;
        refresh();
    }
    public void takeDamage(int dmg){
        this.currHP -= dmg;
        refresh();
    }
    public int getHP(){
        return currHP;
    }
    public Double getHPasPercentage(){
        if(maxHP == 0) return 100.0;
        return ((100.00 * currHP) / maxHP) / 100.0;
    }
    private void reforge(){
        
            double greenWidth = (width * (getHPasPercentage()));
            green.setPrefWidth(greenWidth);
            green.relocate(0, 0);
            green.toFront();
    }
    private AnchorPane forge(){
        AnchorPane total = new AnchorPane();
        green = new AnchorPane();
        total.setPrefWidth(width);
        total.setPrefHeight(height);
        total.setStyle("-fx-background-color:RED;");
        green.setStyle("-fx-background-color:GREEN;");
        double greenWidth = (width * (getHPasPercentage()));
        
        green.setPrefWidth(greenWidth);
        green.setPrefHeight(height);
   
        total.getChildren().addAll(green);
        green.relocate(0, 0);
        return total;
    }

 
    @Override
    public Pane getView() {
        return this.view;
    }

    @Override
    public void update(ViewPane p) {
        p.getChildren().remove(this.getView());
        this.getView().relocate(this.getX(), this.getY());
        p.getChildren().add(this.getView());
    }

    private void registerMe() {
        Engine.engine.registerToNoCollide(ID, this);
    }
    
   
   
 
}
