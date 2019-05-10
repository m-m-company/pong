package com.matteon.pong.managers.bonus;

import com.matteon.pong.Paddle;

public class BonusPoint extends Bonus {
    public BonusPoint(String path) {
        super(path);
    }

    @Override
    public void activate(boolean whosLast, Paddle player1, Paddle player2) {
    	this.isActive = true;
    	this.used = true;
    	if (whosLast)
            player1.addPoint();
        else
            player2.addPoint();
    }

    @Override
    public void deactivate() {
    	this.isActive = false;
    }
}
