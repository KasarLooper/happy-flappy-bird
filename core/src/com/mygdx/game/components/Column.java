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
    private boolean isPassed;

    public Column(int x) {
        this.x = x;
        up = new Texture("tube/tube_flipped.png");
        down = new Texture("tube/tube.png");

        width = up.getWidth();
        height = up.getHeight();

        int centY = Gdx.graphics.getHeight() / 2;
        upY = centY + ColumnConstants.INTERVAL_HEIGHT / 2;
        downY = upY - ColumnConstants.INTERVAL_HEIGHT - height;
        isPassed = false;

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
        if (isOutScreen())
            isPassed = false;
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

    public boolean isAfterColumn() {
        if (ColumnConstants.BIRD_X >= x + width && !isPassed) {
            isPassed = true;
            return true;
        }
        return false;
    }

    public boolean isCollapse(int x, int y, int width, int height) {
        return x >= this.x && x <= this.x + width && (y + height >= upY || y <= downY + down.getHeight());
    }
}
