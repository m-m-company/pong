package com.matteon.pong.managers.bonus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.matteon.pong.Ball;
import com.matteon.pong.Paddle;

import java.util.Random;

public abstract class Bonus extends Rectangle {
    private static int MIN_X_SPAWN = 80;
    private static int MAX_X_SPAWN = 720;
    private static int MIN_Y_SPAWN = 20;
    private static int MAX_Y_SPAWN = 580;
    protected Texture texture;
    protected Paddle affectedPaddle;
    protected boolean isActive;
    protected boolean used;

    public boolean isUsed() {
        return used;
    }

    public boolean isActive() {
        return isActive;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public Bonus(String path) {
        super(new Random().nextInt((Bonus.MAX_X_SPAWN - Bonus.MIN_X_SPAWN) + 1) + Bonus.MIN_X_SPAWN,
                new Random().nextInt(MAX_Y_SPAWN - MIN_Y_SPAWN + 1) + MIN_Y_SPAWN, 20, 20);
        this.texture = new Texture(Gdx.files.internal(path));
    }

    public abstract void activate(boolean whosLast, Paddle player1, Paddle player2);

    public boolean isHitted(Ball b) {
        return b.intersects(this.x, this.y, this.width, this.height);
    }

    public abstract void deactivate();
}
