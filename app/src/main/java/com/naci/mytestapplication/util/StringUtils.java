package com.naci.mytestapplication.util;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;

import androidx.annotation.ColorInt;

public class StringUtils {

    public static Spannable getColoredUnderlineTextSpan(String text, @ColorInt int color, int spanStart, int spanEnd) {
        if (TextUtils.isEmpty(text)) {
            return new SpannableStringBuilder("");
        }
        if (spanEnd < 0) {
            spanEnd = text.length();
        }
        if (spanStart < 0) {
            spanStart = 0;
        }
        if (spanEnd > text.length()) {
            spanEnd = text.length();
        }
        if (spanStart >= spanEnd) {
            return new SpannableStringBuilder(text);
        }
        Spannable spannable = new SpannableStringBuilder(text);
        spannable.setSpan(new ForegroundColorSpan(color), spanStart, spanEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new UnderlineSpan(), spanStart, spanEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
