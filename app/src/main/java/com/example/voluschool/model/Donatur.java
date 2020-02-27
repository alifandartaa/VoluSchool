package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Donatur implements Parcelable {
    private String id;
    private String nama;
    private int donationCost;
    private String date;


    protected Donatur(Parcel in) {
        id = in.readString();
        nama = in.readString();
        donationCost = in.readInt();
        date = in.readString();
    }

    public static final Creator<Donatur> CREATOR = new Creator<Donatur>() {
        @Override
        public Donatur createFromParcel(Parcel in) {
            return new Donatur(in);
        }

        @Override
        public Donatur[] newArray(int size) {
            return new Donatur[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nama);
        dest.writeInt(donationCost);
        dest.writeString(date);
    }

    public Donatur(String id, String nama, int donationCost, String date) {
        this.id = id;
        this.nama = nama;
        this.donationCost = donationCost;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getDonationCost() {
        return donationCost;
    }

    public void setDonationCost(int donationCost) {
        this.donationCost = donationCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
