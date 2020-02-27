package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

//namaLengkap, email, password, confPass, noHp, noKtp
public class User implements Parcelable {
    private static int count = 0;
    private int id;
    private String name;
    private String email;
    private String password;
    private String confirmpw;
    private String nohp;
    private String noktp;

    public User(String name, String email, String password, String confirmpw, String nohp, String noktp) {
        this.id = ++count;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmpw = confirmpw;
        this.nohp = nohp;
        this.noktp = noktp;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpw() {
        return confirmpw;
    }

    public void setConfirmpw(String confirmpw) {
        this.confirmpw = confirmpw;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    protected User(Parcel in) {
        name = in.readString();
        email = in.readString();
        password = in.readString();
        confirmpw = in.readString();
        nohp = in.readString();
        noktp = in.readString();
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
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(confirmpw);
        dest.writeString(nohp);
        dest.writeString(noktp);
    }
}
