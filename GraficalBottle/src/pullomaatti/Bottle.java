/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pullomaatti;


/**
 *
 * @author camilla
 */
public class Bottle {
    String name;
    String manufacturer;
    double volume;
    double price;
    
    public Bottle(String n, String m, double v, double p) {
        
        name = n;
        manufacturer = m;
        volume = v;
        price = p;
        
    }
    
    public String getBottle() {
        return name;
    }
    
    public String getManufacturer() {
        return manufacturer;
    }
    
    public double getVolume() {
        return volume;
    }
    
    public double getPrice() {
        return price;
    }
    
}
