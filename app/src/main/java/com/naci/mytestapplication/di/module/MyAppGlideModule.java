package com.naci.mytestapplication.di.module;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.naci.mytestapplication.R;

import org.jetbrains.annotations.NotNull;

@GlideModule
public final class MyAppGlideModule extends AppGlideModule {
    @Override
    public void applyOptions(@NotNull Context context, GlideBuilder builder) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_rowing)
                .error(R.drawable.ic_report_problem);
        builder.setDefaultRequestOptions(requestOptions);
    }
}
