/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.Geometrywars;
import geometrywars.Rendering.Engine;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author timber
 */
public class IntroMenu {
    
    private final String backgroundImage = "SpaceBG1.jpg";
    
    private final Stage actingStage;
    public IntroMenu(Stage primaryStage){
        this.actingStage = primaryStage;
        Pane root = new Pane();
        root.getChildren().add(createMenu());
        root.setStyle("-fx-background-color:#000000");
        URL resource = Geometrywars.class.getResource("/geometrywars/Resources/" + backgroundImage);
        String image = resource.toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " +
           "-fx-background-position: center center; " +
           "-fx-background-repeat: stretch;");

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("Geometrywars Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane createMenu(){
        Pane p = new Pane();
        p.setStyle("-fx-background-color:#000000;");
        p.setStyle("-fx-color:#000000;");
        Label title = new Label("Start the adventure...");
        title.setTextFill(Color.web("#ffff00"));
        Button singlePlayer = new Button("SinglePlayer");
        singlePlayer.setOnAction(singlePlayerPressed());
        Button multiPlayer = new Button("MultiPlayer");
        multiPlayer.setOnAction(multiPlayerPressed());
        Button highscores = new Button("Highscores");
        highscores.setOnAction(highscorePressed());
        singlePlayer.setStyle("-fx-border-color: white;");
        multiPlayer.setStyle("-fx-border-color: white;");
        highscores.setStyle("-fx-border-color: white;");
        p.setPrefSize(300, 300);
        p.getChildren().addAll(title, singlePlayer, multiPlayer, highscores);
        title.relocate(120,50);
        singlePlayer.relocate(120, 100);
        multiPlayer.relocate(120, 150);
        highscores.relocate(120, 200);
        return p;
    }

    private EventHandler<ActionEvent> singlePlayerPressed() {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                //JOptionPane.showMessageDialog(null, "SinglePlayer pressed.");
                Pane root = new Pane();
                root.getChildren().add(Engine.engine.getViewPane());
                Scene scene = new Scene(root, 800, 800);
                actingStage.setTitle("Geometrywars");
                actingStage.setScene(scene);
                actingStage.show();
                // Give engine connection to the mouse and keylisteners defined in control package
                Engine.engine.setControls(new InputHandler(scene), new MouseHandler(scene));
        
                Engine.engine.spawnPlayer1();
                //Engine.engine.spawnCollidableCircleDummy(100, 50);
                // Start the engine
                Engine.engine.start();
                // Spawn stuff (testing phase)
                //Engine.engine.spawnLabel(Engine.RenderLevel.NO_COLLIDE, 100, 100, "Testlabel");
                //Engine.engine.spawnImage(Engine.RenderLevel.NO_COLLIDE, 200, 200, "BlueRocketSmall.png");
                //Engine.engine.spawnImage(Engine.RenderLevel.BACKGROUND, 120, 120, "YellowRocket.png");
            }
            
        };
    }
    
    
    private EventHandler<ActionEvent> multiPlayerPressed() {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                JOptionPane.showMessageDialog(null, "We are currently working on multiplayer. Patch will be on 20/12.");
            }
            
        };
    }
    
    private EventHandler<ActionEvent> highscorePressed() {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                // Maak een nieuwe frame met de highscores, laat het popuppen en laat de menu in de background staan.
                // Wanneer de highscores sluiten, kun je dan nog steeds het spel starten in single of multiplayer mode.
                // Dus niet zoals singleplayerpressed, niet de stage hergebruiken! nieuwe stage maken & daarop je nieuwe scene zetten.
                JOptionPane.showMessageDialog(null, "Highscores pressed. (emiel, link dit naar je database vanuit IntroMenu:highscorePressed())");
            }
            
        };
    }
        
    
    
}
