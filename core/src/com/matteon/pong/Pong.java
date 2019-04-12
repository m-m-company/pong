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
    int up;

    @Override
    public void create() {
        pointsPlayer = new BitmapFont();
        pointsSecond = new BitmapFont();
        //System.out.println(Gdx.graphics.getWidth());
        //System.out.println(Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        obstacles = new ArrayList<Rectangle>();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        sh = new ShapeRenderer();
        sh.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        /*for (int i = 10; i < 800; i += 40) {
            obstacles.add(new Rectangle(i, 600 / 2, 20, 20));
        }*/
        player = new Bar(0, 300);
        second = new Bar(795, 300);
        ball = new Ball(800 / 2, 600 / 2, 15);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getY() < 555)
            player.moveUp(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getY() > 0)
            player.moveDown(Gdx.graphics.getDeltaTime());
        up = inteligent(second.getX(), second.getY(), ball.getCenterX(), ball.getCenterY());
        if(up == 1 && second.getY() < 555)
        	second.moveUp(Gdx.graphics.getDeltaTime());
        if(up == 2 && second.getY() > 0)
        	second.moveDown(Gdx.graphics.getDeltaTime());
        /*if (Gdx.input.isKeyPressed(Input.Keys.UP) && second.getY() < 555)
            second.moveUp(Gdx.graphics.getDeltaTime());
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && second.getY() > 0)
            second.moveDown(Gdx.graphics.getDeltaTime());*/
        ball.update(player, second);
        if(ball.getCenterX() <0) {
            player.addPoint();
            ball.setCenterX(400);
            ball.setCenterY(300);
        }
        if(ball.getCenterX() >800) {
        	second.addPoint();
            ball.setCenterX(400);
            ball.setCenterY(300);
        }

        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(Color.WHITE);
        sh.rect(player.x, player.y, player.width, player.height);
        sh.rect(second.x, second.y, second.width, second.height);
        /*for (Rectangle r : obstacles)
            sh.rect(r.x, r.y, r.width, r.height);*/
        sh.setColor(Color.YELLOW);
        sh.circle((float) ball.getCenterX(), (float) ball.getCenterY(),
                (float) ball.getRadius());
        sh.end();

        batch.begin();
        pointsPlayer.setColor(Color.WHITE);
        pointsSecond.setColor(Color.WHITE);
        pointsSecond.draw(batch, player.getPoints().toString(), 350, 550);
        pointsPlayer.draw(batch, second.getPoints().toString(), 450, 550);
        batch.end();

    }
    
    public int inteligent(double myX, double myY, double ballX, double ballY) {
    	if(myX - ballX > 300) {
    		return 0;
    	}
    	if(myY < ballY)
    		return 1;
    	if(myY > ballY)
    		return 2;
    	return 0;
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
