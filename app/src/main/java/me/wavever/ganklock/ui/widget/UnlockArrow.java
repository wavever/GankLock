package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import me.wavever.ganklock.R;

/**
 * Created by wavevr on 2016/2/20.
 */
public class UnlockArrow extends View {

    private int width;
    private int height;
    private int strokeWidth;
    private Paint paint;
    private Path path;

    public UnlockArrow(Context context) {
        this(context, null);
    }

    public UnlockArrow(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public UnlockArrow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        paint = new Paint();
        path = new Path();
        strokeWidth = context.getResources().getDimensionPixelSize(R.dimen.unlock_arrow_stroke_width);
        width = context.getResources().getDimensionPixelSize(R.dimen.unlock_arrow_width);
        height = context.getResources().getDimensionPixelSize(R.dimen.unlock_arrow_height);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth((float) this.strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = width;
        }else{
            if(mode == MeasureSpec.AT_MOST){
                result = this.width+strokeWidth;
            }
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec) {
        int result = 0;
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if(mode == MeasureSpec.EXACTLY){
            result = height;
        }else{
            if(mode == MeasureSpec.AT_MOST){
                result = this.height+strokeWidth;
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(0, 0);
        path.lineTo(width, height / 2);
        path.lineTo(0, height);
        canvas.drawPath(path, paint);
    }

}
