package com.naci.mytestapplication.ui.base;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.naci.mytestapplication.di.module.GlideApp;

public class BindingAdapters {
    @BindingAdapter("srcUrl")
    public static void setImageUrl(ImageView view, String url) {
        GlideApp.with(view.getContext()).
                load(url).
                into(view);
    }
}
