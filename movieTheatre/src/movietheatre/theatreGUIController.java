/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietheatre;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author camilla
 */
public class theatreGUIController implements Initializable {

    @FXML
    private ListView<String> showLabel;
    @FXML
    private TextField startTime;
    @FXML
    private TextField endTime;
    @FXML
    private Button listMovies;
    @FXML
    private Button searchName;
    @FXML
    private TextField nameMovie;
    @FXML
    private DatePicker pickDate;
    @FXML
    private ComboBox<String> chooseTheatre;
    @FXML
    private ImageView prettyPic;
    
    private readWeb rw;
    private ArrayList<String> theatres;
    private Theatres names;
    private String date;
    private Movies movieList;
    SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
    Calendar datenow = Calendar.getInstance();
    private String start_start = "00:00";
    private String start_end = "23:59";

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        File file = new File("/home/camilla/osu!/Skins/Jantsiv6/menu-button-background.png");
        Image image = new Image(file.toURI().toString());
        prettyPic.setImage(image);
        
        try {
            rw = new readWeb("http://www.finnkino.fi/xml/TheatreAreas/");
            String content = rw.getContent();
            names = new Theatres(content);
            //showLabel.setText(rw.getContent());
            theatres = new ArrayList<>();
            
            int i = 0;
            while(i < names.getMap().size()) {
                theatres.add((String) names.getMap().keySet().toArray()[i]);
                i++;
            }
            i = 0;
            while(i < theatres.size()) {
                chooseTheatre.getItems().add(theatres.get(i));
                i++;
            }
            
        } catch (IOException ex) {
            Logger.getLogger(theatreGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void getTheatreAction(ActionEvent event) {
        System.out.println(chooseTheatre.getValue());
    }

    @FXML
    private void getDateAction(ActionEvent event) {
        String[] temp;
        date = pickDate.getValue().format(DateTimeFormatter.ISO_DATE);
        temp = date.split("-");
        date = temp[2] + "." + temp[1] + "." + temp[0];
        
    }

    @FXML
    private void listMoviesAction(ActionEvent event) {

        showLabel.getItems().clear();
        String[] start_time = startTime.getText().split(":");
        String[] end_time = endTime.getText().split(":");
        
        System.out.println(Arrays.toString(start_time) + " " + Arrays.toString(end_time));
        
        if(startTime.getText().trim().equals("") && endTime.getText().trim().equals("")) {
            
            start_time = start_start.split(":");
            end_time = start_end.split(":");
            System.out.println(Arrays.toString(start_time) + " " + Arrays.toString(end_time));
            date = format.format(datenow.getTime());
            
            printMovies(start_time, end_time, date);
            
        }
        
        
        else if(startTime.getText().trim().equals("") || endTime.getText().trim().equals("")) {
            
            showLabel.getItems().add("Valitse ensin alkamisaika!");
            
        }
        
        else if(date.trim().equals("")) {
            
            showLabel.getItems().add("Valitse ensin esityspäivä!");
            
        }
        
        else {
        
            printMovies(start_time, end_time, date);

        }
        
    }
    
    @FXML
    private void searchMovieAction(ActionEvent event) {
        
        readWeb rw_movies;
        String[] temp_time;
        PlacesAndTime placeList;
        showLabel.getItems().clear();
        int j = 0;
        while(names.getMap().size() > j) {
            try {
                System.out.println("Listataan elokuvia osoitteesta:");
                System.out.println("http://www.finnkino.fi/xml/Schedule/");
                rw_movies = new readWeb("http://www.finnkino.fi/xml/Schedule/?area=" + 
                        names.getMap().get(theatres.get(j)));
                String content = rw_movies.getContent();
                placeList = new PlacesAndTime(content);

                System.out.println(placeList.getMap().size());

                int i = 0;
                while(placeList.getMap().containsKey(String.valueOf(i)) == true) {

                    String temp = String.valueOf(i);

                    System.out.println(placeList.getMap().get(temp));
                    String name = placeList.getMap().get(temp);
                    String genre = placeList.getMap().get(name);
                    String start = placeList.getMap().get(genre);
                    String end = placeList.getMap().get(start);
                    String show_date = placeList.getMap().get(end);
                    String place = placeList.getMap().get(show_date);

                    temp_time = start.split("T");
                    temp_time[1] = temp_time[1].replace(':', '.');
                    start = temp_time[1].substring(0, 5);

                    temp_time = end.split("T");
                    temp_time[1] = temp_time[1].replace(':', '.');
                    end = temp_time[1].substring(0, 5);

                    temp_time = show_date.split("T");
                    temp_time = temp_time[0].split("-");
                    show_date = temp_time[2] + "." + temp_time[1] + "." + temp_time[0];

                    if(name.contains(nameMovie.getText())) {
                        showLabel.getItems().add(show_date + " " + name + "\n" + genre + "\n" + start + " – " + end
                                                    + "\n" + place);
                    }

                    i++;

                }


            } catch (IOException ex) {
                Logger.getLogger(theatreGUIController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            j++;
        
        }
        
    }
    
    private boolean checkTime(String[] current, String[] start, String[] end) {
        
        String temp = current[0] + current[1];
        int current_time = Integer.parseInt(temp);
        
        temp = start[0] + start[1];
        int start_time = Integer.parseInt(temp);
        
        temp = end[0] + end[1];
        int end_time = Integer.parseInt(temp);
        
        if(Integer.parseInt(start[0]) > 23 || Integer.parseInt(start[1]) > 59 
                || Integer.parseInt(end[0]) > 23 || Integer.parseInt(end[1]) > 59) {
            
            return false;
            
        }
        
        else
            return current_time >= start_time && current_time <= end_time;
        
    }
    
    private void printMovies(String[] start_time, String[] end_time, String date) {
        
        readWeb rw_movies;
        String[] temp_time;
        boolean flag;
        try {
            System.out.println("Listataan elokuvia osoitteesta:");
            System.out.println("http://www.finnkino.fi/xml/Schedule/?area=" + names.getMap().get(chooseTheatre.getValue()) + "&dt=" + date);
            rw_movies = new readWeb("http://www.finnkino.fi/xml/Schedule/?area=" + names.getMap().get(chooseTheatre.getValue()) + "&dt=" + date);
            String content = rw_movies.getContent();
            movieList = new Movies(content);


        } catch (IOException ex) {
            Logger.getLogger(theatreGUIController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int i = 0;
        while(movieList.getMap().containsKey(String.valueOf(i)) == true) {
            System.out.println(i);
            String temp = String.valueOf(i);

            System.out.println(movieList.getMap().get(temp));
            String name = movieList.getMap().get(temp);
            String genre = movieList.getMap().get(name);
            String start = movieList.getMap().get(genre);
            String end = movieList.getMap().get(start);
            String place = movieList.getMap().get(end);

            temp_time = start.split("T");
            temp_time[1] = temp_time[1].replace(':', '.');
            start = temp_time[1].substring(0, 5);

            temp_time = start.split("\\.");

            flag = checkTime(temp_time, start_time, end_time);

            if(flag == true) {
                temp_time = end.split("T");
                temp_time[1] = temp_time[1].replace(':', '.');
                end = temp_time[1].substring(0, 5);

                place = place.replace('s', 'S');

                showLabel.getItems().add(name + "\n" + genre + "\n" + start + " – " + end
                                            + "\n" + place);
            }
            
            i++;

        }
        
    }
    
}
