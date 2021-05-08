package com.game;

import java.util.Random;

public class Travel {
    private int travelTime;
    private static int day;
    private boolean arrive = false;
    public Travel(){
        Random rand = new Random();
        this.travelTime = 10 + rand.nextInt(20);
    }

    public boolean isArrive() {
        return arrive;
    }

    public void tick(Structure player) throws InterruptedException {
        //tick down travel time
        Thread.sleep(500);
        System.out.print("Day " + day + ": ");
        day++;
        if(this.travelTime==0)arrive = true;
        else {
            this.travelTime--;
            Encounter gen = new Encounter();
            if(gen.isChanceEncounter()){
                gen.enterEncounter(player);
            }
        }
    }

    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }

    public int getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
    }
}
