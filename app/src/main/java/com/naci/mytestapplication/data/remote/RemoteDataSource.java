package com.naci.mytestapplication.data.remote;

import com.naci.mytestapplication.data.remote.api.AnimeServiceApi;

import javax.inject.Inject;

public class RemoteDataSource {
    private final AnimeServiceApi animeServiceApi;

    @Inject
    public RemoteDataSource(AnimeServiceApi animeServiceApi) {
        this.animeServiceApi = animeServiceApi;
    }

    public AnimeServiceApi getAnimeRetrofitService() {
        return animeServiceApi;
    }
}
