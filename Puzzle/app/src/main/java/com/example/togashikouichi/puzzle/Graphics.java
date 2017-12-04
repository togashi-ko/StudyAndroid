package com.example.togashikouichi.puzzle;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by TogashiKouichi on 2017/11/28.
 */

public class Graphics {
    private Paint paint;
    private Bitmap bmp;
    private Canvas canvas;
    private int originX;
    private int originY;

    public  Graphics(int w, int h){
        bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public Bitmap getBmp() {
        return  bmp;
    }

    public void setOrigin (int x, int y) {
        canvas.translate(x, y);
        originX = x;
        originY = y;
    }

    public int getOriginX(){
        return originX;
    }
    public int getOriginY() {
        return originY;
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void  setTextSize (int fontSize) {
        paint.setTextSize(fontSize);
    }

    public Paint.FontMetrics getFontMetrics() {
        return paint.getFontMetrics();
    }

    public int measureText(String string) {
        return (int)paint.measureText(string);
    }

    public void fillRect (int x, int y, int w, int h) {
        if (canvas == null) return;;
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(new Rect(x, y, x+w, y+h), paint);
    }

    public void drawBitmap(Bitmap bitmap, int x, int y ) {
        if (canvas == null)return;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Rect src = new Rect(0, 0 ,w ,h);
        Rect dst = new Rect(x, y ,x+w ,y+h);
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    public void drawBitmap(Bitmap bitmap, Rect src, Rect dst) {
        if (canvas == null) return;
        canvas.drawBitmap(bitmap, src, dst, null);
    }

    public void drawText (String string, int x, int y) {
        if (canvas == null) return;
        canvas.drawText(string, x, y, paint);
    }
}
