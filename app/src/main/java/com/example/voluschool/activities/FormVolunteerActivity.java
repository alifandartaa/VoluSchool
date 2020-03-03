package com.example.voluschool.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluschool.R;

import java.util.Objects;

public class FormVolunteerActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_volunteer);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_pembayaran));

        Button btnSubmitVolu = findViewById(R.id.btn_submit_volunteer);
        btnSubmitVolu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit_volunteer) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle(getString(R.string.volunteer));
            alertDialogBuilder
                    .setMessage(R.string.message_alert)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.alert_confirm_positive), (dialog, which) -> {
                        Toast.makeText(FormVolunteerActivity.this, getString(R.string.result_joinvol), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .setNegativeButton(getString(R.string.alert_confirm_negative), (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
