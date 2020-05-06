package com.naci.mytestapplication.ui.main;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.naci.mytestapplication.App;
import com.naci.mytestapplication.R;
import com.naci.mytestapplication.data.Repository;
import com.naci.mytestapplication.data.remote.Resource;
import com.naci.mytestapplication.data.remote.response.AnimeModel;
import com.naci.mytestapplication.util.SingleLiveEvent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainViewModel extends ViewModel {
    private final Repository repository;
    //Triggers only once
    public final SingleLiveEvent<Resource<AnimeModel>> animeListResponse = new SingleLiveEvent<>();
    public final MutableLiveData<String> errorString = new MutableLiveData<>();
    public String animeGenre;

    /*
        Constructor'a müdahale edemediğimiz (Activity, Fragment gibi) sınıflar dışında
        property injection yerine constructor injection kullanılmalı. Performans ve test için.
     */

    @Inject
    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public String getAppName() {
        return App.getStringById(R.string.app_name);
    }

    @SuppressLint("CheckResult")
    void getAnimeList() {
        Timber.wtf("getAnimeList Request started");
        animeListResponse.setValue(Resource.loading());
        Observable<AnimeModel> observable = repository.getUserRemoteDataSource().getAnimeRetrofitService().getAnimeListByGenre(animeGenre);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AnimeModel>() {
                    @Override
                    public void onNext(AnimeModel animeModel) {
                        Timber.wtf("getAnimeList Request onNext");
                        animeListResponse.setValue(Resource.success(animeModel));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "getAnimeList Request onError");
                        animeListResponse.setValue(Resource.error("Error on connection", e));
                    }

                    @Override
                    public void onComplete() {
                        Timber.wtf("getAnimeList Request Completed");
                        animeListResponse.setValue(Resource.completed());
                    }
                });
    }

    public void onFetchClicked() {
        if (!TextUtils.isEmpty(animeGenre) && animeGenre.length() > 3) {
            errorString.setValue(null);
            getAnimeList();
        } else {
            errorString.setValue(App.getStringById(R.string.anime_search_error, animeGenre));
        }
    }
}
