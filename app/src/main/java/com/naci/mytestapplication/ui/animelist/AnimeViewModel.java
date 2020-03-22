package com.naci.mytestapplication.ui.animelist;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

//TODO : aktivite içinde shared view model kullanılabilir
public class AnimeViewModel extends ViewModel {
    public final MutableLiveData<Object> animeListResponse = new MutableLiveData<>();

    @Inject
    public AnimeViewModel() {
    }
}
