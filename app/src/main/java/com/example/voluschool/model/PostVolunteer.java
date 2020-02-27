package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostVolunteer implements Parcelable {
    private String id;
    private String schoolName;
    private int totalPeople;
    private String story;
    private String company;
    private int schoolImage;


    protected PostVolunteer(Parcel in) {
        id = in.readString();
        schoolName = in.readString();
        totalPeople = in.readInt();
        story = in.readString();
        company = in.readString();
        schoolImage = in.readInt();
    }

    public static final Creator<PostVolunteer> CREATOR = new Creator<PostVolunteer>() {
        @Override
        public PostVolunteer createFromParcel(Parcel in) {
            return new PostVolunteer(in);
        }

        @Override
        public PostVolunteer[] newArray(int size) {
            return new PostVolunteer[size];
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
        dest.writeInt(totalPeople);
        dest.writeString(story);
        dest.writeString(company);
        dest.writeInt(schoolImage);
    }

    public PostVolunteer(String id, String schoolName, int totalPeople, String story, String company, int schoolImage) {
        this.id = id;
        this.schoolName = schoolName;
        this.totalPeople = totalPeople;
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

    public int getTotalPeople() {
        return totalPeople;
    }

    public void setTotalPeople(int totalPeople) {
        this.totalPeople = totalPeople;
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
