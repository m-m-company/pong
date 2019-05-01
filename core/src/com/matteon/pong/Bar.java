package com.matteon.pong;

import com.badlogic.gdx.math.Rectangle;

public class Bar  extends Rectangle{
	
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
    public Bar(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(Bar.WIDTH);
        this.setHeight(Bar.HEIGHT);
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
