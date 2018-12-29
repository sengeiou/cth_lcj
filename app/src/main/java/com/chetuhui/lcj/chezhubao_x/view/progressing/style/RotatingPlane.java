package com.chetuhui.lcj.chezhubao_x.view.progressing.style;

import android.animation.ValueAnimator;
import android.graphics.Rect;

import com.chetuhui.lcj.chezhubao_x.view.progressing.animation.SpriteAnimatorBuilder;
import com.chetuhui.lcj.chezhubao_x.view.progressing.sprite.RectSprite;

/**
 * @author vondear
 */
public class RotatingPlane extends RectSprite {
    @Override
    protected void onBoundsChange(Rect bounds) {
        setDrawBounds(clipSquare(bounds));
    }

    @Override
    public ValueAnimator onCreateAnimation() {
        float fractions[] = new float[]{0f, 0.5f, 1f};
        return new SpriteAnimatorBuilder(this).
                rotateX(fractions, 0, -180, -180).
                rotateY(fractions, 0, 0, -180).
                duration(1200).
                easeInOut(fractions)
                .build();
    }
}