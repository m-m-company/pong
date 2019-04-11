package com.matteon.pong;

import com.badlogic.gdx.math.Rectangle;

public class Bar  extends Rectangle{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
        this.setWidth(5);
        this.setHeight(45);
        points = 0;
    }

    public void moveUp(float deltaTime){
        this.y += 200*deltaTime;
    }
    public void moveDown(float deltaTime){
        this.y -= 200*deltaTime;
    }
}
