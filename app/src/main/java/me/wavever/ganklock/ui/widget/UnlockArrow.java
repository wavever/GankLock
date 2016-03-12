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
 * Created by WAVE on 2016/2/20.
 */
public class UnlockArrow extends View {

    private int width;
    private int height;
    private int strokeWidth;
    private int locate;
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
        this.height = 0;
        this.width = 0;
        this.strokeWidth = 0;
        this.locate = 0;
        this.paint = new Paint();
        this.path = new Path();
        init(context);
    }


    private void init(Context context) {

        this.strokeWidth = context.getResources()
                                  .getDimensionPixelSize(
                                          R.dimen.unlock_arrow_stroke_width);
        this.locate = context.getResources()
                             .getDimensionPixelOffset(
                                     R.dimen.unlock_arrow_locate);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth((float) this.strokeWidth);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setColor(Color.BLUE);
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.width = getMeasuredWidth();
        this.height = getMeasuredHeight();
        this.path.reset();
        this.path.moveTo((float) this.locate, (float) this.locate);
        this.path.lineTo((float) (this.width / 2 + this.locate),
                (float) (this.locate - this.height));
        this.path.lineTo((float) (this.locate + this.width),
                (float) this.locate);

      /*  this.path.lineTo((float) (this.width + this.strokeWidth), (float) (
                (this.height / 2) + this.strokeWidth));
        this.path.lineTo((float) this.strokeWidth, (float) (this.height + this.strokeWidth));*/

        canvas.drawPath(this.path, this.paint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }


    private int measureWidth(int widthMeasureSpec) {
        int width;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        else {
            width = 120;
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(width, widthSize);
            }
        }
        return width;
    }


    private int measureHeight(int heightMeasureSpec) {
        int height;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        else {
            height = 50;
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(height, heightSize);
            }
        }
        return height;
    }
}
