package com.example.voluschool.fragments;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.voluschool.R;
import com.example.voluschool.model.PostDonation;
import com.example.voluschool.responses.DonationPostResponse;
import com.example.voluschool.retrofit.ApiClient;
import com.example.voluschool.retrofit.MyApi;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDonatPostFragment extends Fragment {

    private Button btnCreateDonasi;
    private Button btnUpload;
    private EditText etSekolah, etDeskripsi, etFasil, etTargetDana;
    String sekolah, deskripsi, fasil, target;
    private ImageView ivPreviewSchool;
    private ArrayList<PostDonation> postDonations = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    public MultipartBody.Part part;
    File finalfile;
    MyApi myApi;
    private RequestBody sekolah1, deskripsi1, fasil1, target1;

    final int CAMERA_REQUEST = 1;

    public CreateDonatPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_donat_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCreateDonasi = view.findViewById(R.id.btn_create_donat);
        btnUpload = view.findViewById(R.id.btn_upimg_don);
        etTargetDana = view.findViewById(R.id.et_crcost_school);
        ivPreviewSchool = view.findViewById(R.id.iv_img_scdonate);
        etSekolah = view.findViewById(R.id.et_crname_school);
        etDeskripsi = view.findViewById(R.id.et_crdesc_school);
        etFasil = view.findViewById(R.id.et_crfac_school);
        etTargetDana = view.findViewById(R.id.et_crcost_school);

        Context context = getActivity();
        sharedPreferences = context.getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        btnCreateDonasi.setOnClickListener(v -> {
            sekolah = etSekolah.getText().toString();
            deskripsi = etDeskripsi.getText().toString();
            fasil = etFasil.getText().toString();
            target = etTargetDana.getText().toString();

            sekolah1 = RequestBody.create(MediaType.parse("text/plain"), sekolah);
            deskripsi1 = RequestBody.create(MediaType.parse("text/plain"), deskripsi);
            target1 = RequestBody.create(MediaType.parse("text/plain"), target);

            myApi = ApiClient.getClient().create(MyApi.class);

            Call<DonationPostResponse> donateCall = myApi.donate(sekolah1, target1, deskripsi1, part);

            donateCall.enqueue(new Callback<DonationPostResponse>() {
                @Override
                public void onResponse(Call<DonationPostResponse> call, Response<DonationPostResponse> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<DonationPostResponse> call, Throwable t) {

                }
            });
        });

        btnUpload.setOnClickListener(v -> {
            takePicture();
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = Objects.requireNonNull(data).getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPreviewSchool.setImageBitmap(imageBitmap);

            Uri tempUri = getImageUri(Objects.requireNonNull(getActivity()).getApplicationContext(), Objects.requireNonNull(imageBitmap));

            finalfile = new File(getRealPathFromURI(tempUri));

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), finalfile);

            part = MultipartBody.Part.createFormData("myFile", finalfile.getName(), requestBody);
        }

    }

    private Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private String getRealPathFromURI(Uri uri) {
        String path = "";
        if (Objects.requireNonNull(getContext()).getContentResolver() != null) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                path = cursor.getString(idx);
                cursor.close();
            }
        }
        return path;
    }
}
