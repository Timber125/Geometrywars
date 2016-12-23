/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import geometrywars.Rendering.Engine;
import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.Game.IntroMenu;
import geometrywars.Rendering.ViewPane;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author timber
 */
public class Geometrywars extends Application {
    
    /* 
    9 januari analyse
    Upgrades:
    
        -> Server Client systeem
        -> Meer soorten enemies nee
        -> Meer soorten "guns" en bullets nee
        -> Allies in de shop 
        -> Highscores
      jan  -> visuele aura's rond kogels & spelers ter duidelijkheid
      jan  -> explosies bij 'hit'
        -> Meer levels en meer differentiatie
        -> Bosses
      jan  -> Database support
        
        -> Impossible difficulty : Aiming intelligence die voorspelt waar speler naartoe gaat
    
    */
    
    
    
    
    @Override
    public void start(Stage primaryStage) {
       IntroMenu menu = new IntroMenu(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
