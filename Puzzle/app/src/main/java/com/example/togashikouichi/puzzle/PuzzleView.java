package com.example.togashikouichi.puzzle;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class PuzzleView extends View {

    private final static int
    S_TITLE = 0,
    S_PLAY = 1,
    S_CLEAR = 2;

    private final static int
    W = 480,
    H = 800;

    private Graphics g;
    private Rect gSrc;
    private Rect gDst;
    private int scene = S_TITLE;
    private Bitmap[] bmp = new Bitmap[5];
    private int[] data = new int[25];
    private int shuffule;

    public PuzzleView(Activity activity) {
        super(activity);

        for (int i = 0; i < 5; i++ ) {
            bmp[1] = readBitmap(activity, "puzzle" + 1);
        }

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        int dh = W*p.y/p.x;

        g = new Graphics(W, dh);
        g.setOrigin(0, (dh - H)/2);
        gSrc = new Rect(0, 0, W, dh);
        gDst = new Rect(0, 0, p.x, p.y);

        initScene(S_TITLE);
    }

    private void initScene(int scene) {
        this.scene = scene;

        if (scene == S_TITLE) {
            for (int i = 0; i < 16; i++)data[i] = i;
        }
        else if (scene == S_PLAY) {
            shuffule = 20;
            while (shuffule > 0) {

                if (movePrice(rand(4), rand(4))) shuffule--;
            }
        }
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        g.drawBitmap(bmp[0], 0, 0);

        int px = (W-400)/2;
        int py = (H-400)/2;
        g.drawBitmap(bmp[2], px - 2, py - 2);
        for (int i = 0; i < 16; i++) {
            int sx = data[i] % 4;
            int sy = data[i] / 4;
            int dx = i % 4;
            int dy = i / 4;
            if (scene != S_PLAY || data[i] != 15) {
                g.drawBitmap(bmp[1],
                        new Rect(100*sx, 100*sy, 100*sx+100, 100*sy+100),
                        new Rect(px+100*dx, py+100*dy, px+100*dx+100, py+100*dy+100));
                g.drawBitmap(bmp[3], px+100*dx, py+100*dy);
            }
        }

        if (scene == S_TITLE || scene == S_CLEAR) {
            String string = (scene == S_TITLE) ? "15 Puzzle":"Clear";
            g.setColor(Color.WHITE);
            g.setTextSize(60);
            g.drawText(string, (W-g.measureText(string))/2, (200+g.getOriginY()-(int)g.getFontMetrics().top)/2);
        }

        if (scene == S_TITLE) {
            g.drawBitmap(bmp[4], 104, 600 + (200-g.getOriginY()-56)/2);
        }

        canvas.drawBitmap(g.getBmp(), gSrc, gDst, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touthX = (int)(event.getX()*W/getWidth());
        int touthY = (int)(event.getY()*H/getHeight());
        int touchAction = event.getAction();
        if (touchAction == MotionEvent.ACTION_DOWN) {
            if (scene == S_TITLE) {
                initScene(S_PLAY);
            }
            else if (scene == S_PLAY) {
                int px = (W-400)/2;
                int py = (H-400)/2;
                if (px < touthX && touthX < px+400 &&
                        py < touthY && touthY < py+400) {
                    int tx = (touthX-px) / 100;
                    int ty = (touthY-py) / 100;
                    movePrice(tx, ty);
                }
            } else if (scene == S_CLEAR) {
                initScene(S_TITLE);
            }
        }
        return true;
    }

    private boolean movePrice (int tx, int ty) {
        int fx = 0;
        int fy = 0;
        for (int i = 0; i < 16; i++) {
            if (data[i] == 15) {
                fx = i%4;
                fy = i/4;
                break;
            }
        }
        if ((fx == tx && fy == ty) || (fx != tx && fy != ty)) {
            return false;
        }

        if (fx == tx && fy < ty) {
            for (int i = fy; i < ty; i++) {
                data[fx+i*4] = data[fx+i*4+4];
            }
            data[tx+ty*4] = 15;
        }
        else if (fx == tx && fx > ty) {
            for (int i = fy; i > ty; i--) {
                data[fx+i*4] = data[fx+i*4-4];
            }
            data[tx+ty*4] = 15;
        } else if (fy == ty && fx < tx) {
            for (int i = fx; i < tx; i++) {
                data[i+fy*4] = data[i+fy*4+1];
            }
            data[tx+ty*4] = 15;
        } else if (fy == ty && fx > tx) {
            for (int i = fx; i > tx; i--) {
                data[i+fy*4] = data[i+fy*4-1];
            }
            data[tx+ty*4] = 15;
        }

        if (shuffule > 0 ) {
            return true;
        }

        int clearCheck = 0;
        for (int i = 0; i < 16; i++) {
            if (data[i] == 1)clearCheck++;
        }
        if (clearCheck == 16) {
            scene = S_CLEAR;
        }
        invalidate();
        return true;
    }

    private static Random rand = new Random();
    private static int rand (int num) {
        return (rand.nextInt()>>>1)%num;
    }

    private static String num2str(int num, int len) {
        String str = ""+num;
        while (str.length() < len) str = "0"+str;
        return str;
    }

    private static Bitmap readBitmap(Context context, String name) {
        int resID = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return BitmapFactory.decodeResource(context.getResources(), resID);
    }


}
