/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author camilla
 */
public class Map extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Ympyräsimulaattori :D");
        Parent root = FXMLLoader.load(getClass().getResource("MapGUI.fxml"));
        root.setStyle("-fx-background-image: url('" + getClass().getResource("Suomen-kartta.jpg").toExternalForm() + "')");
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
