package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.FlappyBirdGame;
import com.mygdx.game.components.Column;
import com.mygdx.game.components.ColumnConstants;
import com.mygdx.game.components.ColumnRow;
import com.mygdx.game.components.MovingBackground;

public class GameScreen implements Screen {

    private final FlappyBirdGame game;
    private final OrthographicCamera camera;
    private MovingBackground background;
    private SpriteBatch batch;
    private ColumnRow columnRow;

    public GameScreen(FlappyBirdGame game) {
        this.game = game;
        batch = game.getBatch();
        camera = game.getCamera();
    }

    @Override
    public void show() {
        background = new MovingBackground("background/game_bg.png", ColumnConstants.BACKGROUND_SPEED);
        columnRow = new ColumnRow(ColumnConstants.COLUMNS_SPEED, ColumnConstants.DISTANCE_BETWEEN_COLUMNS, ColumnConstants.FIRST_COLUMN_X);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        background.draw(batch);
        columnRow.draw(batch);
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
    }
}
