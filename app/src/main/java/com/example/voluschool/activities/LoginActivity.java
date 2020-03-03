package com.example.voluschool.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voluschool.R;
import com.example.voluschool.model.Login;
import com.example.voluschool.responses.LoginResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;
import com.example.voluschool.utils.AppPreference;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvDoRegister;
    private Button btnLogin;
    private EditText etEmailLogin, etPasswordLogin;
    MyApi myApi;
    ProgressDialog progressDialog;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appPreference = new AppPreference(LoginActivity.this);
        init();
        Objects.requireNonNull(getSupportActionBar()).hide();

        tvDoRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
        btnLogin.setOnClickListener(v -> {
            if (etEmailLogin.getText().toString().isEmpty() || etPasswordLogin.getText().toString().isEmpty()) {
                Toast.makeText(LoginActivity.this, getString(R.string.field_cannot_blank), Toast.LENGTH_SHORT).show();
                btnLogin.setEnabled(true);
            } else {
                requestLogin();
            }
        });
    }

    private void init() {
        etEmailLogin = findViewById(R.id.et_email_login);
        etPasswordLogin = findViewById(R.id.et_passwd_login);
        tvDoRegister = findViewById(R.id.tv_open_regis);
        btnLogin = findViewById(R.id.btn_submit_login);
    }

    private void requestLogin(){
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        String email = etEmailLogin.getText().toString();
        String password = etPasswordLogin.getText().toString();

        myApi = ApiClient.getClient().create(MyApi.class);
        Login login = new Login(email, password);

        Call<LoginResponse> loginCall = myApi.login(login);


        loginCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    String token = Objects.requireNonNull(response.body()).getToken();
                    Toast.makeText(LoginActivity.this, token, Toast.LENGTH_SHORT).show();
                    appPreference.saveToken(token);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (appPreference.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
