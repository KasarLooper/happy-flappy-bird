package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Column {
    private Texture up;
    private Texture down;
    private int x;
    private int upY, downY;
    private int width, height;

    public Column(int x) {
        this.x = x;
        up = new Texture("tube/tube_flipped.png");
        down = new Texture("tube/tube.png");

        width = up.getWidth();
        height = up.getHeight();

        int centY = Gdx.graphics.getHeight() / 2;
        upY = centY + ColumnConstants.INTERVAL_HEIGHT / 2;
        downY = upY - ColumnConstants.INTERVAL_HEIGHT - height;

        int range = Math.min(downY + height - ColumnConstants.UP_PART_HEIGHT, height - (upY - ColumnConstants.INTERVAL_HEIGHT));
        int randomDef = ((int) (Math.random() * range)) * 2 - range;
        upY -= randomDef;
        downY -= randomDef;
    }

    public void draw(Batch batch) {
        batch.draw(up, x, upY);
        batch.draw(down, x, downY);
    }

    public void move(int speed) {
        x -= speed;
    }

    public boolean isOutScreen() {
        return x <= -width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void dispose() {
        up.dispose();
        down.dispose();
    }
}
