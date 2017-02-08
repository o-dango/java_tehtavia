/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author camilla
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea label;
    @FXML
    private TextField inputField;
    @FXML
    private ColorPicker colorPick;
    
    ReadAndWrite file;
    Text t = new Text("");
    public String name;
    private String path;
    private String color = "black";
    @FXML
    private Button openButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button helloButton;
    
    
//    @FXML
//    private void addTextAction(ActionEvent event) {
//        
//        if((inputField.getText()).equals("") != true) {
//            label.setText(label.getText() + inputField.getText() + '\n');
//            inputField.clear();
//            System.out.println("Text added");
//        }
//        else {
//            label.setText(label.getText());
//            inputField.clear();
//        }
//    }
    
    private void addInputAction(ActionEvent event) {
//        if((inputField.getText()).equals("") != true) {
//            label.setText(label.getText() + inputField.getText() + '\n');
//            inputField.clear();
//            System.out.println("Text added");
//        }
//        else {
//            label.setText(label.getText());
//            inputField.clear();
//        }
        
        name = inputField.getText();
            
    }
    
    @FXML
    private void openFileAction(ActionEvent event) throws IOException {
       t = new Text();
       label.setStyle("-fx-text-fill: " + color);
        if(inputField.getText().equals("")) {
            inputField.setPromptText("Anna tiedoston nimi!");
        }
        else {
            path = System.getProperty("user.dir") + "/" + inputField.getText();
            file = new ReadAndWrite(path);
            System.out.println(path);
            t.setText(file.readFile(path));
            label.setText(t.getText());
        }
        
    }
    
    @FXML
    private void saveFileAction(ActionEvent event) throws IOException {
        path = System.getProperty("user.dir") + "/" + inputField.getText();
        System.out.println(path);
        if(inputField.getText().equals("")) {
            inputField.setPromptText("Anna tiedoston nimi!");
        }
        else {
            label.setStyle("-fx-text-fill: " + color);
            t.setText(file.Write(path, label.getText()));
        }
        
    }
    
    @FXML
    private void clearTextAction(ActionEvent event) {
        label.setText("");
    }
    
    @FXML
    private void setTextColor(ActionEvent event) {
        //t = new Text(label.getText());
        t.setText(label.getText());
        System.out.println("Tekstin väri: " + colorPick.getValue());
        color = (Color.web((colorPick.getValue()).toString())).toString();
        color = color.substring(2);
        label.setStyle("-fx-text-fill: " + color);
        label.setText(t.getText());
    }
    
    @FXML
    private void closeEditorAction(ActionEvent event) {
        System.out.println("Suljetaan editori...");
        exit();
    }
    
    @FXML
    private void helloButtonAction(ActionEvent event) {
        System.out.println("Hello World!");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void addInputAction(InputMethodEvent event) {
    }
    
}
