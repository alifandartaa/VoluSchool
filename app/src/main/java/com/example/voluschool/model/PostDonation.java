package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostDonation implements Parcelable {
    private String id;
    private String schoolName;
    private int donationCost;
    private int totalCost;
    private String story;
    private String company;
    private int schoolImage;


    protected PostDonation(Parcel in) {
        id = in.readString();
        schoolName = in.readString();
        donationCost = in.readInt();
        totalCost = in.readInt();
        story = in.readString();
        company = in.readString();
        schoolImage = in.readInt();
    }

    public static final Creator<PostDonation> CREATOR = new Creator<PostDonation>() {
        @Override
        public PostDonation createFromParcel(Parcel in) {
            return new PostDonation(in);
        }

        @Override
        public PostDonation[] newArray(int size) {
            return new PostDonation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(schoolName);
        dest.writeInt(donationCost);
        dest.writeInt(totalCost);
        dest.writeString(story);
        dest.writeString(company);
        dest.writeInt(schoolImage);
    }

    public PostDonation(String id, String schoolName, int donationCost, int totalCost, String story, String company, int schoolImage) {
        this.id = id;
        this.schoolName = schoolName;
        this.donationCost = donationCost;
        this.totalCost = totalCost;
        this.story = story;
        this.company = company;
        this.schoolImage = schoolImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getDonationCost() {
        return donationCost;
    }

    public void setDonationCost(int donationCost) {
        this.donationCost = donationCost;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getSchoolImage() {
        return schoolImage;
    }

    public void setSchoolImage(int schoolImage) {
        this.schoolImage = schoolImage;
    }
}

