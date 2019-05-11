package com.matteon.pong.managers.bonus;

import com.matteon.pong.Ball;
import com.matteon.pong.Paddle;

import java.util.Random;

public class BonusManager {
    private Bonus actualBonus;

    public Bonus getBonus() {
        return actualBonus;
    }

    public void check(Ball b, Paddle player1, Paddle player2, boolean whosLast) {
        if (actualBonus != null && actualBonus.isHitted(b) && !actualBonus.isActive()) {
            actualBonus.activate(whosLast, player1, player2);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    float activeTime = 0;
                    Bonus temporary = actualBonus;
                    while (temporary.isActive()) {
                        try {
                            Thread.sleep(300);
                            activeTime += 0.1f;
                            if (activeTime > 3) {
                                temporary.deactivate();
                                return;
                            }
                        } catch (InterruptedException e) {
                           return;
                        }
                    }
                }
            }).start();
        }
    }

    public void spawnBonus() {
        Random r = new Random();
        int choice = r.nextInt(1);
        switch (choice) {
            case 0:
                actualBonus = new BonusSpeed("bonusSpeed.png");
                break;
            case 1:
                actualBonus = new BonusPoint("bonusPoint.png");
                break;
            case 2:
                actualBonus = new BonusLenght("bonusLenght.png");
                break;
        }
    }
}
