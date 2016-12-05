/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars;

import geometrywars.Rendering.Engine;
import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
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
    
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        root.getChildren().add(Engine.engine.getViewPane());
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Geometrywars");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Give engine connection to the mouse and keylisteners defined in control package
        Engine.engine.setControls(new InputHandler(scene), new MouseHandler(scene));
        
        Engine.engine.spawnPlayer1();
        Engine.engine.spawnCollidableCircleDummy(100, 50);
        // Start the engine
        Engine.engine.start();
        // Spawn stuff (testing phase)
        //Engine.engine.spawnLabel(Engine.RenderLevel.NO_COLLIDE, 100, 100, "Testlabel");
        //Engine.engine.spawnImage(Engine.RenderLevel.NO_COLLIDE, 200, 200, "BlueRocketSmall.png");
        //Engine.engine.spawnImage(Engine.RenderLevel.BACKGROUND, 120, 120, "YellowRocket.png");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
