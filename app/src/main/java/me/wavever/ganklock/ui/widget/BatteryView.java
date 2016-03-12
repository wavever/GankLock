package me.wavever.ganklock.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import me.wavever.ganklock.R;

/**
 * Created by WAVE on 2016/2/20.
 */
public class BatteryView extends View {

    private Path path;
    private Bitmap redBattery;
    private Bitmap battery;
    private Paint paint;

    public BatteryView(Context context) {
        this(context, null);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.path = new Path();
        this.redBattery = BitmapFactory.decodeResource(getResources(), R.drawable.gank);
        this.battery = BitmapFactory.decodeResource(getResources(),R.drawable.gank2);
        this.paint = new Paint(1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
