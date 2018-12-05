package com.example.zhangjinming.androidgame8.bean;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.zhangjinming.androidgame8.R;
import com.example.zhangjinming.androidgame8.utils.ResourceUtil;

public class Block {
    private int x;
    private int y;
    private int dir;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int WIDTH = 10;

    private int mColors[] = {ResourceUtil.getColor(R.color.black),
            ResourceUtil.getColor(R.color.upload_video_add_text_color03),
            ResourceUtil.getColor(R.color.upload_video_add_text_color04),
            ResourceUtil.getColor(R.color.upload_video_add_text_color05),
            ResourceUtil.getColor(R.color.upload_video_add_text_color06),
            ResourceUtil.getColor(R.color.upload_video_add_text_color07)};

    private int currentColor = mColors[0];

    public Block(int x, int y, int dir, int selectColor) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.currentColor = mColors[selectColor];
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getCurrentColor() {
        return currentColor;
    }

    //selectColor  取值：1 2 3 4 5
    public void setCurrentColor(int selectColor) {
        this.currentColor = mColors[selectColor];
    }

    public void logic() {
        switch (dir) {
            case LEFT:
                x -= 10;
                break;
            case RIGHT:
                x += 10;
                break;
            case UP:
                y -= 10;
                break;
            case DOWN:
                y += 10;
                break;
        }
    }


    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(currentColor);
        canvas.drawRect(x, y, x + WIDTH, y + WIDTH, paint);
    }
}
