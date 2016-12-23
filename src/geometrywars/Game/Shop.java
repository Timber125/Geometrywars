/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Game.Objects.Gun;
import geometrywars.Game.Objects.Player;
import geometrywars.Game.Objects.SpaceCannon;
import geometrywars.Game.Objects.SpaceLaserGun;
import geometrywars.Game.Objects.SpaceMitrailette;
import geometrywars.Rendering.Engine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author timber
 */
public class Shop {
    private static final int costSpaceLaser = 1000;
    private static final int costArmor = 100;
    private static final int costSpaceCannon = 3500;
    private static final int costSpaceMitrailette = 1500;
    private static final int costAlly = 1000;
    
    public static Shop shop = new Shop();
    private Stage shopStage;
    private Scene shopScene;
    
    private Button buyArmor = new Button("Buy 10 armor");
    private Label armorEnable = new Label("10 armor");
    private Label armorDisable = new Label("Currently on Full armor");
    private Label armorPoverty = new Label("Not enough points");
    private Label armorCost = new Label("[ " + costArmor + " ]");
    
    private Button buySpaceCannon = new Button("Buy spaceCannon");
    private Label spaceCannonEnable = new Label("SpaceCannon 2.5 attack speed, 100 dmg");
    private Label spaceCannonDisable = new Label("Currently equipped");
    private Label spaceCannonPoverty = new Label("Not enough points");
    private Label spaceCannonCost = new Label("[ " + costSpaceCannon + " ]");
    
    private Button buySpaceLaser = new Button("Buy spaceLaser");
    private Label spaceLaserEnable = new Label("SpaceLaser 0.4 attack speed, 40 dmg");
    private Label spaceLaserDisable = new Label("Currently equipped");
    private Label spaceLaserPoverty = new Label("Not enough points");
    private Label spaceLaserCost = new Label("[ " + costSpaceLaser + " ]");
    
    private Button buySpaceMitrailette = new Button("Buy spaceMitrailette");
    private Label spaceMitrailetteEnable = new Label("SpaceMitrailette 4.0 attack speed, 5 dmg");
    private Label spaceMitrailetteDisable = new Label("Currently equipped");
    private Label spaceMitrailettePoverty = new Label("Not enough points");
    private Label spaceMitrailetteCost = new Label("[ " + costSpaceMitrailette + " ]");
    
    private Button buyAlly = new Button("Buy an allied spaceship");
    private Label allyEnable = new Label("ally with spacemitrailette");
    private Label allyDisable = new Label("You can't spawn an ally right now");
    private Label allyPoverty = new Label("Not enough points");
    private Label allyCost = new Label("[ " + costAlly + " ]");
    
    
    
