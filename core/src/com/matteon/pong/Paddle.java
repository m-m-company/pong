package com.matteon.pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Queue;
import com.matteon.pong.managers.bonus.Bonus;
import com.matteon.pong.managers.bonus.BonusLenght;
import com.matteon.pong.managers.bonus.BonusSpeed;

public class Paddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 45;
	public static final int DEFAULT_SPEED = 200;
	public static final int ADVANCED_SPEED = 400;
	public Color myColor;
	//Bonus
	private Queue<Bonus> bonus;
	private int speed;
	private Integer points;
	private Integer score;
	private Integer currentDefaultSpeed;

	public Integer getScore() {
		return score;
	}

	public void setCurrentDefaultSpeed(Integer currentDefaultSpeed) {
		this.currentDefaultSpeed = currentDefaultSpeed;
	}

	public void setScore(Integer score) {
		this.score = score;
		if (this.score < 0)
			this.setScore(0);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Integer getPoints() {
		return points;
	}

	public void addPoint() {
		points++;
	}

	public Paddle(int x, int y) {
		this.setX(x);
		this.setY(y);
		this.setWidth(Paddle.WIDTH);
		this.setHeight(Paddle.HEIGHT);
		myColor = Color.WHITE;
		points = 0;
		speed = DEFAULT_SPEED;
		currentDefaultSpeed = DEFAULT_SPEED;
		score = 0;
		bonus = new Queue<Bonus>();
	}

	public void updateStats() {
		this.setHeight(HEIGHT);
		this.setSpeed(currentDefaultSpeed);
		this.myColor = Color.WHITE;
		for (int i = bonus.size - 1; i >= 0; i--) {
			if (bonus.get(i) instanceof BonusLenght) {
				this.setHeight(HEIGHT + BonusLenght.AUGMENT);
				myColor = Color.RED;
			} else if (bonus.get(i) instanceof BonusSpeed) {
				if (this.speed == ADVANCED_SPEED) {
					currentDefaultSpeed = ADVANCED_SPEED;
					this.setSpeed(ADVANCED_SPEED * 2);
				} else {
					currentDefaultSpeed = DEFAULT_SPEED;
					this.setSpeed(ADVANCED_SPEED);
				}
				myColor = Color.GREEN;
			}
		}
	}

	public void moveUp(float deltaTime) {
		this.y += this.speed * deltaTime;
	}

	public void moveDown(float deltaTime) {
		this.y -= this.speed * deltaTime;
	}

	public Queue<Bonus> getBonus() {
		return bonus;
	}
}
