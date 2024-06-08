package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FlappyBirdGame;
import com.mygdx.game.components.Bird;
import com.mygdx.game.components.ColumnConstants;
import com.mygdx.game.components.ColumnRow;
import com.mygdx.game.components.MovingBackground;

public class GameScreen implements Screen {
    private final FlappyBirdGame game;
    private final OrthographicCamera camera;
    private MovingBackground background;
    private SpriteBatch batch;
    private ColumnRow columnRow;
    private Bird bird;
    private int points = 0;
    private BitmapFont scoreText;
    private GlyphLayout gl;
    private int textX, textY;

    public GameScreen(FlappyBirdGame game) {
        this.game = game;
        batch = game.getBatch();
        camera = game.getCamera();
    }

    @Override
    public void show() {
        background = new MovingBackground("background/game_bg.png", ColumnConstants.BACKGROUND_SPEED);
        columnRow = new ColumnRow(ColumnConstants.COLUMNS_SPEED, ColumnConstants.DISTANCE_BETWEEN_COLUMNS, ColumnConstants.FIRST_COLUMN_X);
        bird = new Bird();
        points = 0;

        scoreText = new BitmapFont();
        scoreText.getData().scale(7);
        updateTextX();
        textY = Gdx.graphics.getHeight() - (int) gl.height / 2;
    }

    private void updateTextX() {
        gl = new GlyphLayout(scoreText, "Score: " + points);
        textX = ((Gdx.graphics.getWidth() - (int) gl.width) / 2);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        if (bird.isAbroad())
            game.onLose();
        if (columnRow.isCollapse(bird.getX(), bird.getY(), bird.getWidth(), bird.getHeight()))
            game.onLose();

        if (columnRow.isPassedColumn())
            points++;
        if (isOverTen(points))
            updateTextX();

        batch.begin();
        background.draw(batch);
        columnRow.draw(batch);
        bird.draw(batch);
        scoreText.draw(batch, "Score: " + points, textX, textY);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        background.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        columnRow.dispose();
        bird.dispose();
        scoreText.dispose();
    }

    private boolean isOverTen(int n) {
        if (n == 0) return true;
        return (n % 10 == 0 && isOverTen(n / 10));
    }

    public int getPoints() {
        return points;
    }
}
