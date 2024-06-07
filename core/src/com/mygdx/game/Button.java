package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class Button {
    private Texture bg;
    private BitmapFont font;
    private int x, y;
    private int textWidth, textHeight;
    private int textX, textY;
    private int writeX, upY;
    private String text;
    private Runnable onClick;

    public Button(String text, int x, int y, Texture bg, Runnable onClick) {
        this.text = text;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().scale(5);
        this.bg = bg;

        GlyphLayout layout = new GlyphLayout(font, text);
        textWidth = (int) layout.width;
        textHeight = (int) layout.height;

        update(x, y);

        this.onClick = onClick;
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
        int width = bg.getWidth();
        int height = bg.getHeight();
        writeX = x + width;
        upY = y + height;

        textX = x + (width - textWidth) / 2;
        textY = upY - (height - textHeight) / 2;

        System.out.printf("%s x: %d - %d; y: %d - %d\n", text, x, writeX, y, upY);
    }

    public void draw(Batch batch) {
        batch.draw(bg, x, y);
        font.draw(batch, text, textX, textY);

        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();
            System.out.printf("(%d, %d) was pressed\n", x, y);
            if (x <= writeX && x >= this.x && y <= upY && y >= this.y)
                onClick.run();
        }
    }

    public void dispose() {
        bg.dispose();
        font.dispose();
    }
}
