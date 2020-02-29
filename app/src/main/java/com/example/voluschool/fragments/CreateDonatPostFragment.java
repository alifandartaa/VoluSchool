package com.example.voluschool.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.voluschool.R;
import com.example.voluschool.model.PostDonation;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;

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

        btnCreateDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sekolah = etSekolah.getText().toString();
                deskripsi = etDeskripsi.getText().toString();
                fasil = etFasil.getText().toString();
                target = etTargetDana.getText().toString();
                int targetValue = Integer.parseInt(target);
                PostDonation postDonation1 = new PostDonation(sekolah, 0
                        , targetValue, fasil, "Alif Andarta Al Falah", R.drawable.img_sd_mburing);
                Snackbar.make(view, "Post Donasi Telah Dibuat", Snackbar.LENGTH_SHORT).show();
                postDonations.add(postDonation1);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(postDonation1);
                editor.putString("postdonation", json);
                editor.apply();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
                Toast.makeText(getActivity(), getString(R.string.upload_berhasil), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPreviewSchool.setImageBitmap(imageBitmap);

//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            dataFoto = baos.toByteArray();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = getActivity();
        sharedPreferences = context.getSharedPreferences(getString(R.string.shared_preference), Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("postdonation", "");
        PostDonation postDonation = gson.fromJson(json, PostDonation.class);
        postDonations.add(postDonation);
    }
}
