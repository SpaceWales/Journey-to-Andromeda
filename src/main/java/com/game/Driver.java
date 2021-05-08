package com.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) throws InterruptedException {

        //just get the core working

        //generate ship object
        //"travel" to landmark
        //have an option prompt at landmark
        //"travel" to next landmark
        //continue until n landmarks are reached

        Scanner input = new Scanner(System.in);

        Structure player = new Structure();

        List<Galaxy> landmarks = new ArrayList<Galaxy>();
        //gen a list of 10 galaxies
        for (int i = 0; i < 10; i++) {
            landmarks.add(new Galaxy());
        }

        boolean winCondition = false;

        for (Galaxy g : landmarks) {
            Travel nextDestination = new Travel();
            while (!nextDestination.isArrive() && player.isRunning()) {
                //System.out.println("");
                nextDestination.tick(player);
                player.traveling();
            }
            if (player.isRunning()) {
                Structure outpost = new Structure("outpost");
                if (g.getName().equals("Andromeda")) {
                    winCondition = true;
                    break;
                }
                g.arrivalInGalaxy(player, outpost);
                //setup a trade method, pass in an outpost and player
            }
        }

        if (winCondition && player.isRunning()) {
            System.out.println("Congrats! you made it to Andromeda!");
        } else {
            System.out.println("sorry you lost :(");
        }
    }
}
