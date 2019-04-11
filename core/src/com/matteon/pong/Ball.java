package com.matteon.pong;

import com.badlogic.gdx.math.Vector2;
import javafx.scene.shape.Circle;

public class Ball extends Circle{
    float dx, dy;

    public Ball(int x, int y, int radius) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(radius);
        dx = -3;
        dy = -3;
    }

    public void update(Bar player, Bar second) {
        if (this.intersects(player.getX(), player.getY(), player.getWidth(), player.getHeight())) 
        {
        	System.out.println("ciao");
            Vector2 v = new Vector2();
            if (player.getCenter(v).x >= this.getCenterX()) {
            	dy = 3;
            }
            dx *= -1;
            this.setCenterX(this.getCenterX() + player.getWidth());
        }
        if (this.intersects(second.getX(), second.getY(), second.getWidth(), second.getHeight())) 
        {
            Vector2 v = new Vector2();
            if (second.getCenter(v).x >= this.getCenterX()) {
                System.out.println("intersect");
                dy = -3;
            }
            dx *= -1;
            this.setCenterX(this.getCenterX() - player.getWidth());
        }
        if (this.getCenterY() + this.getRadius() >= 600 || this.getCenterY() - this.getRadius() <= 0) {
            dy *= -1;
        }

        this.setCenterX(this.getCenterX() + dx);
        this.setCenterY(this.getCenterY() + dy);
    }
}
