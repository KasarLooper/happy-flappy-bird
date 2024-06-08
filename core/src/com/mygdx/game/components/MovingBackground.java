package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class MovingBackground {
    private int speed;
    private Texture texture1;
    private Texture texture2;
    private int x1, x2;

    public MovingBackground(String path, int speed) {
        texture1 = new Texture(path);
        texture2 = new Texture(path);
        this.speed = speed;

        x1 = 0;
        x2 = Gdx.graphics.getWidth();
    }

    public void update(int width, int height) {
    }

    public void draw(Batch batch) {
        if (x1 <= -Gdx.graphics.getWidth())
            x1 = Gdx.graphics.getWidth();
        if (x2 <= -Gdx.graphics.getWidth())
            x2 = Gdx.graphics.getWidth();

        batch.draw(texture1, x1, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(texture2, x2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        x1 -= speed;
        x2 -= speed;
    }

    public void dispose() {
        texture1.dispose();
        texture2.dispose();
    }
}
