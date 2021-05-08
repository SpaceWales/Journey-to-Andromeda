package com.game;

import java.util.Random;

public class Ship extends Structure{

    boolean friendly = true;

    public Ship(){
        super();
        Random r = new Random();
        int rand = r.nextInt(10);
        if(rand < 5){
            this.friendly = false;
        }
    }

    public boolean isFriendly() {
        return this.friendly;
    }
}
