/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pullomaatti;

import java.util.Scanner;

/**
 *
 * @author camilla
 */
public class Pullomaatti {
    
    public static void main(String[] args) {
        
        String temp;
        int option;
        int choose;
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        BottleDispenser maatti = BottleDispenser.getInstance();
       
       do {
           
           System.out.println("\n*** LIMSA-AUTOMAATTI ***");
           System.out.println("1) Lisää rahaa koneeseen");
           System.out.println("2) Osta pullo");
           System.out.println("3) Ota rahat ulos");
           System.out.println("4) Listaa koneessa olevat pullot");
           System.out.println("0) Lopeta");
           System.out.print("Valintasi: ");
           temp = scan.nextLine();
           option = Integer.parseInt(temp);
           
           switch(option) {
               
               case 1:
                   System.out.print("Anna rahamäärä: ");
                   temp = scan2.nextLine();
                   maatti.addCoin(Double.parseDouble(temp));
                   break;
                   
               case 2:
                   maatti.printBottles();
                   System.out.print("Valintasi: ");
                   temp = scan2.nextLine();
                   choose = Integer.parseInt(temp);
                   maatti.buyBottle(choose);
                   break;
                   
               case 3:
                   maatti.returnCoins();
                   break;
                   
               case 4:
                   maatti.printBottles();
                   break;
                   
               case 0:
                   break;
                   
               default:
                   System.out.println("Virheellinen valinta!");
                   break;
           }
           
       } while(option != 0);
       
    }
    
}
