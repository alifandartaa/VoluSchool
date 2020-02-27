package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.voluschool.R;

public class FormDonasiActivity extends AppCompatActivity {
    private Button btnSubmitDonasi;
    private Spinner spBank;
    private String choosenBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_donasi);

        getSupportActionBar().setTitle(getString(R.string.detail_pembayaran));

        btnSubmitDonasi = findViewById(R.id.btn_submit_donasi);
        spBank = findViewById(R.id.sp_metode);

        btnSubmitDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosenBank = spBank.getSelectedItem().toString();
                if(choosenBank.equalsIgnoreCase("BNI")){
                    setContentView(R.layout.layout_bni);
                }
            }
        });
    }
}
