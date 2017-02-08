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
public class Lines {
    
    private ArrayList<Double> x_start;
    private ArrayList<Double> x_end;
    private ArrayList<Double> y_start;
    private ArrayList<Double> y_end;
    static private Lines lines = null;
    
    private Lines() {
    
    x_start = new ArrayList<>();
    x_end = new ArrayList<>();
    y_start = new ArrayList<>();
    y_end = new ArrayList<>();
    
    }
    
    static public Lines getInstance() {
        
        if(lines == null) {
            lines = new Lines();
        }
        
        return lines;
        
    }
    
    public void setLine(double start_x, double start_y, double end_x, double end_y) {
        
        x_start.add(start_x);
        y_start.add(start_y);
        x_end.add(end_x);
        y_end.add(end_y);
        
    }
    
    public double getStartX() {
        
        return x_start.get(x_start.size()-1);
        
    }
    
    public double getStartY() {
        
        return y_start.get(x_start.size()-1);
        
    }
    
    public double getEndX() {
        
        return x_end.get(x_start.size()-1);
        
    }
    
    public double getEndY() {
        
        return y_end.get(x_start.size()-1);
        
    }
    
    public int size() {
        
        return x_start.size();
        
    }
    
}
