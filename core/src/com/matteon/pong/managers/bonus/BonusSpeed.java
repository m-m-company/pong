package com.matteon.pong.managers.bonus;

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
			affectedPaddle = player1;
		} else {
			affectedPaddle = player2;
		}
		affectedPaddle.getBonus().addFirst(this);
	}

	@Override
	public void deactivate() {
		this.isActive = false;
		affectedPaddle.getBonus().removeLast();
	}
}
