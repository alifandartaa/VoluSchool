package com.example.voluschool.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.voluschool.R;
import com.example.voluschool.model.User;
import com.example.voluschool.responses.RegisterResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity{

    private Button btnRegister, btnUpload;
    private String namaLengkap, email, password, confPass, noHp, noKtp;
    private EditText etNamaLengkap, etEmail, etPassw, etConfPassw, etNoHp, etNoKtp;
    MyApi myApi;
    private byte[] dataFoto;
    private String url_photo = "/DOWNLOADS/LINE";
    ProgressDialog progressDialog;
    private int id;
//    GalleryPhoto galleryPhoto;
    private ImageView ivPreviewKtp;

    final int CAMERA_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        Objects.requireNonNull(getSupportActionBar()).hide();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestRegister();
            }
        });

//        galleryPhoto = new GalleryPhoto(getApplicationContext());

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                takePicture();
            }
        });
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
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            Uri uriImage = data.getData() ;
            ivPreviewKtp.setImageBitmap(imageBitmap);

//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            dataFoto = baos.toByteArray();
//            url_photo = uriImage.toString();
        }
    }

//    public Uri getImageUri(Context inContext, Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.(inContext.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK) {
//            if (requestCode == GALLERY_REQUEST) {
//                assert data != null;
//                Uri uri = data.getData();
//                galleryPhoto.setPhotoUri(uri);
//                String photoPath = galleryPhoto.getPath();
//                try {
//                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
//                    ivPreviewKtp.setImageBitmap(bitmap);
//                } catch (FileNotFoundException e) {
//                    Toast.makeText(this, "Error Upload CV", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

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
        id = makeRandomNum();
        Log.d("tag" , "randomnum"+ id);
        namaLengkap = etNamaLengkap.getText().toString();
        email = etEmail.getText().toString();
        password = etPassw.getText().toString();
        confPass = etConfPassw.getText().toString();
        noHp = etNoHp.getText().toString();
        noKtp = etNoKtp.getText().toString();


        User user = new User(id, namaLengkap, email, password, confPass, noHp, noKtp, url_photo);
        myApi = ApiClient.getClient().create(MyApi.class);

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

    private int makeRandomNum() {
        int min = 1;
        int max = 100;
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }
}
