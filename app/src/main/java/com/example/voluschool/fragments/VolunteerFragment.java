package com.example.voluschool.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.voluschool.R;
import com.example.voluschool.activities.DetailDonationActivity;
import com.example.voluschool.activities.DetailVolunteerActivity;
import com.example.voluschool.adapter.PostVolunteerAdapter;
import com.example.voluschool.model.PostVolunteer;

import java.util.ArrayList;

import static com.example.voluschool.activities.DetailDonationActivity.EXTRA_DONATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class VolunteerFragment extends Fragment {

    private ProgressBar progressBar;
    private ArrayList<PostVolunteer> postVolunteers = new ArrayList<>();

    public VolunteerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volunteer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        progressBar = view.findViewById(R.id.pb_donate);
        RecyclerView rvVolunteer = view.findViewById(R.id.rv_volunteer);

        PostVolunteer postVolunteer1 = new PostVolunteer("1","SD Tulusayu", 10, "Tenaga kerja guru di sekolah ini sangatlah minim. Hal tersebut dikarenakan kurang akses untuk menuju tempat tersebut sangat sulit", "BCC", R.drawable.img_sd_tulusayu );
        PostVolunteer postVolunteer2 = new PostVolunteer("2", "SD Bakalan", 5, "Tenaga kerja guru di sekolah ini sangatlah minim. Hal tersebut dikarenakan kurang akses untuk menuju tempat tersebut sangat sulit", "BCC", R.drawable.img_sd_bakalan);
        PostVolunteer postVolunteer3 = new PostVolunteer("3", "SD Bandulan", 7, "Tenaga kerja guru di sekolah ini sangatlah minim. Hal tersebut dikarenakan kurang akses untuk menuju tempat tersebut sangat sulit", "BCC", R.drawable.img_sd_bandulan);
        postVolunteers.add(postVolunteer1);
        postVolunteers.add(postVolunteer2);
        postVolunteers.add(postVolunteer3);
        rvVolunteer.setHasFixedSize(true);
        LinearLayoutManager horizontalLayout = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvVolunteer.setLayoutManager(horizontalLayout);
        PostVolunteerAdapter postVolunteerAdapter = new PostVolunteerAdapter(postVolunteers);
        postVolunteerAdapter.notifyDataSetChanged();
        rvVolunteer.setAdapter(postVolunteerAdapter);
        postVolunteerAdapter.setOnItemClickCallback(new PostVolunteerAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(PostVolunteer data) {
                showSelectedData(data);
            }
        });
    }

    private void showSelectedData(PostVolunteer postVolunteer) {
        Intent intent = new Intent(getActivity(), DetailVolunteerActivity.class);
        intent.putExtra(EXTRA_DONATION, postVolunteer);
        startActivity(intent);
    }
}
