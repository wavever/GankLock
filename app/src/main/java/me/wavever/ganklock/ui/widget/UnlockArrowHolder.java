package me.wavever.ganklock.ui.widget;

import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import me.wavever.ganklock.R;

/**
 * Created by wavever on 2016/5/25.
 */
public class UnlockArrowHolder extends RelativeLayout {

    private ValueAnimator valueAnimator = null;
    private AnimatorSet animationSet = null;
    private UnlockArrow[] arrows = null;
    private int animationDistance = 0;//滑动箭头做动画的距离
    private int arrowGap = 0;//箭头之间的距离

    public UnlockArrowHolder(Context context) {
        super(context);
        init(context);
    }

    public UnlockArrowHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public UnlockArrowHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        animationDistance = (int) context.getResources().getDimension(R.dimen.unlock_arrow_animation_distance);
        arrowGap = context.getResources().getDimensionPixelSize(R.dimen.unlock_arrow_gap);
        arrows = new UnlockArrow[3];
        for (int i = 0; i < 3; i++) {
            arrows[i] = new UnlockArrow(getContext());
            addView(arrows[i]);
            arrows[i].setAlpha(0.0f);
        }
        animationSet = new AnimatorSet();
        animationSet.setInterpolator(new LinearInterpolator());
        float f = animationDistance * 2 + arrowGap * 2; //总共的距离
        valueAnimator = ObjectAnimator.ofObject(new ArrowEvaluator(this), new Object[]{Integer.valueOf(0), Float.valueOf(f)}).setDuration(1680);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        animationSet.play(valueAnimator);
    }

    public class ArrowEvaluator extends FloatEvaluator {

        private final UnlockArrowHolder unlockArrowHolder;

        public ArrowEvaluator(UnlockArrowHolder unlockArrowHolder) {
            this.unlockArrowHolder = unlockArrowHolder;
        }

        @Override
        public Float evaluate(float fraction, Number startValue, Number endValue) {

            float startFloat = startValue.floatValue();
            float float2 = startFloat + ((endValue.floatValue() - startFloat) * fraction);//当前动画的完成度
            for (int i = 0; i < 3; i++) {
                float s = float2 - arrowGap * i;
                if (s > animationDistance * 2) {
                    s = animationDistance * 2;
                }
                arrows[i].setTranslationX(s);
                arrows[i].setAlpha(s > animationDistance ?
                        ((animationDistance * 2 - s) / (animationDistance * 1.0f)) : animationDistance * 1.0f);
            }
            return Float.valueOf((endValue.floatValue() - startFloat) * fraction + startFloat);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        startAnim();
        super.onAttachedToWindow();
    }

    private void startAnim() {
        setVisibility(VISIBLE);
        for (int i = 0; i < 3; i++) {
            arrows[i].setLayerType(LAYER_TYPE_HARDWARE, null);
        }
        animationSet.start();
    }

    private void stopAnim() {
        setVisibility(GONE);
        for (int i = 0; i < 3; i++) {
            arrows[i].setLayerType(LAYER_TYPE_NONE, null);
        }
        animationSet.cancel();//set会遍历子Animator调用cancel方法
    }

    @Override
    protected void onDetachedFromWindow() {
        stopAnim();
        super.onDetachedFromWindow();
    }
}
