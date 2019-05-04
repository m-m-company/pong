package com.matteon.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.matteon.pong.managers.graphic.*;
import com.matteon.pong.managers.sound.SoundManager;

public class Pong extends ApplicationAdapter {
	GrapichManager graphic;
	SoundManager sound;
	Paddle player;
	Paddle second;
	Ball ball;
	boolean multiplayer;
	int up;

	@Override
	public void create() {
		sound = new SoundManager();
		graphic = new GrapichManager();
		player = new Paddle(Paddle.WIDTH, 300);
		second = new Paddle(800 - Paddle.WIDTH * 2, 300);
		ball = new Ball(800 / 2, 600 / 2, 15);
		multiplayer = false;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getY() < 555)
			player.moveUp(Gdx.graphics.getDeltaTime());
		if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getY() > 0)
			player.moveDown(Gdx.graphics.getDeltaTime());
		if (multiplayer) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP) && second.getY() < 555)
				second.moveUp(Gdx.graphics.getDeltaTime());
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && second.getY() > 0)
				second.moveDown(Gdx.graphics.getDeltaTime());
		} else {
			int difficulty = 0;
			int difference = player.getPoints() - second.getPoints();
			second.speed = 200;
			player.speed = 200;
			ball.setSpeedMode(0);
			if(difference >= 3 && difference <= 5) {
				difficulty = 2;
				System.out.println(difference + " " + difficulty + " " + ball.dx + " " + ball.dy);
			}
			else if(difference >5) {
				difficulty = 4;
				second.speed*=2;
				player.speed*=2;
				ball.setSpeedMode(1);
				System.out.println(difference + " " + difficulty + " " + ball.dx + " " + ball.dy);
			}
			System.out.println(difference + " " + difficulty + " " + ball.dx + " " + ball.dy);
			up = inteligent(second.getX(), second.getY(), ball.getCenterX(), ball.getCenterY(),difficulty);
			if (up == 1 && second.getY() < 555)
				second.moveUp(Gdx.graphics.getDeltaTime());
			if (up == 2 && second.getY() > 0)
				second.moveDown(Gdx.graphics.getDeltaTime());

		}
		if (ball.update(player, second))
			sound.playHit();

		if (ball.getCenterX() < 0) {
			second.addPoint();
			ball.setCenterX(400);
			ball.setCenterY(300);
			sound.playGoal();
		}
		if (ball.getCenterX() > 800) {
			player.addPoint();
			ball.setCenterX(400);
			ball.setCenterY(300);
			sound.playGoal();
		}

		graphic.drawBall(ball);
		graphic.drawPaddle(player);
		graphic.drawPaddle(second);
		graphic.drawPoints(player.getPoints(), second.getPoints());
		graphic.drawMidfield();
	}

	public int inteligent(double myX, double myY, double ballX, double ballY, int difficulty) {
		if (myX - ballX > 350 + difficulty*70) {
			return 0;
		}
		if (myY < ballY)
			return 1;
		if (myY > ballY)
			return 2;
		return 0;
	}

	@Override
	public void dispose() {
		// batch.dispose();
	}
}
