package com.game;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Galaxy extends Structure {
    private String name;
    private int planets;
    private String[] nameOptions = {"Janeway","Icarus","Hogwarts","Gregor","FreddyKrueger",
                                    "Eye of Sauron","Danica","Cigar","Beelzebub","Andromeda"};
    static public int count = 0;
    public Galaxy(){
        //generate some stuff about galaxies
        this.name = nameOptions[count];
        count++;
    }

    public String getName() {
        return name;
    }

    public void trade(Structure player,Structure outpost){
        Scanner input = new Scanner(System.in);
        String msg = "You find a small shop on the ";
        Random rand = new Random();
        int n = 1 + rand.nextInt(9);
        msg += n;
        if(n == 1)msg += "st ";
        if(n == 2)msg += "nd ";
        if(n==3)msg += "rd ";
        msg+="th planet in the system..";
        System.out.println(msg);
        boolean trading = true;
        System.out.println("Player inventory::");
        System.out.println(player);
        System.out.println("Outpost inventory::");
        System.out.println(outpost);
        //setting up buy or sell option!
        System.out.println("you come to 1. buy or 2. sell?");
        String buyOrSell = input.nextLine();
        boolean validInput = false;
        do {
            System.out.println("what would you like to buy? enter choice");
            System.out.println("1. Fuel. player: {" + player.getFuel()
                    + "} available: {" + outpost.getFuel()
                    + "} price: {" + outpost.getFuelPrice() + "}");
            System.out.println("2. Ammo. player: {" + player.getAmmo()
                    + "} available: {" + outpost.getAmmo()
                    + "} price: {" + outpost.getAmmoPrice() + "}");
            System.out.println("3. Crew. player: {" + player.getCrew()
                    + "} available: {" + outpost.getCrew()
                    + "} price: {" + outpost.getCrewPrice() + "}");
            System.out.println("4. Repair packs. hull% = {" + player.getHitpoints()
                    + "} available: {" + outpost.getHitpoints()
                    + "} price: {" + outpost.getHitpointPrice() + "}");
            System.out.println("5. Nothing, you regret stepping foot in here. ");
            //if and check here, send to buy method in structure
            int user = Integer.parseInt(input.nextLine());
            if (user == 5) {
                System.out.println("you exit quickly.");
                trading = false;
            }
            else {
                if(user > 5){
                    System.out.println("invalid input");
                    continue;
                }
                System.out.println("Enter amount to buy:");
                int amount = Integer.parseInt(input.nextLine());
                outpost.removeInventory(user, amount);
                player.addItem(user, amount);
                System.out.println("New inventory::");
                System.out.println("player: " + player);
                //System.out.println("outpost: " + outpost);
                System.out.println("Continue on? y or n");
                String continueing = input.nextLine();
                if (continueing.equals("Y") || continueing.equals("y")) trading = false;
            }
        }while (trading) ;
    }


    public void arrivalInGalaxy(Structure player,Structure outpost){
        Scanner input = new Scanner(System.in);
        System.out.println("Whew, you have arrived in " + this.name + " galaxy..");
        System.out.println("options to do?: ");
        boolean readyToGo = false;
        do{
            System.out.println("1. find outpost to trade.");
            System.out.println("2. continue on to next galaxy");
            String user = input.nextLine();
            if(user.equals("1")){
                //shift inventory around
                //System.out.println("you shift some inventory around");
                trade(player,outpost);
                readyToGo = true;
            }
            if(user.equals("2")){
                System.out.println("you continue on to the next system");
                readyToGo = true;
            }
        }
        while(!readyToGo);
    }

}
