package com.naci.mytestapplication.data.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AnimeData implements Parcelable {
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("attributes")
    private AnimeAttributesData detail;

    public AnimeData(String id, String type, AnimeAttributesData detail) {
        this.id = id;
        this.type = type;
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AnimeAttributesData getDetail() {
        return detail;
    }

    public void setDetail(AnimeAttributesData detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "AnimeData{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", detail=" + detail +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.type);
        dest.writeParcelable(this.detail, flags);
    }

    protected AnimeData(Parcel in) {
        this.id = in.readString();
        this.type = in.readString();
        this.detail = in.readParcelable(AnimeAttributesData.class.getClassLoader());
    }

    public static final Parcelable.Creator<AnimeData> CREATOR = new Parcelable.Creator<AnimeData>() {
        @Override
        public AnimeData createFromParcel(Parcel source) {
            return new AnimeData(source);
        }

        @Override
        public AnimeData[] newArray(int size) {
            return new AnimeData[size];
        }
    };
}
