package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.voluschool.R;
import com.example.voluschool.model.PostVolunteer;

public class DetailVolunteerActivity extends AppCompatActivity {

    public static final String EXTRA_VOLUNTEER = "extra_volunteer";
    private Intent intent;
    private PostVolunteer postVolunteer;
    private TextView tvDetSchool, tvTotPeople, tvCompany, tvStory;
    private ImageView ivDetSchool;
    private Button btnJoinVolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_volunteer);


    }
}
