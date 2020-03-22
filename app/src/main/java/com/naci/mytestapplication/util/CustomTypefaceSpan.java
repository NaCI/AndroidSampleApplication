package com.naci.mytestapplication.util;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

import androidx.annotation.ColorInt;

public class CustomTypefaceSpan extends TypefaceSpan {

    private final Typeface newType;
    private int mColor = -1;

    public CustomTypefaceSpan(String family, Typeface type) {
        super(family);
        newType = type;
    }

    public CustomTypefaceSpan(String family, Typeface type, @ColorInt int color) {
        super(family);
        newType = type;
        mColor = color;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        if (mColor > 0) {
            ds.setColor(mColor);
        }
        applyCustomTypeFace(ds, newType);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }

    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }
}
