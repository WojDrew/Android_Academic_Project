package com.example.mso_laboratorium_zdalne.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

        private Path path;
        private Paint paint;

        public DrawingView(Context context, AttributeSet attrs){
                super(context, attrs);
                path = new Path();
                paint = new Paint();
                paint.setAntiAlias(true);
                paint.setColor(Color.RED);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(5f);
        }

        @Override
        protected void onDraw(Canvas canvas) {
                canvas.drawPath(path,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                                path.moveTo(x, y);
                                return true;
                        case MotionEvent.ACTION_MOVE:
                                path.lineTo(x, y);
                                break;
                        default:
                                return false;
                }
                invalidate();
                return true;
        }
}