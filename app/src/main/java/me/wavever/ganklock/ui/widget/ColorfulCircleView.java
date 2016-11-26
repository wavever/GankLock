package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import java.util.Random;
import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/10/12.
 * TODO 在ViewGroup中使用的时候绘制时不完整的,udate:当最外层的VIewGroup的height设置为wrap_content时会出现覆盖的问题，而当设置一个明确的值则不会出现这个问题
 */

public class ColorfulCircleView extends View {

    private static final int DEFAULT_TEXT_SIZE = 20;

    private Paint mCirclePaint;
    private Paint mTextPaint;
    private Paint mIconPaint;
    private String mText;
    private int mTextColor;
    private int mTextSize = DEFAULT_TEXT_SIZE;
    private int mCircleColor;
    private Bitmap mIcon;
    private int mPadding;
    private boolean mAutoCapital;//是否将小写自动转化为大写
    private boolean mRandomBg;
    private int[] mColors;

    private FontMetrics mFontMetrics;


    public ColorfulCircleView(Context context) {
        this(context, null);
    }


    public ColorfulCircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public ColorfulCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorfulCircleView,
            defStyleAttr, 0);
        mText = a.getString(R.styleable.ColorfulCircleView_ccv_text);
        mTextColor = a.getColor(R.styleable.ColorfulCircleView_ccv_text_color, Color.WHITE);
        mTextSize = a.getDimensionPixelSize(R.styleable.ColorfulCircleView_ccv_text_size,
            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize,
                getResources().getDisplayMetrics()));
        mCircleColor = a.getColor(R.styleable.ColorfulCircleView_ccv_background_color, Color.BLUE);
        mIcon = BitmapFactory.decodeResource(getResources(),
            a.getResourceId(R.styleable.ColorfulCircleView_ccv_icon, 0));
        mPadding = a.getDimensionPixelSize(R.styleable.ColorfulCircleView_ccv_padding,
            (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, mPadding,
                getResources().getDisplayMetrics()));
        mAutoCapital = a.getBoolean(R.styleable.ColorfulCircleView_ccv_auto_capital, true);
        mRandomBg = a.getBoolean(R.styleable.ColorfulCircleView_ccv_random_color_bg, true);
        a.recycle();

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mCircleColor);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mIconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFontMetrics = mTextPaint.getFontMetrics();
        if (mRandomBg) {
            mColors = new int[] { R.color.green, R.color.red, R.color.orange,
                R.color.md_blue_grey_300, R.color.md_amber_300, R.color.md_lime_A700,
                R.color.md_teal_A700, R.color.md_light_blue_A700 };
            mCirclePaint.setColor(mColors[new Random().nextInt(5)]);
        }
    }


    public String getText() {
        return mText;
    }


    public void setText(String mText) {
        this.mText = mText;
    }


    public void setTextSize(int size) {
        if (size != mTextSize) {
            mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, size,
                getResources().getDisplayMetrics()));
        }
    }


    public int getTextColor() {
        return mTextColor;
    }


    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
        mTextPaint.setColor(mTextColor);
    }


    public int getCircleColor() {
        return mCircleColor;
    }


    public void setCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
        mCirclePaint.setColor(mCircleColor);
    }


    public Bitmap getIcon() {
        return mIcon;
    }


    public void setIcon(Bitmap mIcon) {
        this.mIcon = mIcon;
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getCircleMeasuredWidth(widthMeasureSpec),
            getCircleMeasuredWidth(heightMeasureSpec));
    }


    private int getCircleMeasuredWidth(int widthMeasureSpec) {

        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        int result = 0;
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            if (specMode == MeasureSpec.AT_MOST) {
                //如果是wrap_content则按照icon或是text测，icon优先
                if (mIcon != null) {
                    result = (int) (Math.max(mIcon.getWidth(), mIcon.getHeight()) * Math.sqrt(2));
                } else {
                    result = (int) (Math.abs(mFontMetrics.ascent) + mFontMetrics.descent);//text的高度
                }
            }
        }
        result += 2 * mPadding;
        return result;
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBttom = getPaddingBottom();

        int r = getMeasuredWidth() / 2;
        canvas.drawCircle(r, r, r, mCirclePaint);
        if (mIcon != null) {
            float left = r - mIcon.getWidth() / 2;
            float top = r - mIcon.getHeight() / 2;
            canvas.drawBitmap(mIcon, left, top, mIconPaint);
            return;
        }
        float x = r - mTextPaint.measureText(getCharFromText()) / 2;
        float y = r + (Math.abs(mFontMetrics.ascent) + mFontMetrics.descent) / 2 -
            mFontMetrics.descent;//或者减去bottom
        canvas.drawText(getCharFromText(), x, y, mTextPaint);
    }


    private String getCharFromText() {
        if (mText != null) {
            return mAutoCapital
                   ? String.valueOf(mText.charAt(0)).toUpperCase()//大写
                   : String.valueOf(mText.charAt(0));
        }
        return "";
    }
}
