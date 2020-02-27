package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.voluschool.R;
import com.example.voluschool.model.PostVolunteer;

public class DetailVolunteerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_VOLUNTEER = "extra_volunteer";
    private Intent intent;
    private PostVolunteer postVolunteer;
    private TextView tvDetSchool, tvRegistered, tvTarget, tvCompany, tvStory, tvLocation, tvDonatur;
    private ImageView ivDetSchool;
    private Button btnJoinVolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_volunteer);

        init();
        getSupportActionBar().hide();
        intent = getIntent();
        postVolunteer = intent.getParcelableExtra(EXTRA_VOLUNTEER);
        tvDetSchool.setText(postVolunteer.getSchoolName());
        tvTarget.append(String.valueOf(postVolunteer.getTotalPeople()));
        tvRegistered.append(String.valueOf(postVolunteer.getRegisteredPeople()));
        tvCompany.setText(postVolunteer.getCompany());
        tvStory.setText(postVolunteer.getStory());
        Glide.with(this)
                .load(postVolunteer.getSchoolImage())
                .into(ivDetSchool);

        btnJoinVolu.setOnClickListener(this);
    }

    private void init() {
        tvDetSchool = findViewById(R.id.tv_detvol_name);
        tvRegistered = findViewById(R.id.tv_detvol_people);
        tvTarget = findViewById(R.id.tv_detvol_total);
        tvCompany = findViewById(R.id.tv_detvol_company);
        tvStory = findViewById(R.id.tv_detvol_story);
        ivDetSchool = findViewById(R.id.iv_detvol_school);
        btnJoinVolu = findViewById(R.id.btn_detail_donate);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_detail_donate) {
            Intent intent = new Intent(DetailVolunteerActivity.this, FormVolunteerActivity.class);
            startActivity(intent);
        }
    }
}
