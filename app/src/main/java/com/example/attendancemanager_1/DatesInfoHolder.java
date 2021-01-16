package com.example.attendancemanager_1;

import android.os.Parcel;
import android.os.Parcelable;

public class DatesInfoHolder implements Parcelable {

    private String date;

    public DatesInfoHolder() {

    }

    public DatesInfoHolder(String date) {
        this.date = date;
    }

    protected DatesInfoHolder(Parcel in) {
        date = in.readString();
    }

    public static final Creator<DatesInfoHolder> CREATOR = new Creator<DatesInfoHolder>() {
        @Override
        public DatesInfoHolder createFromParcel(Parcel in) {
            return new DatesInfoHolder(in);
        }

        @Override
        public DatesInfoHolder[] newArray(int size) {
            return new DatesInfoHolder[size];
        }
    };

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(date);
    }
}
