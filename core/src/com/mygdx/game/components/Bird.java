package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Bird {
    private double x, y;
    private Texture[] frames;
    private int currentFrame;
    private int restFrames;
    private int restFramesUp;
    private double a;
    private double speed;
    private boolean isPressed;

    public Bird() {
        this.x = ColumnConstants.BIRD_X;
        y = (double) Gdx.graphics.getHeight() / 2;
        frames = new Texture[4];
        frames[0] = new Texture("bird/bird0.png");
        frames[1] = new Texture("bird/bird1.png");
        frames[2] = new Texture("bird/bird2.png");
        frames[3] = frames[1];
        currentFrame = 0;
        restFrames = ColumnConstants.FRAMES_TO_MOVE_WINGS;

        a = -ColumnConstants.GRAVITY;
        isPressed = false;
    }

    public void draw(Batch batch) {
        batch.draw(frames[currentFrame], (int) x, (int) y);
        restFrames--;
        if (restFrames == 0) {
            currentFrame++;
            restFrames = ColumnConstants.FRAMES_TO_MOVE_WINGS;
            if (currentFrame == 4)
                currentFrame = 0;
        }

        if (Gdx.input.justTouched() && !isPressed) {
            isPressed = true;
            restFramesUp = ColumnConstants.FRAMES_FLYING_UP;
        }
        if (restFramesUp > 0)
            a = ColumnConstants.IMPULSE_WHEN_TOUCHED;
        restFramesUp--;
        if (restFramesUp == 0) {
            isPressed = false;
            a = -ColumnConstants.GRAVITY;
        }

        speed += a;
        y += speed;
    }

    public void dispose() {
        for (Texture frame : frames)
            frame.dispose();
    }
}
