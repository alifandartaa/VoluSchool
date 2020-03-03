package com.example.voluschool.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PostVolunteer implements Parcelable {
    private static int count = 0;
    private int id;
    private String schoolName;
    private int registeredPeople;
    private int totalPeople;
    private String story;
    private String location;
    private String criteria;
    private String company;
    private int schoolImage;

    public PostVolunteer(String schoolName, int registeredPeople, int totalPeople, String story, String location, String criteria, String company, int schoolImage) {
        this.id = ++count;
        this.schoolName = schoolName;
        this.registeredPeople = registeredPeople;
        this.totalPeople = totalPeople;
        this.story = story;
        this.location = location;
        this.criteria = criteria;
        this.company = company;
        this.schoolImage = schoolImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public int getRegisteredPeople() {
        return registeredPeople;
    }

    public int getTotalPeople() {
        return totalPeople;
    }

    public String getStory() {
        return story;
    }

    public String getCompany() {
        return company;
    }

    public int getSchoolImage() {
        return schoolImage;
    }

    private PostVolunteer(Parcel in) {
        id = in.readInt();
        schoolName = in.readString();
        registeredPeople = in.readInt();
        totalPeople = in.readInt();
        story = in.readString();
        location = in.readString();
        criteria = in.readString();
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
        dest.writeInt(id);
        dest.writeString(schoolName);
        dest.writeInt(registeredPeople);
        dest.writeInt(totalPeople);
        dest.writeString(story);
        dest.writeString(location);
        dest.writeString(criteria);
        dest.writeString(company);
        dest.writeInt(schoolImage);
    }
}
