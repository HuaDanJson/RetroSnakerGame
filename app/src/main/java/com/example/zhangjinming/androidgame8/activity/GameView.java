package com.example.zhangjinming.androidgame8.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.zhangjinming.androidgame8.bean.Block;
import com.example.zhangjinming.androidgame8.bean.DBScoreBean;
import com.example.zhangjinming.androidgame8.bean.Food;
import com.example.zhangjinming.androidgame8.utils.DBScoreBeanDaoUtils;

import java.util.ArrayList;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static SurfaceHolder holder;
    private static GestureDetector gestureDetector;
    private float scaleX;
    private float scaleY;
    private static Food food;
    private static Paint paint;
    private boolean isRun;
    private ArrayList<Block> blocks;
    private boolean gameOver;
    private Block head;
    private EasyGameViewListener mListener;
    private int EASY_TYPE = 20000;
    private int MIDDLE_TYPE = 10000;
    private int HARD_TYPE = 5000;
    private int CURRENT_TYPE = EASY_TYPE;
    private int mBlockCurrentColor;
    private DBScoreBean mDBScoreBean;
    private Thread mThread;

    public void setEasyGameViewListener(EasyGameViewListener listener) {
        this.mListener = listener;
    }

    public GameView(Context context, SurfaceHolder holder) {
        super(context);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void reStartGame() {
        if (gameOver) {
            init();
            isRun = true;
            mThread = new Thread(this);
            mThread.start();
        }
    }

    public void init(Context context) {
        mDBScoreBean = DBScoreBeanDaoUtils.getInstance().queryOneData(1);
        if (mDBScoreBean != null) {
            mBlockCurrentColor = mDBScoreBean.getType();
        }
        holder = getHolder();
        holder.addCallback(this);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blocks = new ArrayList<>();
        food = new Food();
        Point screenPoint = new Point();
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                windowManager.getDefaultDisplay().getRealSize(screenPoint);
            } else {
                windowManager.getDefaultDisplay().getSize(screenPoint);
            }
        }
        scaleX = (float) screenPoint.x / 1080;
        scaleY = (float) screenPoint.y / 1920;
        init();
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
//                        if (gameOver) {
//                            init();
//                        }
                        return super.onDoubleTap(e);
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2,
                                           float velocityX, float velocityY) {
                        float x = e1.getX() - e2.getX();
                        float y = e1.getY() - e2.getY();
                        if (Math.abs(x) > Math.abs(y)) {
                            if (x > 0) {
                                if (head.getDir() != Block.RIGHT) {
                                    head.setDir(Block.LEFT);
                                }
                            } else {
                                if (head.getDir() != Block.LEFT) {
                                    head.setDir(Block.RIGHT);
                                }

                            }

                        } else {
                            if (y > 0) {
                                if (head.getDir() != Block.DOWN) {
                                    head.setDir(Block.UP);
                                }
                            } else {
                                if (head.getDir() != Block.UP) {
                                    head.setDir(Block.DOWN);
                                }
                            }
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });
    }

    private void init() {
        gameOver = false;
        blocks.clear();
        //控制初始蛇长度
        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(300 + Block.WIDTH * i, 100, Block.LEFT, mBlockCurrentColor));
        }
        head = blocks.get(0);
        setFood();
    }

    private void setFood() {
        food.reset();
        for (Block block : blocks) {
            if (block.getX() == food.getX() && block.getY() == food.getY()) {
                setFood();
                break;
            }
        }
//        food.setX(head.getX()-20);
//        food.setY(head.getX());
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        isRun = true;
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        isRun = false;
    }

    public void setType(int type, int blockColor) {
        mBlockCurrentColor = blockColor;
        switch (type) {
            case 1:
                CURRENT_TYPE = EASY_TYPE;
                break;
            case 2:
                CURRENT_TYPE = MIDDLE_TYPE;
                break;
            case 3:
                CURRENT_TYPE = HARD_TYPE;
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        while (isRun) {
            long startTime = System.currentTimeMillis();
            logic();
            redraw();
            long endTime = System.currentTimeMillis();
            long diffTime = endTime - startTime;
            if (diffTime < 1000 / 60) {
                try {
                    Thread.sleep(CURRENT_TYPE / 60 - diffTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void logic() {
        if (!gameOver) {
            for (Block block : blocks) {
                block.logic();
            }
            for (int i = blocks.size() - 1; i > 0; i--) {
                blocks.get(i).setDir(blocks.get(i - 1).getDir());
            }
            eatFood();
            eatSelf();
            outBounds();
        }
    }

    private void eatSelf() {
        for (int i = 4; i < blocks.size(); i++) {
            if (blocks.get(i).getX() == head.getX() && blocks.get(i).getY() == head.getY()) {
                gameOver = true;
                break;

            }
        }
    }

    private void eatFood() {
        if (head.getX() == food.getX() && head.getY() == food.getY()) {
            Block end = blocks.get(blocks.size() - 1);
            switch (end.getDir()) {
                case Block.LEFT:
                    blocks.add(new Block(end.getX() + Block.WIDTH, end.getY(), end.getDir(), mBlockCurrentColor));
                    adDBScore();
                    break;

                case Block.RIGHT:
                    blocks.add(new Block(end.getX() - Block.WIDTH, end.getY(), end.getDir(), mBlockCurrentColor));
                    adDBScore();
                    break;

                case Block.UP:
                    blocks.add(new Block(end.getX(), end.getY() + Block.WIDTH, end.getDir(), mBlockCurrentColor));
                    adDBScore();
                    break;

                case Block.DOWN:
                    blocks.add(new Block(end.getX(), end.getY() - Block.WIDTH, end.getDir(), mBlockCurrentColor));
                    adDBScore();
                    break;
                default:
                    break;
            }
            setFood();
        }
    }

    public void adDBScore() {
        if (mListener != null) {
            mListener.easyGameViewEatFood();
        }
    }


    private void outBounds() {
        if (head.getX() < 0 || head.getX() + Block.WIDTH > 1080
                || head.getY() < 0 || head.getY() + Block.WIDTH > 1920) {
            gameOver = true;
        }
    }

    private void redraw() {
        Canvas lockCanvas = holder.lockCanvas();
        try {
            synchronized (holder) {
                myDraw(lockCanvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lockCanvas != null) {
                holder.unlockCanvasAndPost(lockCanvas);
            }
        }

    }

    private void myDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(scaleX, scaleY);
        canvas.drawColor(Color.WHITE);
        paint.reset();//重置画笔
        for (Block block : blocks) {
            block.draw(canvas, paint);
        }
        food.draw(canvas, paint);
        if (gameOver) {
            paint.setTextAlign(Paint.Align.CENTER);
            paint.setTextSize(36);
            paint.setColor(Color.BLACK);
            if (mListener != null) {
                isRun = false;
                mListener.gameOver();
            }
//            canvas.drawText("游戏结束，请双击屏幕重新开始", 540, 1054, paint);
        }
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        performClick();
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public interface EasyGameViewListener {

        void easyGameViewEatFood();

        void gameOver();
    }
}
