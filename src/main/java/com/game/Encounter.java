package com.game;

import java.util.Random;
import java.util.Scanner;

public class Encounter {
    //encounter has been generated
    private boolean chanceEncounter = false;

    public Encounter() {
        Random rand = new Random();
        int n = rand.nextInt(100);
        if (n < 20) {
            chanceEncounter = true;
        }
    }

    public boolean isChanceEncounter() {
        return chanceEncounter;
    }

    //chance encounter here

    public void enterEncounter(Structure player) {
        String[] commentary = {"you wake up hungry", "your first mate wakes you up to tell you he had a bad dream", "you lay awake and contemplate the purpose of life",
                "you wander the halls aimlessly", "you hear screaming from a corridor, you quickly turn and walk the other way",
                "you get bored and put a kick me sign on the back of your pilots uniform", "you wander to the mess hall for coffee, again"};
        Random roll = new Random();
        int encounterRoll = roll.nextInt(100);
        //0-33 commentary, 34-66 breakdown, deplete random resource, 67-100 ship
        if (encounterRoll < 40) {
            int ran = roll.nextInt(commentary.length);
            //System.out.println("encounter roll < 34: " + ran);
            System.out.println(commentary[ran]);
        } else if (encounterRoll < 65) {
            String[] badShit = {"a fire","band of pirates","mutiny","space anomaly","weird alien dude","beam of light"};
            String msg = "captain, wake up! there was a ";
            int badmsg = roll.nextInt(badShit.length);
            msg += badShit[badmsg] + " and now half the ";
            int ran = roll.nextInt(4);
            if (ran == 0) {
                msg += "fuel is gone!";
                player.setFuel(player.getFuel() / 2);
                msg += "\nwe only have " + player.getFuel() + " left!";
                System.out.println(msg);
            }
            if (ran == 1) {
                msg += "ammo is gone!";
                player.setAmmo(player.getAmmo() / 2);
                msg += "\nwe only have " + player.getAmmo() + " left!";
                System.out.println(msg);
            }
            if (ran == 2) {
                msg += "crew disappeared!";
                player.setCrew(player.getCrew() / 2);
                msg += "\n there's only " + player.getCrew() + " of us left!";
                System.out.println(msg);
            }
            if (ran == 3) {
                msg += "credits are gone! filthy pirates!";
                player.setCurrency(player.getCurrency() / 2);
                msg += "\nwe only have " + player.getCurrency() + " left!";
            }
        } else {
            Ship npc = new Ship();
            System.out.println("a ship appears!");
            if (npc.isFriendly()) {
                System.out.println("they were friendly!");
                player.friendlyBonus();
            } else {
                //System.out.println("hostiles!");
                npc.setupHostile();
                fight(player, npc);
            }

        }
        Scanner input = new Scanner(System.in);
        System.out.println("enter to continue");
        String s = input.nextLine();
    }

    public void fight(Structure player, Ship hostile) {
        System.out.println("hostile ship incoming!");
        System.out.println("1. Fight?\n2.Run?");
        Scanner input = new Scanner(System.in);
        String user = input.nextLine();
        if (user.equals("1")) {
            //fight
            System.out.println("you prepare to fight!");
            Random roll = new Random();
            int takeDamage = roll.nextInt(10);
            if (takeDamage < 7) {
                player.takeDamage();
            }
            player.attack(hostile);
        }
        if (user.equals("2")) {
            //run
            //enter a modifier here to have a chance to take damage
            System.out.println("you try to get away!");
            Random roll = new Random();
            int takeDamage = roll.nextInt(10);
            if (takeDamage < 3) {
                player.takeDamage();
            }
            System.out.println("you got away!");
        }
    }


}
