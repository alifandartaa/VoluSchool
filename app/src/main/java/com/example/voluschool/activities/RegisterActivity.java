package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voluschool.R;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.RegisterResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private Button btnRegister;
    private String namaLengkap, email, password, confPass, noHp, noKtp;
    private EditText etNamaLengkap, etEmail, etPassw, etConfPassw, etNoHp, etNoKtp;
    MyApi myApi;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        Objects.requireNonNull(getSupportActionBar()).hide();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestRegister();
            }
        });
    }

    private void init() {
        btnRegister = findViewById(R.id.btn_submit_register);
        etNamaLengkap = findViewById(R.id.et_nama_lengkap_register);
        etEmail = findViewById(R.id.et_email_register);
        etPassw = findViewById(R.id.et_password_register);
        etConfPassw = findViewById(R.id.et_conpass_register);
        etNoHp = findViewById(R.id.et_nohp_register);
        etNoKtp = findViewById(R.id.et_noktp_register);
    }

    private void requestRegister(){
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        namaLengkap = etNamaLengkap.getText().toString();
        email = etEmail.getText().toString();
        password = etPassw.getText().toString();
        confPass = etConfPassw.getText().toString();
        noHp = etNoHp.getText().toString();
        noKtp = etNoKtp.getText().toString();

        myApi = ApiClient.getClient().create(MyApi.class);
        User user = new User(namaLengkap, email, password, confPass, noHp, noKtp);

        Call<RegisterResponse> registerCall = myApi.register(user);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
