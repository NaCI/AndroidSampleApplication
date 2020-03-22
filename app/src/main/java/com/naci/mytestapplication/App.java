package com.naci.mytestapplication;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.StringRes;

import com.naci.mytestapplication.di.component.ApplicationComponent;
import com.naci.mytestapplication.di.component.DaggerApplicationComponent;
import com.naci.mytestapplication.di.component.MainComponent;
import com.naci.mytestapplication.di.module.AppModule;
import com.naci.mytestapplication.di.module.Main.MainModule;
import com.naci.mytestapplication.di.module.Main.TestModule;
import com.naci.mytestapplication.di.module.NetworkModule;

import settings.AppSettings;
import timber.log.Timber;

public class App extends Application {

    private static App instance;
    private ApplicationComponent mAppComponent;
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (instance == null) {
            instance = this;
        }
        if (AppSettings.SHOW_LOG) {
            Timber.plant(new Timber.DebugTree());
        }

        // Dagger%COMPONENT_NAME%
        mAppComponent = DaggerApplicationComponent.builder()
                // list of modules that are part of this component need to be created here too
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .networkModule(new NetworkModule(BuildConfig.BASE_URL))
                .build();

        // If a Dagger 2 component does not have any constructor arguments for any of its modules,
        // then we can use .create() as a shortcut instead:
        //  mAppComponent = DaggerApplicationComponent.create();
    }

    public static String getStringById(@StringRes int id, Object... objects) {
        if (instance == null) {
            return null;
        }
        return instance.getApplicationContext().getString(id, objects);
    }

    public static App getInstance() {
        return instance;
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }

    public MainComponent getMainComponent(Activity activity) {
        if (mainComponent == null) {
            mainComponent = mAppComponent.mainComponentBuilder()
                    .mainModule(new MainModule(activity))
                    .testModule(new TestModule())
                    .build();
        }
        return mainComponent;
    }

    public void clearMainComponent() {
        mainComponent = null;
    }
}
