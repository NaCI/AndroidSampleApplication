package com.naci.mytestapplication.di.module.Main;

import com.naci.mytestapplication.di.scope.MainScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    @Provides
    @MainScope
    @Named("TestModule")
    String providesString() {
        return "TestModule String";
    }
}
