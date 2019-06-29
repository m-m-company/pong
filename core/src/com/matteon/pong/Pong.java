package com.matteon.pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.matteon.pong.managers.bonus.BonusManager;
import com.matteon.pong.managers.graphic.GraphicManager;
import com.matteon.pong.managers.sound.SoundManager;

public class Pong extends ApplicationAdapter {
	private GraphicManager graphicManager;
	private SoundManager soundManager;
	private BonusManager bonusManager;
	private float delay;
	private Paddle player;
	private Paddle second;
	private Ball ball;
	private Boolean multiplayer;
	boolean exitFromMenu = false;

	@Override
	public void create() {
		soundManager = new SoundManager();
		graphicManager = new GraphicManager();
		bonusManager = new BonusManager();
		delay = 0;
		player = new Paddle(Paddle.WIDTH, GraphicManager.HEIGHT / 2);
		second = new Paddle(GraphicManager.WIDTH - Paddle.WIDTH * 2, GraphicManager.HEIGHT / 2);
		ball = new Ball(GraphicManager.WIDTH / 2, GraphicManager.HEIGHT / 2, 15);
		multiplayer = false;
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!exitFromMenu) {
			this.menuHandler();
		} else {
			delay += Gdx.graphics.getDeltaTime();
			if (delay > 10) {
				bonusManager.spawnBonus();
				delay = 0;
			}
			bonusManager.check(ball, player, second, ball.whoHittedMe);
			if (Gdx.input.isKeyJustPressed(Input.Keys.Y))
				player.addPoint();
			
			if (multiplayer) {
				if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getY() < GraphicManager.HEIGHT - player.getHeight())
					player.moveUp(Gdx.graphics.getDeltaTime());
				if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getY() > 0)
					player.moveDown(Gdx.graphics.getDeltaTime());
				if (Gdx.input.isKeyPressed(Input.Keys.UP) && second.getY() < GraphicManager.HEIGHT - second.getHeight())
					second.moveUp(Gdx.graphics.getDeltaTime());
				if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && second.getY() > 0)
					second.moveDown(Gdx.graphics.getDeltaTime());
			} else {
				if (Gdx.input.isKeyPressed(Input.Keys.UP) && player.getY() < GraphicManager.HEIGHT - player.getHeight())
					player.moveUp(Gdx.graphics.getDeltaTime());
				if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && player.getY() > 0)
					player.moveDown(Gdx.graphics.getDeltaTime());
				this.playAsSinglePlayer();
			}
			player.updateStats();
			second.updateStats();
			if (player.getPoints() > 9 || second.getPoints() > 9) {
				System.out.println(player.getScore());
				this.create();
				exitFromMenu = !exitFromMenu;
			}
			this.ballHandler();
			graphicManager.drawBonus(bonusManager.getBonus());
			graphicManager.drawBall(ball);
			graphicManager.drawPaddle(player);
			graphicManager.drawPaddle(second);
			graphicManager.drawPoints(player.getPoints(), second.getPoints());
			graphicManager.drawMidfield();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
			Gdx.app.exit();
	}

	private void ballHandler() {
		if (ball.update(player, second))
			soundManager.playHit();

		if (ball.getCenterX() < 0) {
			second.addPoint();
			second.setScore(second.getScore() + 100);
			player.setScore(player.getScore() - 100);
			ball.setCenterX(400);
			ball.setCenterY(300);
			soundManager.playGoal();
		}
		if (ball.getCenterX() > GraphicManager.WIDTH) {
			player.addPoint();
			player.setScore(player.getScore() + 100);
			second.setScore(second.getScore() - 100);
			ball.setCenterX(400);
			ball.setCenterY(300);
			soundManager.playGoal();
		}
	}

	private void menuHandler() {
		graphicManager.drawMenu(multiplayer);
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			multiplayer = !multiplayer;
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
			exitFromMenu = true;
	}

	private void playAsSinglePlayer() {
		int difficulty = 0;
		int difference = Math.abs(player.getPoints() - second.getPoints());

		if (difference >= 3 && difference <= 5) {
			second.setCurrentDefaultSpeed(Paddle.DEFAULT_SPEED);
			player.setCurrentDefaultSpeed(Paddle.DEFAULT_SPEED);
			difficulty = 2;
		} else if (difference > 5) {
			difficulty = 4;
			second.setCurrentDefaultSpeed(Paddle.ADVANCED_SPEED);
			player.setCurrentDefaultSpeed(Paddle.ADVANCED_SPEED);
			ball.setSpeedMode(1);
		} else {
			second.setCurrentDefaultSpeed(second.getSpeed());
			player.setCurrentDefaultSpeed(player.getSpeed());
			ball.setSpeedMode(0);
		}

		int direction = simpleAI(second.getX(), second.getY(), ball.getCenterX(), ball.getCenterY(), difficulty);
		if (direction == 1 && second.getY() < GraphicManager.HEIGHT - second.getHeight())
			second.moveUp(Gdx.graphics.getDeltaTime());
		if (direction == 2 && second.getY() > 0)
			second.moveDown(Gdx.graphics.getDeltaTime());
	}

	private int simpleAI(double myX, double myY, double ballX, double ballY, int difficulty) {
		if (myX - ballX > 350 + difficulty * 70) {
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
