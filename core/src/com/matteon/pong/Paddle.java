package com.matteon.pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Paddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 45;
	public static final int DEFAULT_SPEED = 200;
	public Color myColor;
	private int speed;
	private Integer points;
	private Integer score;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
		if (this.score < 0) this.setScore(0);
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
		score = 0;
	}

	public void moveUp(float deltaTime) {
		this.y += this.speed * deltaTime;
	}

	public void moveDown(float deltaTime) {
		this.y -= this.speed * deltaTime;
	}
}
