/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import java.util.ArrayList;

/**
 *
 * @author camilla
 */
public class Circles {
    
    static private Circles c = null;
    private ArrayList<String> names;
    private ArrayList<Double> x;
    private ArrayList<Double> y;
    
    private Circles() {
        
        names = new ArrayList<>();
        x = new ArrayList<>();
        y = new ArrayList<>();
        
    }
    
    static public Circles getInstance() {
        
        if(c == null) {
            c = new Circles();
        }
        
        return c;
        
    }
    
    public double getPositionY(int i) {
        
        try {
            if(names.isEmpty())
                return 0;
            else
                return y.get(i);
            
        } catch(Exception ex) {
            return -1;
        }
        
    }
    
    public double getPositionX(int i) {
        
        try {
            if(names.isEmpty())
                return 0;
            else
                return x.get(i);
            
        } catch(Exception ex) {
            return -1;
        }
        
    }
    
    public String getName(int i) {
        
        try {
            if(names.isEmpty() && i == 0)
                return "höhö";
            else
                return names.get(i);
            
        } catch(Exception ex) {
            return "null";
        }
        
    }
    
    public void saveInfo(String n, double xp, double yp) {
        
        names.add(n);
        x.add(xp);
        y.add(yp);
        
    }
    
}
