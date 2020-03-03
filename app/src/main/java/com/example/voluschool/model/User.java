package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private String name;
    private String email;
    private String password;
    private String nohp;
    private String noktp;
    private String image;

    private User(Parcel in) {
        id = in.readInt();
        name = in.readString();
        email = in.readString();
        password = in.readString();
        nohp = in.readString();
        noktp = in.readString();
        image = in.readString();
    }

    public User(int id, String name, String email, String password, String nohp, String noktp, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nohp = nohp;
        this.noktp = noktp;
        this.image = image;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(nohp);
        dest.writeString(noktp);
        dest.writeString(image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
