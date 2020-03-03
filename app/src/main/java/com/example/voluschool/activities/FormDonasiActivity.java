package com.example.voluschool.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluschool.R;

import java.util.Objects;

public class FormDonasiActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner spBank;
    private String choosenBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_donasi);

        Objects.requireNonNull(getSupportActionBar()).setTitle(getString(R.string.detail_pembayaran));

        Button btnSubmitDonasi = findViewById(R.id.btn_submit_donasi);
        spBank = findViewById(R.id.sp_metode);
        btnSubmitDonasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit_donasi) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle(R.string.submit_donasi);
            alertDialogBuilder
                    .setMessage(R.string.message_alert)
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.alert_confirm_positive), (dialog, which) -> {
                        choosenBank = spBank.getSelectedItem().toString();
                        switch (choosenBank) {
                            case "BNI":
                                setContentView(R.layout.layout_bni);
                                break;
                            case "BCA":
                                setContentView(R.layout.layout_bca);
                                break;
                            case "Mandiri":
                                setContentView(R.layout.layout_mandiri);
                                break;
                            case "BRI":
                                setContentView(R.layout.layout_bri);
                                break;
                        }
                        Toast.makeText(FormDonasiActivity.this, getString(R.string.result_donasi), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    })
                    .setNegativeButton(getString(R.string.alert_confirm_negative), (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }
}
