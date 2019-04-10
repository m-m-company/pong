package com.matteon.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Pong extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    OrthographicCamera camera;
    Bar player;
    Bar second;
    ShapeRenderer sh;
    Ball ball;
    BitmapFont pointsPlayer;
    BitmapFont pointsSecond;
    ArrayList<Rectangle> obstacles;

    @Override
    public void create() {
        pointsPlayer = new BitmapFont();
        pointsSecond = new BitmapFont();
        System.out.println(Gdx.graphics.getWidth());
        System.out.println(Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        obstacles = new ArrayList<Rectangle>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        sh = new ShapeRenderer();
        sh.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        for (int i = 10; i < 800; i += 40) {
            obstacles.add(new Rectangle(i, 600 / 2, 20, 20));
        }
        player = new Bar(380, 580);
        second = new Bar(380, 20);
        ball = new Ball(800 / 2, 600 / 2, 15);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.moveLeft(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.moveRight(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            second.moveLeft(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            second.moveRight(Gdx.graphics.getDeltaTime());
        ball.update(player, second);
        if(ball.getBall().getCenterY() <0) {
            player.addPoint();
            ball.getBall().setCenterY(800/2);
        }
        if(ball.getBall().getCenterY() >600) {
            ball.getBall().setCenterY(800/2);
            second.addPoint();
        }

        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(Color.WHITE);
        sh.rect(player.getRect().x, player.getRect().y, player.getRect().width, player.getRect().height);
        sh.rect(second.getRect().x, second.getRect().y, second.getRect().width, second.getRect().height);
        for (Rectangle r : obstacles)
            sh.rect(r.x, r.y, r.width, r.height);
        sh.setColor(Color.YELLOW);
        sh.circle((float) ball.getBall().getCenterX(), (float) ball.getBall().getCenterY(),
                (float) ball.getBall().getRadius());
        sh.end();

        batch.begin();
        pointsPlayer.setColor(Color.WHITE);
        pointsSecond.setColor(Color.WHITE);
        pointsSecond.draw(batch, player.getPoints().toString(), 800-50, 300+50);
        pointsPlayer.draw(batch, second.getPoints().toString(), 800-50, 300-50);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
