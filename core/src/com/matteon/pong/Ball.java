package com.matteon.pong;

import com.badlogic.gdx.math.Vector2;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
    float dx, dy;
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
        dx = -4;
        dy = -4;
    }

    public boolean update(Paddle player, Paddle second) {
        boolean returnValue = false;
        if (this.intersects(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
            System.out.println("ciao");
            Vector2 v = new Vector2();
            if (player.getCenter(v).x >= this.getCenterX()) {
                dy = 4;
            }
            dx *= -1;
            whoHittedMe = true;
            this.setCenterX(this.getCenterX() + player.getWidth());
            returnValue = true;
        }
        if (this.intersects(second.getX(), second.getY(), second.getWidth(), second.getHeight())) {
            Vector2 v = new Vector2();
            if (second.getCenter(v).x <= this.getCenterX()) {
                dy = -4;
            }
            dx *= -1;
            whoHittedMe = false;
            this.setCenterX(this.getCenterX() - player.getWidth());
            returnValue = true;
        }
        if (this.getCenterY() + this.getRadius() >= 600 || this.getCenterY() - this.getRadius() <= 0) {
            dy *= -1;
            returnValue = false;
        }
        this.setCenterX(this.getCenterX() + dx + dx * 1.5f * speedMode);
        this.setCenterY(this.getCenterY() + dy + dy * 1.5f * speedMode);
        return returnValue;
    }
}
