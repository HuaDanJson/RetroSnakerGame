package com.example.zhangjinming.androidgame8.bean;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

public class Food {

    private int x;
    private int y;
    private Random random;
    public static final int WIDTH=10;

    public Food(){
        this.x = x;
        this.y = y;
        random=new Random();
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

    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.RED);
        canvas.drawRect(x,y,x+WIDTH,y+WIDTH,paint);
    }
    public void reset(){
        x=random.nextInt(75)*WIDTH;
        y=random.nextInt(128)*WIDTH;
    }
}
