package com.example.voluschool.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.voluschool.R;
import com.example.voluschool.model.PostVolunteer;

import java.util.Objects;

public class DetailVolunteerActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_VOLUNTEER = "extra_volunteer";
    private PostVolunteer postVolunteer;
    private TextView tvDetSchool, tvRegistered, tvTarget, tvCompany, tvStory, tvVolPenuh;
    private ImageView ivDetSchool;
    private Button btnJoinVolu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_volunteer);

        init();
        Objects.requireNonNull(getSupportActionBar()).setTitle(getResources().getString(R.string.detail_sekolah));
        Intent intent = getIntent();
        postVolunteer = intent.getParcelableExtra(EXTRA_VOLUNTEER);
        tvDetSchool.setText(Objects.requireNonNull(postVolunteer).getSchoolName());
        if(postVolunteer.getRegisteredPeople() == postVolunteer.getTotalPeople()){
            tvVolPenuh.setVisibility(View.VISIBLE);
        }
        tvTarget.append(String.valueOf(postVolunteer.getTotalPeople()));
        String registered = String.valueOf(postVolunteer.getRegisteredPeople());
        String formatted = "Terkumpul" + " " + registered + "orang";
        tvRegistered.setText(formatted);
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
        tvVolPenuh = findViewById(R.id.tv_detvol_penuh);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_detail_donate) {
            if(postVolunteer.getRegisteredPeople() == postVolunteer.getTotalPeople()){
                Toast.makeText(this, getString(R.string.string_volu_penuh), Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(DetailVolunteerActivity.this, FormVolunteerActivity.class);
                startActivity(intent);
            }
        }
    }
}
