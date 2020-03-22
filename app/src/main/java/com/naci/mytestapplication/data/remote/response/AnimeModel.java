package com.naci.mytestapplication.data.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnimeModel implements Parcelable {
    @SerializedName("data")
    private List<AnimeData> animeList;

    public AnimeModel(List<AnimeData> animeList) {
        this.animeList = animeList;
    }

    public List<AnimeData> getAnimeList() {
        return animeList;
    }

    public void setAnimeList(List<AnimeData> animeList) {
        this.animeList = animeList;
    }

    @Override
    public String toString() {
        return "AnimeModel{" +
                "animeList=" + animeList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.animeList);
    }

    protected AnimeModel(Parcel in) {
        this.animeList = in.createTypedArrayList(AnimeData.CREATOR);
    }

    public static final Parcelable.Creator<AnimeModel> CREATOR = new Parcelable.Creator<AnimeModel>() {
        @Override
        public AnimeModel createFromParcel(Parcel source) {
            return new AnimeModel(source);
        }

        @Override
        public AnimeModel[] newArray(int size) {
            return new AnimeModel[size];
        }
    };
}
