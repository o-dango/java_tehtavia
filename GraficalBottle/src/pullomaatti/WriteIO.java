/*
 * Copyright (C) 2016 camilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pullomaatti;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author camilla
 */
public class WriteIO {
    
    private final String filename;
    static private WriteIO writer = null;
    private final String path = System.getProperty("user.dir") + "/";
    
    private WriteIO() {
        
        filename = "receipt.txt";
        
    }
    
    static public WriteIO getInstance() {
        
        if(writer == null) {
            
            writer = new WriteIO();
            
        }
        
        return writer;
        
    }
    
    public void writeReceipt(String info) {
        
        Date timenow = new Date();
        System.out.println("Tallennetaan tiedosto");
        try (BufferedWriter output = new BufferedWriter(new FileWriter(path + filename))) {
            
            String stuff = timenow +"\n"+ info;
            output.write(stuff);
        } catch(IOException ex) {
            System.out.println("Tallennus epäonnistui!");
        }
        
    }
    
}
