package com.example.voluschool.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voluschool.R;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateDonatPostFragment extends Fragment {

    private Button btnCreateDonasi;
    private Button btnUpload;
    private EditText etTargetDana;

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

        btnCreateDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Post Donasi Telah Dibuat", Snackbar.LENGTH_SHORT).show();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.upload_berhasil), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
