package com.naci.mytestapplication.di.component;

import com.naci.mytestapplication.di.module.AppModule;
import com.naci.mytestapplication.di.module.NetworkModule;
import com.naci.mytestapplication.di.module.ViewModelModule;
import com.naci.mytestapplication.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
                NetworkModule.class,
                ViewModelModule.class
        }
)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);

    MainComponent.Builder mainComponentBuilder();
}
