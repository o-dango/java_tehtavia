/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficalbottle;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pullomaatti.BottleDispenser;
import pullomaatti.WriteIO;

/**
 *
 * @author camilla
 */
public class BottleGUIController implements Initializable {
    
    @FXML
    private Button returnMoneyButton;
    @FXML
    private Button addMoneyButton;
    @FXML
    private TextArea textLabel;
    @FXML
    private ComboBox<String> chooseProduct;
    @FXML
    private ComboBox<String> chooseSize;
    @FXML
    private Slider moneySlider;
    @FXML
    private Button printReceipt;
    @FXML
    private Button buyProduct;
    @FXML
    private Button closeWindowButton;
    @FXML
    private ImageView bottleImage;
    @FXML
    private Label showMoney;
    @FXML
    private TextArea showPrices;
    
    private BottleDispenser bd;
    private WriteIO write;
    ArrayList<String> bottles_array;
    ArrayList<Double> prices_array;
    ArrayList<Double> volumes_array;
    boolean flag = false;
    String receipt = "";
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int i = 1;
        File file = new File("/home/camilla/Kuvat/Random/Nyanko-sensei-sake.jpg");
        Image image = new Image(file.toURI().toString());
        bottleImage.setImage(image);
        
        bd = BottleDispenser.getInstance();
        write = WriteIO.getInstance();
        bottles_array = bd.getBottles();
        prices_array = bd.getPrices();
        volumes_array = bd.getVolumes();
        
        showPrices.setText(bottles_array.get(0) + " " + volumes_array.get(0));
        showPrices.setText(showPrices.getText() + "\n" + prices_array.get(0) + "€");
        while(i < prices_array.size()) {
            
            if((prices_array.get(i)).equals(prices_array.get(i-1))) {
                //
            }
            else {
                showPrices.setText(showPrices.getText() + "\n" + bottles_array.get(i) + " " + volumes_array.get(i));
                showPrices.setText(showPrices.getText() + "\n" + prices_array.get(i) + "€");
            }
            
            i++;
            
        }
        
        chooseProduct.getItems().add("Valitse tuote");
        chooseProduct.getItems().add("Pepsi Max");
        chooseProduct.getItems().add("Coca-Cola Zero");     
        chooseProduct.getItems().add("Fanta Zero");
        
        chooseSize.getItems().add("Valitse koko");
        chooseSize.getItems().add("0.5");
        chooseSize.getItems().add("1.5");
        
    }    

    @FXML
    private void returnMoneyAction(ActionEvent event) {
        
        String money = bd.returnCoins();
        if(money.equals(""))
            textLabel.setText("Ei palautettavaa rahaa!");
        else
            textLabel.setText("Rahaa palautettiin: " + money + "€");
        
        showMoney.setText(bd.getCoins() + "€");
    }

    @FXML
    private void addMoneyAction(ActionEvent event) {
        
        double temp = Math.round(moneySlider.getValue() * 100.0) / 100.0;
        bd.addCoin(temp);
        textLabel.setText("Rahaa lisättiin: " + temp + "€");
        moneySlider.setValue(0);
        showMoney.setText(bd.getCoins() + "€");
        
    }

    @FXML
    private void chooseProductAction(ActionEvent event) {
        
        if(flag == false) {
            if(chooseSize.getValue() == null || (chooseSize.getValue()).equals("Valitse koko") || chooseProduct.getValue() == null || (chooseProduct.getValue()).equals("Valitse tuote")) {
                textLabel.clear();
            }
            else {
                textLabel.setText("Valittuna tuote: " +"\n"+ chooseProduct.getValue() + " ");
                textLabel.setText(textLabel.getText() + chooseSize.getValue());
            }
        }
        else {
            flag = false;
            textLabel.clear();
            
        }
            
    }
    
    @FXML
    private void chooseSizeAction(ActionEvent event) {
        
        if(flag == false) {
            
            if(chooseSize.getValue() == null || (chooseSize.getValue()).equals("Valitse koko") || chooseProduct.getValue() == null || (chooseProduct.getValue()).equals("Valitse tuote")) {
                textLabel.clear();
            }
            else {
                textLabel.setText("Valittuna tuote: " +"\n"+ chooseProduct.getValue() + " ");
                textLabel.setText(textLabel.getText() + chooseSize.getValue());
            }
            
        }
        else {
            flag = false;
            textLabel.clear();
            
        }
        
    }

    @FXML
    private void buyProductAction(ActionEvent event) {
        
        int i = 1;
        String help = chooseProduct.getValue();
        if(chooseSize.getValue().equals("Valitse koko")) {
            textLabel.setText("Valitse ensin koko!");
        }
        else if(chooseProduct.getValue().equals("Valitse tuote")) {
            textLabel.setText("Valitse ensin tuote!");
        }
        else {
            double volume = Double.parseDouble(chooseSize.getValue());

            while(i < bottles_array.size()) {

                if(help.equals(bottles_array.get(i-1)) && volume == volumes_array.get(i-1) && prices_array.get(i-1) <= bd.getCoins()) {
                    bd.buyBottle(i);
                    showMoney.setText(bd.getCoins() + "€");
                    textLabel.setText("Ostettiin tuote:\n" + bottles_array.get(i-1) + " " + volumes_array.get(i-1));
                    receipt = bottles_array.get(i-1) + " " + volumes_array.get(i-1) + "l " + prices_array.get(i-1) + "€";
                    
                    bottles_array.remove(i-1);
                    volumes_array.remove(i-1);
                    prices_array.remove(i-1);
                    flag = true;
                    break;
                }
                else if(help.equals(bottles_array.get(i-1)) && volume == volumes_array.get(i-1) && prices_array.get(i-1) > bd.getCoins()) {
                    textLabel.setText("Ei riittävästi rahaa\nsyötä vielä " + (Math.round((prices_array.get(i-1)-bd.getCoins())*100.0)/100.0 + "€"));
                    System.out.println((prices_array.get(i-1)-bd.getCoins()));
                    break;
                }
                else
                    textLabel.setText("Tuote loppu!");

                i++;
            }

            //chooseProduct.setValue("Valitse tuote");
            //chooseSize.setValue("Valitse koko");
        }
    }

    @FXML
    private void closeWindowAction(ActionEvent event) {
        System.out.println("Suljetaan automaatti...");
        exit();
    }
    
    @FXML
    private void showCurrentMoney(MouseEvent event) {
        double temp = Math.round(moneySlider.getValue() * 100.0) / 100.0;
        textLabel.setText("Rahaa lisätään: " + temp + "€");
    }

    @FXML
    private void printReceiptAction(ActionEvent event) {
        System.out.println("Tulostetaan kuitti.");
        write.writeReceipt(receipt);
        textLabel.setText("Viimeeksi ostettiin:\n" + receipt);
    }

}
