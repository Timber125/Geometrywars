/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.GWClient;
import geometrywars.GWServer;
import geometrywars.Geometrywars;
import geometrywars.Rendering.Engine;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author timber
 */
public class MultiPlayerMenu {
    
    private final String backgroundImage = "SpaceBG1.jpg";
    private TextField serverIP = new TextField();
    private final Stage actingStage;
    private GWClient gwClient;
    public MultiPlayerMenu(Stage primaryStage){
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
        primaryStage.setTitle("Geometrywars SinglePlayer Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private Pane createMenu(){
        Pane p = new Pane();
        p.setStyle("-fx-background-color:#000000;");
        p.setStyle("-fx-color:#000000;");
        Label title = new Label("Can't handle this alone? pity.");
        title.setTextFill(Color.web("#ffff00"));
        Button easy = new Button("Easy");
        easy.setOnAction(start(0));
        Button intermediate = new Button("Intermediate");
        intermediate.setOnAction(start(1));
        Button hard = new Button("Hard");
        hard.setOnAction(start(2));
        Button cruel = new Button("Cruel");
        cruel.setOnAction(start(3));
        easy.setStyle("-fx-border-color: white;");
        intermediate.setStyle("-fx-border-color: white;");
        hard.setStyle("-fx-border-color: white;");
        cruel.setStyle("-fx-border-color: white;");
        p.setPrefSize(300, 300);
        p.getChildren().addAll(title, easy, intermediate, hard, cruel);
        title.relocate(100,50);
        easy.relocate(100, 100);
        intermediate.relocate(100, 150);
        hard.relocate(100, 200);
        cruel.relocate(100, 250);
        Label serverIPLabel = new Label("Server ip:");
        Button connectServer = new Button("Connect");
        p.getChildren().addAll(serverIP, serverIPLabel, connectServer);
        serverIPLabel.relocate(100, 300);
        serverIP.relocate(200, 300);
        connectServer.relocate(300, 300);
        connectServer.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                gwClient = new GWClient(actingStage, serverIP.getText());
            }
            
        });
        return p;
    }

    private EventHandler<ActionEvent> start(int difficulty) {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                //JOptionPane.showMessageDialog(null, "SinglePlayer pressed.");
               
                // Give engine connection to the mouse and keylisteners defined in control package
                //Engine.engine.setDifficulty(difficulty);
                //Engine.engine.spawnPlayer1();
                //Engine.engine.setP1Controls(new InputHandler(scene), new MouseHandler(scene));

                //Engine.engine.spawnCollidableCircleDummy(100, 50);
                // Start the engine
                //Engine.engine.start();
                // Spawn stuff (testing phase)
                //Engine.engine.spawnLabel(Engine.RenderLevel.NO_COLLIDE, 100, 100, "Testlabel");
                //Engine.engine.spawnImage(Engine.RenderLevel.NO_COLLIDE, 200, 200, "BlueRocketSmall.png");
                //Engine.engine.spawnImage(Engine.RenderLevel.BACKGROUND, 120, 120, "YellowRocket.png");
                GWServer s = new GWServer();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MultiPlayerMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                gwClient = s.start(actingStage);
            }
            
        };
    }
    
    
    
}
