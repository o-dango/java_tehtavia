/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pullomaatti;

import java.util.ArrayList;

/**
 *
 * @author camilla
 */
public class BottleDispenser {
    
    private int bottles;
    private double coins;
    private int i;
    private String temp;
    static private BottleDispenser db = null;
    ArrayList<String> bottle_array;
    ArrayList<Double> price_array;
    ArrayList<Double> volume_array;
    
    private BottleDispenser() {
        
        coins = 0;
        bottles = 36;
        
        bottle_array = new ArrayList<>();
        price_array = new ArrayList<>();
        volume_array = new ArrayList<>();
        
        Bottle pepsi_small = new Bottle("Pepsi Max", "Pepsi", 0.5, 1.8);
        Bottle pepsi_big = new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2);
        Bottle cola_small = new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2.0);
        Bottle cola_big = new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5);
        Bottle fanta_small = new Bottle("Fanta Zero", "Coca-Cola", 0.5, 1.95);
        Bottle fanta_big = new Bottle("Fanta Zero", "Coca-Cola", 1.5, 2.45);
        
        int i;
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(pepsi_small.getBottle());
            price_array.add(pepsi_small.getPrice());
            volume_array.add(pepsi_small.getVolume());
        }
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(pepsi_big.getBottle());
            price_array.add(pepsi_big.getPrice());
            volume_array.add(pepsi_big.getVolume());
        }
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(cola_small.getBottle());
            price_array.add(cola_small.getPrice());
            volume_array.add(cola_small.getVolume());
        }
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(cola_big.getBottle());
            price_array.add(cola_big.getPrice());
            volume_array.add(cola_big.getVolume());
        }
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(fanta_small.getBottle());
            price_array.add(fanta_small.getPrice());
            volume_array.add(fanta_small.getVolume());
        }
        for(i = 0; i <= bottles/6; i++ ) {
            bottle_array.add(fanta_big.getBottle());
            price_array.add(fanta_big.getPrice());
            volume_array.add(fanta_big.getVolume());
        }

        
        
    }
    
    static public BottleDispenser getInstance() {
        
        if(db == null) {
            db = new BottleDispenser();
        }
        
        return db;
        
    }
    
    
    public void addCoin(double c) {
        
        coins = coins + c;
        coins = Math.round(coins * 100.0) / 100.0;
        System.out.println("Klink! Lisää rahaa laitteeseen!");
                
    }
    
    public void buyBottle(int o) {
        
        if(o <= bottles) {
        
            if(bottles > 0 && coins >= price_array.get(o-1) ) {
                bottles--;
                System.out.println("KACHUNK! "+ bottle_array.get(o-1) + " tipahti masiinasta!");
                coins = coins - price_array.get(o-1);
                bottle_array.remove(o-1);
                price_array.remove(o-1);
                volume_array.remove(o-1);
                bottles = bottle_array.size();
            
            }
        
            else if(coins < price_array.get(o-1) && bottles > 0) {
                System.out.println("Syötä rahaa ensin!");
            }
            
        }
            
        else if (bottles == 0){
                System.out.println("Pulloautomaatti on tyhjä!");
        }
        
        else {
            System.out.println("Virheellinen syöte!");
        }
        
    }
    
    public String returnCoins() {
        
        String tempc;
        String tempe;
        String temps;
        
        if(coins > 0) {
            tempc = Double.toString(coins);
            tempe = tempc.split("\\.")[0];
            temps = tempc.split("\\.")[1];
            temps = temps.substring(0,1);
            
            if(temps.equals("0"))
                temps = temps + "5";
            else {
                try {
                temps = (tempc.split("\\.")[1]).substring(0,2);
                if (temps.equals("19"))
                    temps = "20";
                } catch (StringIndexOutOfBoundsException ex0) {
                    temps = (tempc.split("\\.")[1]).substring(0,1);
                    temps = temps + "0";
                }
            }
                
            String money = tempe + "," + temps;
            System.out.print("Klink klink. Sinne menivät rahat!");
            System.out.printf(" Rahaa tuli ulos %s,%s\n", tempe, temps);
        
            coins = 0;
            return money;
            
        }
        
        else {
            System.out.println("Ei palautettavaa rahaa!");
            return "";
        }
        
    }
    
    public void printBottles() {
        
        for(i=1; i <= bottles; i++) {
            System.out.println(i + ". Nimi: " + bottle_array.get(i-1));
            System.out.print("\tKoko: " + volume_array.get(i-1));
            System.out.println("\tHinta: " + price_array.get(i-1));
            
        }
        
    }
    
    public ArrayList getBottles() {
        
        return bottle_array;
        
    }
    
    public ArrayList getPrices() {
        
        return price_array;
        
    }
    
    public ArrayList getVolumes() {
        
        return volume_array;
        
    }
    
    public double getCoins() {
        
        return Math.round(coins * 100.0) / 100.0;
        
    }
    
}
