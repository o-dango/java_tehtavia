/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author camilla
 */
public class ReadAndWrite {
    
    private String filename;
    
    ReadAndWrite (String s) {
        
        filename = s;
        
    }
    
    public String readFile(String path) throws FileNotFoundException, IOException {

        System.out.println("Avataan tiedosto");
        String text;
        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            String temp;
            text = "";
            while((temp = input.readLine()) != null) {
                
                text = text + temp + '\n';
                
            }
            
            return text;
        } catch(FileNotFoundException ex) {
            return "Tiedostoa ei löytynyt!";
        } catch(IOException ex2) {
            return "Virhe tiedostonluvussa!";
        }
        
    }
    
    public String Write(String path, String text) throws IOException {
        
        System.out.println("Tallennetaan tiedosto");
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path))) {
            output.write(text);
            return text;
        } catch(IOException ex) {
            return "Tallennus epäonnistui!";
        }
        
    }

}

