package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TwoButtonScreen implements Screen {
	private FlappyBirdGame game;
	private SpriteBatch batch;
	private Button playButton;
	private Button exitButton;
	private Background background;
	private int distanceBetweenButtons;
	private OrthographicCamera camera;
	private String playText;
	private Runnable onClickPlay;
	private String exitText;
	private Runnable onClickExit;

	public TwoButtonScreen(FlappyBirdGame game, String playText, Runnable onClickPlay, String exitText, Runnable onClickExit) {
		this.playText = playText;
		this.onClickPlay = onClickPlay;
		this.exitText = exitText;
		this.onClickExit = onClickExit;

		this.game = game;
		camera = game.getCamera();
		batch = game.getBatch();

		background = new Background("background/restart_bg.png", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		distanceBetweenButtons = 100;
		initPlayButton();
		initExitButton();
	}

	private Texture playButtonBg;
	private int playButtonWidth;
	private int playButtonX;
	private int playButtonY;

	private void initPlayButton() {
		playButtonBg = new Texture("button/button_bg.png");
		playButtonWidth = playButtonBg.getWidth();
		playButtonX = (Gdx.graphics.getWidth() - playButtonWidth) / 2;
		playButtonY = (Gdx.graphics.getHeight() + distanceBetweenButtons) / 2;
		playButton = new Button(playText, playButtonX, playButtonY, playButtonBg, onClickPlay);
	}

	private Texture exitButtonBg;
	private int exitButtonWidth;
	int exitButtonHeight;
	int exitButtonX;
	int exitButtonY;

	private void initExitButton() {
		exitButtonBg = new Texture("button/exit_button_bg.png");
		exitButtonWidth = exitButtonBg.getWidth();
		exitButtonHeight = exitButtonBg.getHeight();
		exitButtonX = (Gdx.graphics.getWidth() - exitButtonWidth) / 2;
		exitButtonY = (Gdx.graphics.getHeight() - distanceBetweenButtons) / 2 - exitButtonHeight;
		exitButton = new Button(exitText, exitButtonX, exitButtonY, exitButtonBg, onClickExit);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		background.draw(batch);
		playButton.draw(batch);
		exitButton.draw(batch);

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		playButton.dispose();
		background.dispose();
	}

	@Override
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
		background.update(width, height);

		playButtonX = (Gdx.graphics.getWidth() - playButtonWidth) / 2;
		playButtonY = (Gdx.graphics.getHeight() + distanceBetweenButtons) / 2;
		playButton.update(playButtonX, playButtonY);

		exitButtonX = (Gdx.graphics.getWidth() - exitButtonWidth) / 2;
		exitButtonY = (Gdx.graphics.getHeight() - distanceBetweenButtons) / 2 - exitButtonHeight;
		exitButton.update(exitButtonX, exitButtonY);
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
}
