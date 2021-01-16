package com.example.attendancemanager_1;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentInfoHolder implements Parcelable {
    private String name;
    private String registration;
    private int presentCount;
    private int presenceStatus;
    public static final int status_present = 1;
    public static final int status_absent = 2;


    public StudentInfoHolder() {
    }

    public StudentInfoHolder(String name, String registration, int presentCount) {
        this.name = name;
        this.registration = registration;
        this.presentCount = presentCount;

    }

    protected StudentInfoHolder(Parcel in) {
        name = in.readString();
        registration = in.readString();
        presentCount = in.readInt();
    }

    public static final Creator<StudentInfoHolder> CREATOR = new Creator<StudentInfoHolder>() {
        @Override
        public StudentInfoHolder createFromParcel(Parcel in) {
            return new StudentInfoHolder(in);
        }

        @Override
        public StudentInfoHolder[] newArray(int size) {
            return new StudentInfoHolder[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(int presentCount) {
        this.presentCount = presentCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(registration);
        parcel.writeInt(presentCount);
    }
}
