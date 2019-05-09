package com.matteon.pong.managers.bonus;

import com.badlogic.gdx.Gdx;
import com.matteon.pong.Ball;
import com.matteon.pong.Paddle;

import java.util.Random;

public class BonusManager {
    private Bonus actualBonus;
    private float activeTime = 0;
    public Bonus getBonus() {
    	return actualBonus;
    }
    public void check(Ball b, Paddle player1, Paddle player2, boolean whosLast){
    	if (actualBonus!=null && actualBonus.isHitted(b) && !actualBonus.isActive()) {
            actualBonus.activate(whosLast, player1, player2);
            new Thread(new Runnable() {
				@Override
				public void run() {
					while(actualBonus.isActive()) {
						try {
							Thread.sleep(1000);
							activeTime+=0.1f;
							if(activeTime>3) {
								actualBonus.deactivate();
								return;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
            }).start();
        }
    }
    public void spawnBonus(){
        Random r = new Random();
        int choice = r.nextInt(3);
        switch (choice){
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
