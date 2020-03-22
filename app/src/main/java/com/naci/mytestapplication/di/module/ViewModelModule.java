package com.naci.mytestapplication.di.module;

import androidx.lifecycle.ViewModel;

import com.naci.mytestapplication.di.ViewModelKey;
import com.naci.mytestapplication.ui.animelist.AnimeViewModel;
import com.naci.mytestapplication.ui.main.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AnimeViewModel.class)
    abstract ViewModel bindAnimeViewModel(AnimeViewModel mainViewModel);
}
