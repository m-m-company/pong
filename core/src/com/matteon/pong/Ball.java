package com.matteon.pong;

import com.badlogic.gdx.math.Vector2;
import com.matteon.pong.managers.graphic.GraphicManager;

import javafx.scene.shape.Circle;

public class Ball extends Circle {
	private static final int DEFAULT_DX = 5;
	private static final int DEFAULT_DY = 5;

	private float dx;
	private float dy;
	public boolean whoHittedMe;

	public int getSpeedMode() {
		return speedMode;
	}

	public void setSpeedMode(int speedMode) {
		this.speedMode = speedMode;
	}

	private int speedMode;

	public Ball(int x, int y, int radius) {
		this.setCenterX(x);
		this.setCenterY(y);
		this.setRadius(radius);
		speedMode = 0;
		dx = -DEFAULT_DX;
		dy = -DEFAULT_DY;
	}

	public boolean update(Paddle player, Paddle second) {
		boolean returnValue = false;
		if (this.intersects(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
			Vector2 v = new Vector2();
			if (player.getCenter(v).y >= this.getCenterY() + DEFAULT_DY) {
				dy = -DEFAULT_DY;
			} else {
				dy = DEFAULT_DY;
			}
			dx = DEFAULT_DX;
			whoHittedMe = true;
			this.setCenterX(player.getWidth() * 2 + this.getRadius());
			returnValue = true;
		}
		if (this.intersects(second.getX(), second.getY(), second.getWidth(), second.getHeight())) {
			Vector2 v = new Vector2();
			if (second.getCenter(v).y <= this.getCenterY()) {
				dy = -DEFAULT_DY;
			} else {
				dy = DEFAULT_DY;
			}
			dx = -DEFAULT_DX;
			whoHittedMe = false;
			this.setCenterX(GraphicManager.WIDTH-(second.getWidth()*2)-this.getRadius());
			returnValue = true;
		}
		if (this.getCenterY() + this.getRadius() >= GraphicManager.HEIGHT || this.getCenterY() - this.getRadius() <= 0) {
			dy *= -1;
			returnValue = false;
		}
		this.setCenterX(this.getCenterX() + dx + dx * 1.5f * speedMode);
		this.setCenterY(this.getCenterY() + dy + dy * 1.5f * speedMode);
		return returnValue;
	}
}
