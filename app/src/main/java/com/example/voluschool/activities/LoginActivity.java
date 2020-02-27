package com.example.voluschool.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voluschool.R;
import com.example.voluschool.model.Login;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.LoginResponse;
import com.example.voluschool.responses.RegisterResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvDoRegister;
    private Button btnLogin;
    private EditText etEmailLogin, etPasswordLogin;
    private String email, password;
    MyApi myApi;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvDoRegister = findViewById(R.id.tv_open_regis);
        btnLogin = findViewById(R.id.btn_submit_login);

        Objects.requireNonNull(getSupportActionBar()).hide();

        tvDoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requestLogin();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void requestLogin(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        email = etEmailLogin.getText().toString();
        password = etPasswordLogin.getText().toString();

        myApi = ApiClient.getClient().create(MyApi.class);
        Login login = new Login(email, password);

        Call<LoginResponse> loginCall = myApi.login(login);

        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
