package com.example.voluschool.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.voluschool.R;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.RegisterResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private Button btnRegister, btnUpload;
    private EditText etNamaLengkap, etEmail, etPassw, etConfPassw, etNoHp, etNoKtp;
    MyApi myApi;
    ProgressDialog progressDialog;
    private int id;
    private ImageView ivPreviewKtp;

    final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        Objects.requireNonNull(getSupportActionBar()).hide();

        btnRegister.setOnClickListener(v -> {
            if (!etPassw.getText().toString().equals(etConfPassw.getText().toString())) {
                Toast.makeText(RegisterActivity.this, getString(R.string.error_confpass), Toast.LENGTH_SHORT).show();
            } else {
//                    requestRegister();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });

        btnUpload.setOnClickListener(v -> takePicture());
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            Bitmap imageBitmap = (Bitmap) Objects.requireNonNull(extras).get("data");
            ivPreviewKtp.setImageBitmap(imageBitmap);
        }
    }

    private void init() {
        btnRegister = findViewById(R.id.btn_submit_register);
        btnUpload = findViewById(R.id.btn_upload_ktp_reg);
        etNamaLengkap = findViewById(R.id.et_nama_lengkap_register);
        etEmail = findViewById(R.id.et_email_register);
        etPassw = findViewById(R.id.et_password_register);
        etConfPassw = findViewById(R.id.et_conpass_register);
        etNoHp = findViewById(R.id.et_nohp_register);
        etNoKtp = findViewById(R.id.et_noktp_register);
        ivPreviewKtp = findViewById(R.id.iv_reg_imgktp);
    }

    private void requestRegister(){
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        makeRandomNum();
//        id = makeRandomNum();
        Log.d("tag" , "randomnum"+ id);
        String namaLengkap = etNamaLengkap.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassw.getText().toString();
        String noHp = etNoHp.getText().toString();
        String noKtp = etNoKtp.getText().toString();

        String url_photo = "/DOWNLOADS/LINE";
        User user = new User(4, namaLengkap, email, password, noHp, noKtp, url_photo);
        myApi = ApiClient.getClient().create(MyApi.class);

        Call<RegisterResponse> registerCall = myApi.register(user);

        registerCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NotNull Call<RegisterResponse> call, @NotNull Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, getString(R.string.success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<RegisterResponse> call, @NotNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeRandomNum() {
        int min = 1;
        int max = 100;
        Random r = new Random();
        r.nextInt(max - min + 1);
    }
}
