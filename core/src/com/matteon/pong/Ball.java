package com.matteon.pong;

import com.badlogic.gdx.math.Vector2;
import javafx.scene.shape.Circle;

public class Ball {
    float dx, dy;
    private Circle ball;

    public Circle getBall() {
        return ball;
    }

    public void setBall(Circle ball) {
        this.ball = ball;
    }

    public Ball(int x, int y, int radius) {
        ball = new Circle(x, y, radius);
        dx = 3;
        dy = 3;
    }

    public void update(Bar player, Bar second) {
        if (getBall().intersects(player.getRect().x, player.getRect().y - player.getRect().getHeight(),
                player.getRect().width, 0.1)) {
            Vector2 v = new Vector2();
            if (player.getRect().getCenter(v).x >= ball.getCenterX()) {
                dx = 3;
            }
            dy *= -1;
            ball.setCenterY(ball.getCenterY() - player.getRect().getHeight());

        }
        if (getBall().intersects(second.getRect().x, second.getRect().y + second.getRect().getHeight(),
                second.getRect().width, 0.1)) {
            Vector2 v = new Vector2();
            if (second.getRect().getCenter(v).x >= ball.getCenterX()) {
                System.out.println("intersect");
                dx = -3;
            }
            dy *= -1;
            ball.setCenterY(ball.getCenterY() + second.getRect().getHeight());
        }
        if (getBall().getCenterX() + getBall().getRadius() >= 800 || getBall().getCenterX() - getBall().getRadius() <= 0) {
            dx *= -1;
        }

        ball.setCenterX(ball.getCenterX() + dx);
        ball.setCenterY(ball.getCenterY() + dy);
    }
}
