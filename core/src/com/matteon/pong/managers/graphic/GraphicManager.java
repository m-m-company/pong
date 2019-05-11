package com.matteon.pong.managers.graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.matteon.pong.Ball;
import com.matteon.pong.Paddle;
import com.matteon.pong.managers.bonus.Bonus;

import java.util.ArrayList;

public class GraphicManager {

    private final ArrayList<Rectangle> midField;
    private final ShapeRenderer sh;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final FreeTypeFontGenerator generator;
    private final FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private final BitmapFont font;

    public GraphicManager() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        sh = new ShapeRenderer();
        sh.setProjectionMatrix(camera.combined);
        batch.setProjectionMatrix(camera.combined);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font.otf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 30;
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        midField = new ArrayList<Rectangle>();
        int y = 0;
        while (y <= 600) {
            midField.add(new Rectangle(400, y, 20, 20));
            y += 30;
        }
    }

    public void drawBonus(Bonus bonus) {
        batch.begin();
        if (bonus != null && !bonus.isUsed())
            batch.draw(bonus.getTexture(), bonus.getX(), bonus.getY());
        batch.end();
    }

    public void drawPaddle(Paddle paddle) {
        sh.begin(ShapeRenderer.ShapeType.Filled);
        sh.setColor(paddle.myColor);
        sh.rect(paddle.x, paddle.y, paddle.width, paddle.height);
        sh.end();
    }

    public void drawBall(Ball ball) {
        sh.begin(ShapeType.Filled);
        sh.setColor(Color.YELLOW);
        sh.circle((float) ball.getCenterX(), (float) ball.getCenterY(), (float) ball.getRadius());
        sh.end();
    }

    public void drawPoints(Integer p1points, Integer p2points) {
        batch.begin();
        font.draw(batch, p1points.toString(), 350, 550);
        font.draw(batch, p2points.toString(), 430, 550);
        batch.end();
    }

    public void drawMidfield() {
        sh.begin(ShapeType.Line);
        sh.setColor(Color.WHITE);
        for (Rectangle r : midField) {
            sh.rect(r.getX(), r.getY(), r.width, r.height);
        }
        sh.end();
    }
}
