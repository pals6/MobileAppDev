package com.example.userdataassignment;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String name;
    String email;
    int age;
    String date, country;

    public User(String name, String email, int age, String date, String country) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.date = date;
        this.country = country;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        age = in.readInt();
        date = in.readString();
        country = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeInt(this.age);
        dest.writeString(this.date);
        dest.writeString(this.country);
    }
}