/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author timber
 */
public class MenuTester extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
         IntroMenu menu = new IntroMenu(primaryStage);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}
