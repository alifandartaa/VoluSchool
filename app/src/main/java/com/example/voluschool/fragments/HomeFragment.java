package com.example.voluschool.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.voluschool.R;
import com.example.voluschool.activities.CreatePostActivity;
import com.example.voluschool.activities.DetailAplikasiActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DonateFragment donateFragment = new DonateFragment();
        VolunteerFragment volunteerFragment = new VolunteerFragment();
        fragmentTransaction.replace(R.id.fg_donation, donateFragment);
        fragmentTransaction.replace(R.id.fg_volunteer, volunteerFragment);
        fragmentTransaction.commit();

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_create_post);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), CreatePostActivity.class);
            startActivity(intent);
        });

        CardView cvDetailApp = view.findViewById(R.id.cv_detail_app);
        cvDetailApp.setOnClickListener(v -> {
            Intent detailIntent = new Intent(getActivity(), DetailAplikasiActivity.class);
            startActivity(detailIntent);
        });
    }
}
