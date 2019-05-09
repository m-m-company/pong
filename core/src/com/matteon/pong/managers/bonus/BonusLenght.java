package com.matteon.pong.managers.bonus;

import com.matteon.pong.Paddle;

public class BonusLenght extends Bonus {
    private static final int AUGMENT = 40;

    public BonusLenght(String path) {
        super(path);
    }

    @Override
    public void activate(boolean whosLast, Paddle player1, Paddle player2) {
    	this.isActive = true;
    	if (whosLast) {
            player1.setHeight(Paddle.HEIGHT + AUGMENT);
            affectedPaddle = player1;
        } else {
            affectedPaddle = player2;
            player2.setHeight(Paddle.HEIGHT + AUGMENT);
        }
    }

    @Override
    public void deactivate() {
    	this.isActive = false;
        affectedPaddle.setHeight(Paddle.HEIGHT);
    }
}
