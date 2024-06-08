package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.RestartScreen;
import com.mygdx.game.screens.TwoButtonScreen;

public class FlappyBirdGame extends Game {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    GameScreen gameScreen;
    TwoButtonScreen mainMenuScreen;
    RestartScreen restartScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        gameScreen = new GameScreen(this);
        mainMenuScreen = new TwoButtonScreen(this, "PLAY", () -> setScreen(gameScreen), "EXIT", Gdx.app::exit);
        restartScreen = new RestartScreen(this, "RESTART", () -> setScreen(gameScreen), "MAIN MENU", () -> setScreen(mainMenuScreen), gameScreen);

        setScreen(mainMenuScreen);
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void onLose() {
        setScreen(restartScreen);
    }
}
