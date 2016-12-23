/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import geometrywars.Control.InputHandler;
import geometrywars.Control.MouseHandler;
import geometrywars.Geometrywars;
import geometrywars.PropertyHandler;
import geometrywars.Rendering.Engine;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import javax.swing.JOptionPane;

/**
 *
 * @author timber
 */
public class IntroMenu {
    
    private final String backgroundImage = "SpaceBG1.jpg";
    private TextField name;
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
        name = new TextField("Nameless player");
        p.setPrefSize(300, 300);
        p.getChildren().addAll(name,title, singlePlayer, multiPlayer, highscores);
        title.relocate(120,50);
        singlePlayer.relocate(120, 100);
        multiPlayer.relocate(120, 150);
        highscores.relocate(120, 200);
        name.relocate(120, 250);
        return p;
    }

    private EventHandler<ActionEvent> singlePlayerPressed() {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                SinglePlayerMenu spm = new SinglePlayerMenu(actingStage, name.getText());
            }
            
        };
    }
    
    
    private EventHandler<ActionEvent> multiPlayerPressed() {
        return new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                //JOptionPane.showMessageDialog(null, "We are currently working on multiplayer. Patch will be on 20/12.");
                new MultiPlayerMenu(actingStage);
            
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
                Stage s = new Stage();
                Pane ap = new Pane();
                
                
                
                PropertyHandler propscores = new PropertyHandler(new File("highscores.properties"));
                PropertyHandler namescores = new PropertyHandler(new File("names.properties"));
                
                Map<String, String> scoremap = propscores.getAll();
                Map<String, String> namesmap = namescores.getAll();
                
                ArrayList<HighScoreEntry> list = new ArrayList<>();
                
                for(String st : scoremap.keySet()){
                   
                    String key = scoremap.get(st);
                    String val = namesmap.get(st);
                    list.add(new HighScoreEntry(st, val, key));
                    
                }
                Collections.sort(list);
                ListView<String> lv = new ListView();
                ArrayList<String> viewlist = new ArrayList<>();
                int counter = 0;
                for(HighScoreEntry hse : list){
                    counter ++;
                    viewlist.add(counter + " : " + hse.score + " - " + hse.name);
                }
                
                ObservableList<String> items =FXCollections.observableArrayList (viewlist);
                lv.setItems(items);
                ap.getChildren().add(lv);
                Scene sc = new Scene(ap, 300, 500);
                
                
                
                s.setScene(sc);
                s.show();
                s.toFront();
                
               
                
            }
            
        };
    }
        
    
    
}
