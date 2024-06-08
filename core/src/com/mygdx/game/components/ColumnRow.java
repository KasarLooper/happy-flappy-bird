package com.mygdx.game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ColumnRow {
    private int speed;
    private int distance;
    private int width;
    private int dx;
    private int curX;
    private Column[] columns;

    public ColumnRow(int speed, int distance, int firstX) {
        this.speed = speed;
        this.distance = distance;
        this.curX = firstX;
        width = new Texture("tube/tube.png").getWidth();
        dx = distance + width;

        int n = Gdx.graphics.getWidth() / dx + 2;
        columns = new Column[n];
        for (int i = 0; i < n; i++) {
            columns[i] = new Column(curX);
            curX += dx;
        }
        curX -= dx;
    }

    public void draw(Batch batch) {
        for (Column col : columns) {
            col.draw(batch);
            col.move(speed);
            if (col.isOutScreen()) {
                curX += dx;
                col.setX(curX);
            }
        }
        curX -= speed;
    }

    public void dispose() {
        for (Column col : columns)
            col.dispose();
    }
}
