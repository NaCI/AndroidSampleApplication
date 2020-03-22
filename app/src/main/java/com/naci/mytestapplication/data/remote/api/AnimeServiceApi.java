package com.naci.mytestapplication.data.remote.api;

import com.naci.mytestapplication.data.remote.response.AnimeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import settings.AppSettings;

public interface AnimeServiceApi {

    String ACCEPT = "Accept: application/vnd.api+json";
    String CONTENT_TYPE = "Content-Type: application/vnd.api+json";
    String ACCEPT_CHARSET = "Accept-Charset: utf-8";

    @Headers({ACCEPT, CONTENT_TYPE, ACCEPT_CHARSET})
    @GET(AppSettings.END_POINT)
    Observable<AnimeModel> getAnimeListByGenre(@Query("filter[genres]") String genres);
}
