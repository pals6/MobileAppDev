package com.assignment.assignment05;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    String name;
    String email;
    String age;
    String country;
    String dob;

    public User(String name, String email, String age, String country, String dob) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.dob = dob;
    }
    public User(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }


    public User(String dob){
        this.dob=dob;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        age = in.readString();
        country = in.readString();
        dob = in.readString();
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
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(age);
        dest.writeString(country);
        dest.writeString(dob);
    }
}
