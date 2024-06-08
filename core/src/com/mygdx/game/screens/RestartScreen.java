package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.mygdx.game.FlappyBirdGame;

public class RestartScreen extends TwoButtonScreen {
    private int score;
    private BitmapFont font;
    private int textX, textY;
    private GameScreen gameScreen;
    public RestartScreen(FlappyBirdGame game, String playText, Runnable onClickPlay, String exitText, Runnable onClickExit, GameScreen screen) {
        super(game, playText, onClickPlay, exitText, onClickExit);
        gameScreen = screen;
    }

    @Override
    public void show() {
        super.show();

        font = new BitmapFont();
        font.getData().scale(7);

        GlyphLayout gl = new GlyphLayout(font, "Score: " + score);
        textX = (Gdx.graphics.getWidth() - (int) gl.width) / 2;
        textY = playButtonY + playButtonHeight + distanceBetweenButtons + (int) gl.height;

        score = gameScreen.getPoints();
    }

    @Override
    protected void draw() {
        super.draw();
        font.draw(batch, "Score: " + score, textX, textY);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}
