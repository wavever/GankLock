package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import me.wavever.ganklock.R;

/**
 * Created by waveverht on 2016/4/19.
 */
public class TagCornerImageView extends ImageView {

    private Paint mPaint;
    private int mTagBgColor;
    private int mTagTextColor;
    private String mTagText;
    private Rect mRect;

    public TagCornerImageView(Context context) {
        this(context, null);
    }

    public TagCornerImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.TagCornerImageView, defStyleAttr, 0);
        mTagBgColor = a.getColor(R.styleable.TagCornerImageView_tag_background_color, Color.BLUE);
        mTagTextColor = a.getInteger(R.styleable.TagCornerImageView_tag_text_color,Color.WHITE);
        mTagText = a.getString(R.styleable.TagCornerImageView_tag_text);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mPaint.setStyle(Paint.Style.FILL);//填充
        mPaint.setTextSize(10);
        mRect = new Rect();
        mPaint.getTextBounds(mTagText,0,mTagText.length(),mRect);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        mPaint.setColor(mTagBgColor);
        canvas.drawRect(0,0,getMeasuredWidth()/4,getMeasuredHeight()/4,mPaint);
        mPaint.setColor(mTagTextColor);
        canvas.drawText(mTagText,getWidth()-mRect.width()/2,getHeight()-mRect.height()/2,mPaint);
        //mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.rotate(20);
        canvas.restore();
    }


}
