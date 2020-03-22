package com.naci.mytestapplication.di.component;

import com.naci.mytestapplication.di.module.Main.MainModule;
import com.naci.mytestapplication.di.module.Main.TestModule;
import com.naci.mytestapplication.di.scope.MainScope;
import com.naci.mytestapplication.ui.animelist.AnimeFragment;
import com.naci.mytestapplication.ui.main.MainFragment;

import dagger.Subcomponent;

@MainScope
@Subcomponent(modules = {MainModule.class, TestModule.class})
public interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        MainComponent.Builder mainModule(MainModule mainModule);

        MainComponent.Builder testModule(TestModule testModule);

        MainComponent build();
    }

    void inject(MainFragment mainFragment);

    void inject(AnimeFragment mainFragment);
}
