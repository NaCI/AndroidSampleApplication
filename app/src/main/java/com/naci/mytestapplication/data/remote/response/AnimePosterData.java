package com.naci.mytestapplication.data.remote.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class AnimePosterData implements Parcelable {
    @SerializedName("tiny")
    private String tinyImage;
    @SerializedName("small")
    private String smallImage;
    @SerializedName("original")
    private String originalImage;
    @SerializedName("medium")
    private String mediumImage;

    public AnimePosterData(String tinyImage, String smallImage, String originalImage, String mediumImage) {
        this.tinyImage = tinyImage;
        this.smallImage = smallImage;
        this.originalImage = originalImage;
        this.mediumImage = mediumImage;
    }

    public String getTinyImage() {
        return tinyImage;
    }

    public void setTinyImage(String tinyImage) {
        this.tinyImage = tinyImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tinyImage);
        dest.writeString(this.smallImage);
        dest.writeString(this.originalImage);
        dest.writeString(this.mediumImage);
    }

    protected AnimePosterData(Parcel in) {
        this.tinyImage = in.readString();
        this.smallImage = in.readString();
        this.originalImage = in.readString();
        this.mediumImage = in.readString();
    }

    public static final Parcelable.Creator<AnimePosterData> CREATOR = new Parcelable.Creator<AnimePosterData>() {
        @Override
        public AnimePosterData createFromParcel(Parcel source) {
            return new AnimePosterData(source);
        }

        @Override
        public AnimePosterData[] newArray(int size) {
            return new AnimePosterData[size];
        }
    };
}