    public Shop(){
        shopStage = new Stage();
        Pane root = new Pane();
        root.setPrefSize(480,300);
        shopScene = new Scene(root, 480, 300);
        
        shopStage.setTitle("Geometrywars Shop");
        shopStage.setScene(shopScene);
        
        root.getChildren().addAll(buyArmor, armorEnable, armorDisable,
                armorPoverty, armorCost, buySpaceCannon, 
                spaceCannonEnable, spaceCannonDisable, 
                spaceCannonCost, spaceCannonPoverty, 
                buySpaceLaser, spaceLaserEnable, 
                spaceLaserDisable, spaceLaserCost, 
                spaceLaserPoverty, buySpaceMitrailette, 
                spaceMitrailetteEnable, spaceMitrailetteDisable, 
                spaceMitrailetteCost, spaceMitrailettePoverty,
                buyAlly, allyEnable, allyDisable, allyPoverty, allyCost);
        
        buyArmor.relocate(280,30);
        buySpaceCannon.relocate(280, 60);
        buySpaceLaser.relocate(280, 90);
        buySpaceMitrailette.relocate(280, 120);
        buyAlly.relocate(280, 150);
        
        // Mag altijd blijven staan.
        armorCost.relocate(420, 30);
        spaceCannonCost.relocate(420, 60);
        spaceLaserCost.relocate(420, 90);
        spaceMitrailetteCost.relocate(420, 120);
        allyCost.relocate(420, 150);
        
        armorEnable.relocate(20, 30);
        armorDisable.relocate(20, 30);
        armorPoverty.relocate(20, 30);
        
        spaceCannonEnable.relocate(20, 60);
        spaceCannonDisable.relocate(20, 60);
        spaceCannonPoverty.relocate(20, 60);
        
        spaceLaserEnable.relocate(20, 90);
        spaceLaserDisable.relocate(20, 90);
        spaceLaserPoverty.relocate(20, 90);
        
        spaceMitrailetteEnable.relocate(20, 120);
        spaceMitrailetteDisable.relocate(20, 120);
        spaceMitrailettePoverty.relocate(20, 120);
        
        allyEnable.relocate(20, 150);
        allyDisable.relocate(20, 150);
        allyPoverty.relocate(20, 150);
        
        shopStage.hide();        
        
        buyArmor.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Player p = getPlayer();
                p.removePoints(costArmor);
                p.setArmor(p.getArmor() + 10);
                refreshShop();
            }
            
        });
        
        buySpaceCannon.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Player p = getPlayer();
                p.removePoints(costSpaceCannon);
                p.setGun(new SpaceCannon());
                refreshShop();
            }
            
        });
        
        buySpaceLaser.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Player p = getPlayer();
                p.removePoints(costSpaceLaser);
                p.setGun(new SpaceLaserGun());
                refreshShop();
            }
            
        });
        
        buySpaceMitrailette.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Player p = getPlayer();
                p.removePoints(costSpaceMitrailette);
                p.setGun(new SpaceMitrailette());
                refreshShop();
            }
            
        });
        
        buyAlly.setOnAction(new EventHandler<ActionEvent>(){
            
            @Override
            public void handle(ActionEvent event) {
                Player p = getPlayer();
                p.removePoints(costAlly);
                Engine.engine.spawnAlly();
                refreshShop();
            }
        });
    }
    
  
    private void makeAllInvisible(){
        armorEnable.setVisible(false);
        armorDisable.setVisible(false);
        armorPoverty.setVisible(false);
        
        spaceCannonEnable.setVisible(false);
        spaceCannonDisable.setVisible(false);
        spaceCannonPoverty.setVisible(false);
        
        spaceLaserEnable.setVisible(false);
        spaceLaserDisable.setVisible(false);
        spaceLaserPoverty.setVisible(false);
        
        spaceMitrailetteEnable.setVisible(false);
        spaceMitrailetteDisable.setVisible(false);
        spaceMitrailettePoverty.setVisible(false);
        
        allyEnable.setVisible(false);
        allyDisable.setVisible(false);
        allyPoverty.setVisible(false);
        
        buyArmor.setDisable(true);
        buySpaceCannon.setDisable(true);
        buySpaceLaser.setDisable(true);
        buySpaceMitrailette.setDisable(true);
        buyAlly.setDisable(true);
    }
    private void refreshShop(){
        makeAllInvisible();
        
        Player p = getPlayer();
        int armor = p.getArmor();
        if(hasFullArmor(armor)){
            buyArmor.setDisable(true);
            armorDisable.setVisible(true);
        }else if(p.getPoints() < costArmor){
            buyArmor.setDisable(true);
            armorPoverty.setVisible(true);
        }else{
            armorEnable.setVisible(true);
            buyArmor.setDisable(false);
        }
        
        if(hasSpaceCannon(p.getGun())){
            buySpaceCannon.setDisable(true);
            spaceCannonDisable.setVisible(true);
        }else if(p.getPoints() < costSpaceCannon){
            buySpaceCannon.setDisable(true);
            spaceCannonPoverty.setVisible(true);
        }else{
            buySpaceCannon.setDisable(false);
            spaceCannonEnable.setVisible(true);
        }
        
        if(hasSpaceLaser(p.getGun())){
            buySpaceLaser.setDisable(true);
            spaceLaserDisable.setVisible(true);
        }else if(p.getPoints() < costSpaceLaser){
            buySpaceLaser.setDisable(true);
            spaceLaserPoverty.setVisible(true);
        }else{
            buySpaceLaser.setDisable(false);
            spaceLaserEnable.setVisible(true);
        }
        
        if(hasSpaceMitraillette(p.getGun())){
            buySpaceMitrailette.setDisable(true);
            spaceMitrailetteDisable.setVisible(true);
        }else if(p.getPoints() < costSpaceMitrailette){
            buySpaceMitrailette.setDisable(true);
            spaceMitrailettePoverty.setVisible(true);
        }else{
            buySpaceMitrailette.setDisable(false);
            spaceMitrailetteEnable.setVisible(true);
        }
        
        if(p.getPoints() < costAlly){
            buyAlly.setDisable(true);
            allyPoverty.setVisible(true);
        }else{
            buyAlly.setDisable(false);
            allyEnable.setVisible(true);
        }
    }
    private Player getPlayer(){
        return (((Player)Engine.engine.find(1L, 2))) ;
    }
    private boolean hasFullArmor(int armor){
        if(armor == 100) return true;
        return false;
    }
    private boolean hasSpaceCannon(Gun gun){
        return (gun instanceof SpaceCannon);
    }
    private boolean hasSpaceLaser(Gun gun){
        return (gun instanceof SpaceLaserGun);
    }
    private boolean hasSpaceMitraillette(Gun gun){
        return (gun instanceof SpaceMitrailette);
    }
    
    public void openShop(){
        refreshShop();
        shopStage.show();
    }
    public void closeShop(){
        shopStage.hide();
    }
}
