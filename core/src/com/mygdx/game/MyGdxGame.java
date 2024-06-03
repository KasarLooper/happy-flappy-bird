package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private int screenWidth;
	private int screenHeight;
	private Button playButton;
	private Button exitButton;
	private Background background;
	private int distanceBetweenButtons;
	
	@Override
	public void create () {
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		batch = new SpriteBatch();

		background = new Background("background/restart_bg.png", screenWidth, screenHeight);
		distanceBetweenButtons = 100;
		initPlayButton();
		initExitButton();
	}

	private void initPlayButton() {
		Texture bg = new Texture("button/button_bg.png");
		int width = bg.getWidth();
		int x = (screenWidth - width) / 2;
		int y = (screenHeight + distanceBetweenButtons) / 2;
		playButton = new Button("PLAY", x, y, bg);
	}

	private void initExitButton() {
		Texture bg = new Texture("button/exit_button_bg.png");
		int width = bg.getWidth();
		int height = bg.getHeight();
		int x = (screenWidth - width) / 2;
		int y = (screenHeight - distanceBetweenButtons) / 2 - height;
		exitButton = new Button("EXIT", x, y, bg);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
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
}
