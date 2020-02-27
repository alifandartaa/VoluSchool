package com.example.voluschool.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.voluschool.R;

public class FormDonasiActivity extends AppCompatActivity implements View.OnClickListener {
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
                    .setPositiveButton(getString(R.string.alert_confirm_positive), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            choosenBank = spBank.getSelectedItem().toString();
                            Toast.makeText(FormDonasiActivity.this, getString(R.string.result_donasi), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton(getString(R.string.alert_confirm_negative), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
