/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometrywars.Game;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author timber
 */
public class Shop {
    
    public static Shop shop = new Shop();
    public Shop(){
        Stage shopStage = new Stage();
        Pane root = new Pane();
        Scene schopScene = new Scene(root, 300, 300);
    }
}
