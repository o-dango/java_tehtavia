/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author camilla
 */
public class MapGUIController implements Initializable {

    @FXML
    private AnchorPane picLabel;

    ImageView iv;
    Circles point;
    Lines lines;
    double x_set = 0;
    double y_set = 0;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        point = Circles.getInstance();
        lines = Lines.getInstance();
    }    

    @FXML
    private void setCircleAction(MouseEvent event) {
        
        int i = 0;
        int help = 0;
        boolean flag = true;
        double x_help;
        double y_help;
        
        System.out.println("Click the circles");
        System.out.println(event.getX() + " " + event.getY());
        
        Circle circle = new Circle(event.getX(), event.getY(), 5);
        
        while(flag == true) {
            x_help = Math.abs((event.getX()) - point.getPositionX(i));
            y_help = Math.abs((event.getY()) - point.getPositionY(i));
            
            if(point.getName(i).equals("null") && i != 0) {
                flag = false;
            }
            else if(Math.sqrt(Math.pow(x_help, 2) + Math.pow(y_help, 2)) <= 5){
                help = help + 1;
            }
            else {
                
            }
    
            i++;
            
        }
        
        if(help == 0) {
            picLabel.getChildren().add(circle);
            point.saveInfo("Circle:D", event.getX(), event.getY());
        }
        else {
            System.out.println("Hei, olen piste :D");
            System.out.println(point.getName(0));
            y_set = event.getY();
            x_set = event.getX();

            Line line = new Line();
            picLabel.getChildren().add(line);

            if(lines.size() == 0) {
                lines.setLine(x_set, y_set, event.getX(), event.getY());
            }
            else {
                lines.setLine(lines.getEndX(), lines.getEndY(), x_set, y_set);
            }

            line.setStartX(lines.getStartX());
            line.setStartY(lines.getStartY());
            line.setEndX(lines.getEndX());
            line.setEndY(lines.getEndY());
            
        }

    }

    @FXML
    private void setLineAction(MouseDragEvent event) {
        System.out.println("höhö");
    }
    
}
