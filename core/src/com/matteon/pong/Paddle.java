package com.matteon.pong;

import com.badlogic.gdx.math.Rectangle;

public class Paddle  extends Rectangle{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 10;
	public static final int HEIGHT = 45;
	public int speed;
	private Integer points;

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoint(){points++;}
    public Paddle(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(Paddle.WIDTH);
        this.setHeight(Paddle.HEIGHT);
        points = 0;
        speed = 200;
    }

    public void moveUp(float deltaTime){
        this.y += this.speed*deltaTime;
    }
    public void moveDown(float deltaTime){
        this.y -= this.speed*deltaTime;
    }
}
