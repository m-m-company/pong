package com.matteon.pong.managers.bonus;

import com.matteon.pong.Paddle;

public class BonusSpeed extends Bonus {
    public BonusSpeed(String path) {
        super(path);
    }

    @Override
    public void activate(boolean whosLast, Paddle player1, Paddle player2) {
        if (whosLast) {
            player1.setSpeed(Paddle.DEFAULT_SPEED * 2);
            affectedPaddle = player1;
        } else {
            player2.setSpeed(Paddle.DEFAULT_SPEED * 2);
            affectedPaddle = player2;
        }
    }

    @Override
    public void deactivate() {
        affectedPaddle.setSpeed(Paddle.DEFAULT_SPEED);
    }
}
