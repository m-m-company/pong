package com.matteon.pong.managers.bonus;

import com.badlogic.gdx.graphics.Color;
import com.matteon.pong.Paddle;

public class BonusSpeed extends Bonus {
    public BonusSpeed(String path) {
        super(path);
    }

    @Override
    public void activate(boolean whosLast, Paddle player1, Paddle player2) {
    	this.isActive = true;
    	this.used = true;
    	if (whosLast) {
        	player1.setSpeed(player1.getSpeed() * 2);
        	player1.myColor = Color.GREEN;
        	affectedPaddle = player1;
        } else {
    	    player2.setSpeed(player2.getSpeed()*2);
        	player2.myColor = Color.GREEN;
        	affectedPaddle = player2;
        }
    }

    @Override
    public void deactivate() {
    	this.isActive = false;
    	affectedPaddle.myColor = Color.WHITE;
        affectedPaddle.setSpeed(affectedPaddle.getSpeed()/2);
    }
}
