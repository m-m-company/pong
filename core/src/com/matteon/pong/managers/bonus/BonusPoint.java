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
		if (whosLast) {
			player1.addPoint();
			player1.setScore(player1.getScore() + 100);
		} else {
			player2.addPoint();
			player2.setScore(player2.getScore() + 100);
		}
	}

	@Override
	public void deactivate() {
		this.isActive = false;
	}
}
