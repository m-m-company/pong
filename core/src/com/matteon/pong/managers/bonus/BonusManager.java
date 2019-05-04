package com.matteon.pong.managers.bonus;

import com.matteon.pong.Ball;
import com.matteon.pong.Paddle;

import java.util.Random;

public class BonusManager {
    private Bonus actualBonus;
    public void check(Ball b, Paddle player1, Paddle player2, boolean whosLast){
        if (actualBonus.isHitted(b))
            actualBonus.activate(whosLast, player1, player2);
    }
    public void spawnBonus(){
        Random r = new Random();
        int choice = r.nextInt(3);
        switch (choice){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
}
