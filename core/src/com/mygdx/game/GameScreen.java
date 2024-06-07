package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private final FlappyBirdGame game;
    private final OrthographicCamera camera;
    private MovingBackground background;
    private SpriteBatch batch;

    public GameScreen(FlappyBirdGame game) {
        this.game = game;
        batch = game.getBatch();
        camera = game.getCamera();
    }

    @Override
    public void show() {
        background = new MovingBackground("background/game_bg.png", -2);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        background.draw(batch);
        batch.end();

        if (Gdx.input.justTouched()) {
            System.out.printf("(%d, %d)", Gdx.input.getX(), Gdx.input.getY());
        }
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
    }
}
