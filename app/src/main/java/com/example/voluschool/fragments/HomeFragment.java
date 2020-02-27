package com.example.voluschool.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.voluschool.R;
import com.example.voluschool.activities.CreatePostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FloatingActionButton floatingActionButton;


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

        fragmentManager = getParentFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        DonateFragment donateFragment = new DonateFragment();
        VolunteerFragment volunteerFragment = new VolunteerFragment();
        fragmentTransaction.replace(R.id.fg_donation, donateFragment);
        fragmentTransaction.replace(R.id.fg_volunteer, volunteerFragment);
        fragmentTransaction.commit();

        floatingActionButton = view.findViewById(R.id.fab_create_post);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreatePostActivity.class);
                startActivity(intent);
            }
        });
    }
}
