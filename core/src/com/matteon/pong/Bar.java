package com.matteon.pong;

import com.badlogic.gdx.math.Rectangle;

public class Bar  {
    private Rectangle rect;
    private Integer points;

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoint(){points++;}
    public Bar(int x, int y) {
        rect = new Rectangle(x, y, 40, 5);
        points = 0;
    }

    public void moveRight(float deltaTime){
        this.rect.x += 200*deltaTime;
    }
    public void moveLeft(float deltaTime){
        this.rect.x -= 200*deltaTime;
    }
}
