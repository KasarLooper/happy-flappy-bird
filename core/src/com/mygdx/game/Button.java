package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Button {
    private Texture bg;
    private BitmapFont font;
    private int x, y;
    private int textX, textY;
    private int writeX, upY;
    private String text;

    public Button(String text, int x, int y, Texture bg) {
        this.x = x;
        this.y = y;
        int width = bg.getWidth();
        int height = bg.getHeight();
        writeX = x + width;
        upY = y + height;
        this.bg = bg;

        this.text = text;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().scale(5);
        GlyphLayout layout = new GlyphLayout(font, text);
        int textWidth = (int) layout.width;
        int textHeight = (int) layout.height;
        textX = x + (width - textWidth) / 2;
        textY = upY - (height - textHeight) / 2;
    }

    public void draw(Batch batch) {
        batch.draw(bg, x, y);
        font.draw(batch, text, textX, textY);
    }

    public void dispose() {
        bg.dispose();
        font.dispose();
    }
}
