package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Background {
    Texture texture;
    int screenWidth, screenHeight;

    public Background(String path, int screenWidth, int screenHeight) {
        texture = new Texture(path);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void draw(Batch batch) {
        batch.draw(texture, 0, 0, screenWidth, screenHeight);
    }

    public void dispose() {
        texture.dispose();
    }
}
