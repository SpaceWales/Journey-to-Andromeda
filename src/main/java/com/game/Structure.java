package com.game;

import java.util.Random;
import java.util.Scanner;

public class Structure {
    private int fuel;
    private int ammo;
    private int crew;
    private int currency;
    private boolean running;
    private double maxFuel;
    private int hitpoints;



    private int fuelPrice = 20;
    private int ammoPrice = 50;
    private int crewPrice = 250;
    private int hitpointPrice = 100;
    private Random rand = new Random();


    public Structure(){
        this.fuel = 100;
        this.ammo = 50;
        this.crew = 10;
        this.currency = 1000;
        this.maxFuel = this.fuel;
        this.hitpoints = 100;
    }

    public void setupHostile(){
        this.fuel = 25 + rand.nextInt(100);
        this.ammo = 25 + rand.nextInt(100);
        this.currency = 500 + rand.nextInt(2500);
    }

    public Structure(String s){
        //outpost setup
        setupTradeOutpost();
    }

    public void attack(Ship hostile){
        Random num = new Random();
        int ammoConsumed = 1 + num.nextInt(10);
        this.setAmmo(this.getAmmo() - ammoConsumed);
        System.out.println("it took " + ammoConsumed + " shots, but you got em!");
        //System.out.println("looting!");
        this.lootHostile(hostile);
    }

    public void takeDamage(){
        Random rand = new Random();
        int damageModifier = rand.nextInt(50);
        System.out.println("HIT! you take " + damageModifier + " damage!");
        this.hitpoints -= damageModifier;
        //System.out.println(this.hitpoints);
        if(hitpoints < 0) {
            running = false;
            System.out.println("dead");
        }
    }

    public void lootHostile(Ship hostile){
        System.out.println("Gained: ");
        System.out.println("Fuel: " + (hostile.getFuel() / 2));
        System.out.println("Ammo: " + (hostile.getAmmo() / 2));
        System.out.println("Currency: " + hostile.getCurrency() / 2);
        this.setFuel(this.getFuel() + (hostile.getFuel() / 2));
        this.setAmmo(this.getAmmo() + (hostile.getAmmo() / 2));
        this.setCurrency(this.getCurrency() + (hostile.getCurrency() / 2));
        System.out.println("new: " + this.toString());
    }


    public void removeInventory(int item,int amount){
        if(item==1){
            //fuel
            this.fuel -= amount;
        }
        if(item==2){
            //ammo
            this.ammo -= amount;
        }
        if(item==3){
            //crew
            this.crew -= amount;
        }
    }

    public void addItem(int item,int amount){
        boolean success = true;
        if(item==1){
            //fuel
            if(this.currency - (fuelPrice * amount) < 0){
                success = false;
            }
            else {
                this.currency -= fuelPrice * amount;
                this.fuel += amount;
            }
        }
        else if(item==2){
            //ammo
            if(this.currency - (ammoPrice * amount) < 0){
                success = false;
            }
            else {
                this.currency -= ammoPrice * amount;
                this.ammo += amount;
            }
        }
        else if(item==3){
            //crew
            if(this.currency - (crewPrice * amount) < 0){
                success = false;
            }
            else {
                this.currency -= crewPrice * amount;
                this.crew += amount;
            }
        }
        else if(item==4){
            //hitpoints
            if(this.currency - (hitpointPrice * amount) < 0){
                success = false;
            }
            else{
                this.currency -= hitpointPrice * amount;
                this.hitpoints += amount;
            }
        }
        else{
            System.out.println("invalid item.");
            return;
        }

        if(success){
            System.out.println("trade completed successfully");
        }
        else{
            System.out.println("you didnt have enough money for that!");
        }
    }

    public void friendlyBonus(){
        System.out.println("10% bonus!");
        System.out.print("Fuel: " + this.fuel + " => ");
        this.fuel += (this.fuel / 10);
        System.out.println(this.fuel);
        System.out.print("Ammo: " + this.ammo + " => ");
        this.ammo += (this.ammo / 10);
        System.out.println(this.ammo);
        System.out.print("Credits: " + this.currency + " => ");
        this.currency += (this.currency / 10);
        System.out.println(this.currency);
    }

    public int getHitpointPrice() {
        return hitpointPrice;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public int getFuelPrice() {
        return fuelPrice;
    }

    public int getAmmoPrice() {
        return ammoPrice;
    }

    public int getCrewPrice() {
        return crewPrice;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public boolean isRunning(){
        if(this.fuel > 0 && this.hitpoints > 0 && this.crew > 0){
            return this.running = true;
        }
        else{
            return this.running = false;
        }
    }


    public void setupTradeOutpost(){
        this.fuel = 5000;
        this.ammo = 5000;
        this.crew = 50;
        this.currency = 5000;
        this.hitpoints = 500;
    }


    public void traveling(){
        this.fuel--;
        double fuelPercentage = this.fuel / this.maxFuel * 100;
        System.out.println("fuel:" + (int)fuelPercentage + "%"
                            + " hull: " + this.hitpoints + "%");
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "fuel=" + fuel +
                ", ammo=" + ammo +
                ", crew=" + crew +
                ", currency=" + currency +
                ", hitpoints=" + hitpoints +
                '}';
    }
}
