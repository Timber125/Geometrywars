
import javafx.application.Application;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Server extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Launch de engine
        // Geef aan player direct de inputhandler mee
        // Zorg dat de player zich volledig zelfstandig registreert
        // Laat vervolgens de 2 spelers als clienten met de server connectie maken, elk met een eigen inputhandler
        // Laat de server 2 players spawnen met de meegegeven inputhandlers
        // Geef class Player mee als friendly met elke bullet die de spelers spawnen
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
