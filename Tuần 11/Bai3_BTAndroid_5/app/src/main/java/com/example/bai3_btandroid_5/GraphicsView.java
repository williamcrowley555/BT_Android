package com.example.bai3_btandroid_5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class GraphicsView extends View {
    Bitmap[] frames = new Bitmap[16];
    int i = 0;
    public GraphicsView(Context context)
    {
        super(context);
        frames[0] = BitmapFactory.decodeResource(getResources(),R.drawable.win_1);
        frames[1] = BitmapFactory.decodeResource(getResources(),R.drawable.win_2);
        frames[2] = BitmapFactory.decodeResource(getResources(),R.drawable.win_3);
        frames[3] = BitmapFactory.decodeResource(getResources(),R.drawable.win_4);
        frames[4] = BitmapFactory.decodeResource(getResources(),R.drawable.win_5);
        frames[5] = BitmapFactory.decodeResource(getResources(),R.drawable.win_6);
        frames[6] = BitmapFactory.decodeResource(getResources(),R.drawable.win_7);
        frames[7] = BitmapFactory.decodeResource(getResources(),R.drawable.win_8);
        frames[8] = BitmapFactory.decodeResource(getResources(),R.drawable.win_9);
        frames[9] = BitmapFactory.decodeResource(getResources(),R.drawable.win_10);
        frames[10] = BitmapFactory.decodeResource(getResources(),R.drawable.win_11);
        frames[11] = BitmapFactory.decodeResource(getResources(),R.drawable.win_12);
        frames[12] = BitmapFactory.decodeResource(getResources(),R.drawable.win_13);
        frames[13] = BitmapFactory.decodeResource(getResources(),R.drawable.win_14);
        frames[14] = BitmapFactory.decodeResource(getResources(),R.drawable.win_15);
        frames[15] = BitmapFactory.decodeResource(getResources(),R.drawable.win_16);

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if(i < 16) {
            canvas.drawBitmap(frames[i],40,40,new Paint());
        }
        else {
            i = 0;
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        i++;
        return true;
    }
}