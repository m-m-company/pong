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
        	player1.setSpeed(Paddle.DEFAULT_SPEED * 2);
        	player1.myColor = Color.GREEN;
        	affectedPaddle = player1;
        } else {
        	System.out.println(player2.getSpeed());
        	player2.myColor = Color.GREEN;
        	affectedPaddle = player2;
        }
    }

    @Override
    public void deactivate() {
    	this.isActive = false;
    	affectedPaddle.myColor = Color.WHITE;
        affectedPaddle.setSpeed(Paddle.DEFAULT_SPEED);
    }
}
