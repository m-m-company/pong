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
    private boolean multiplayer;

    @Override
    public void create() {
        soundManager = new SoundManager();
        graphicManager = new GraphicManager();
        bonusManager = new BonusManager();
        delay = 0;
        player = new Paddle(Paddle.WIDTH, 300);
        second = new Paddle(800 - Paddle.WIDTH * 2, 300);
        ball = new Ball(800 / 2, 600 / 2, 15);
        multiplayer = false;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        delay += Gdx.graphics.getDeltaTime();
        if (delay > 15) {
            System.out.println("spawn");
            bonusManager.spawnBonus();
            delay = 0;
        }

        bonusManager.check(ball, player, second, ball.whoHittedMe);
        if (Gdx.input.isKeyJustPressed(Input.Keys.Y))
            player.addPoint();
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

            if (difference >= 3 && difference <= 5) {
                difficulty = 2;
            } else if (difference > 5) {
                difficulty = 4;
                second.setSpeed(Paddle.DEFAULT_SPEED * 2);
                player.setSpeed(Paddle.DEFAULT_SPEED * 2);
                ball.setSpeedMode(1);
            } else {
                second.setSpeed(second.getSpeed());
                player.setSpeed(player.getSpeed());
                ball.setSpeedMode(0);
            }

            int direction = simpleAI(second.getX(), second.getY(), ball.getCenterX(), ball.getCenterY(), difficulty);
            if (direction == 1 && second.getY() < 555)
                second.moveUp(Gdx.graphics.getDeltaTime());
            if (direction == 2 && second.getY() > 0)
                second.moveDown(Gdx.graphics.getDeltaTime());
        }

        this.ballHandler();
        graphicManager.drawBonus(bonusManager.getBonus());
        graphicManager.drawBall(ball);
        graphicManager.drawPaddle(player);
        graphicManager.drawPaddle(second);
        graphicManager.drawPoints(player.getPoints(), second.getPoints());
        graphicManager.drawMidfield();
    }

    private void ballHandler() {
        if (ball.update(player, second))
            soundManager.playHit();

        if (ball.getCenterX() < 0) {
            second.addPoint();
            ball.setCenterX(400);
            ball.setCenterY(300);
            soundManager.playGoal();
        }
        if (ball.getCenterX() > 800) {
            player.addPoint();
            ball.setCenterX(400);
            ball.setCenterY(300);
            soundManager.playGoal();
        }
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
