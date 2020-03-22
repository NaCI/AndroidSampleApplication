package com.naci.mytestapplication.di.module.Main;

import android.app.Activity;
import android.content.Context;

import com.naci.mytestapplication.di.scope.MainScope;
import com.naci.mytestapplication.ui.customview.CustomProgressBar;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    Activity activity;

    public MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScope
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @MainScope
    @Named("MainModule")
    String providesString() {
        return "MainModule String";
    }

    @Provides
    @MainScope
    public CustomProgressBar provideProgressBar(Activity activity) {
        return new CustomProgressBar(activity);
    }
}
