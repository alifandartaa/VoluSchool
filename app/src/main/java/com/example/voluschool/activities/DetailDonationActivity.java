package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.voluschool.R;
import com.example.voluschool.fragments.DonateFragment;
import com.example.voluschool.fragments.DonaturFragment;
import com.example.voluschool.fragments.VolunteerFragment;
import com.example.voluschool.model.PostDonation;

public class DetailDonationActivity extends AppCompatActivity {

    public static final String EXTRA_DONATION = "extra_donation";
    private Intent intent;
    private PostDonation postDonation;
    private TextView tvDetSchool, tvDetCost, tvDetTotal, tvCompany, tvStory, tvDonPenuh;
    private ImageView ivDetSchool;
    private Button btnDonate;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_donation);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        DonaturFragment donaturFragment = new DonaturFragment();
        fragmentTransaction.replace(R.id.fg_donatur, donaturFragment);
        fragmentTransaction.commit();

        init();
//        getSupportActionBar().hide();
        getSupportActionBar().setTitle(getResources().getString(R.string.detail_sekolah));
        intent = getIntent();
        postDonation = intent.getParcelableExtra(EXTRA_DONATION);
        tvDetSchool.setText(postDonation.getSchoolName());
        if(postDonation.getDonationCost() == postDonation.getTotalCost()){
            tvDonPenuh.setVisibility(View.VISIBLE);
        }
        tvDetCost.append(String.valueOf(postDonation.getDonationCost()));
        tvDetTotal.append(String.valueOf(postDonation.getTotalCost()));
        tvCompany.setText(postDonation.getCompany());
        tvStory.setText(postDonation.getStory());
        Glide.with(this)
                .load(postDonation.getSchoolImage())
                .into(ivDetSchool);

        btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postDonation.getDonationCost() == postDonation.getTotalCost()){
                    Toast.makeText(DetailDonationActivity.this, getString(R.string.string_penuh), Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(DetailDonationActivity.this, FormDonasiActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void init() {
        tvDetSchool = findViewById(R.id.tv_detschool_name);
        tvDetCost = findViewById(R.id.tv_detschool_cost);
        tvDetTotal = findViewById(R.id.tv_detschool_total);
        tvCompany = findViewById(R.id.tv_detail_company);
        tvStory = findViewById(R.id.tv_detail_story);
        btnDonate = findViewById(R.id.btn_detail_donate);
        ivDetSchool = findViewById(R.id.iv_detail_school);
        tvDonPenuh = findViewById(R.id.tv_detdon_penuh);
    }
}
