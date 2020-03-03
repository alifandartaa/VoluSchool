package com.example.voluschool.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluschool.R;
import com.example.voluschool.activities.DetailDonationActivity;
import com.example.voluschool.adapter.PostDonateAdapter;
import com.example.voluschool.model.PostDonation;

import java.util.ArrayList;

import static com.example.voluschool.activities.DetailDonationActivity.EXTRA_DONATION;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {

    private ArrayList<PostDonation> postDonations = new ArrayList<>();

    public DonateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvDonate = view.findViewById(R.id.rv_donate);

        PostDonation postDonation1 = new PostDonation("SD Mburing", 2000000
                , 2000000, "Sekolah ini letaknya berada di pelosok desa. Mungkin perlu 1 jam untuk akses ke tempat nya yang berjarak kurang lebih 10 km. Sekolah ini secara fisik sudah lama dan sebaiknya ada renovasi agar lebih layak dan memadai", "BCC", R.drawable.img_sd_mburing);
        PostDonation postDonation2 = new PostDonation("SD Gadang", 2000000
                , 3000000, "Sekolah ini letaknya berada di pelosok desa. Mungkin perlu 1 jam untuk akses ke tempat nya yang berjarak kurang lebih 10 km. Sekolah ini secara fisik sudah lama dan sebaiknya ada renovasi agar lebih layak dan memadai", "BCC", R.drawable.img_sd_gadang);
        PostDonation postDonation3 = new PostDonation("SD Mulyorejo", 3000000
                , 4000000, "Sekolah ini letaknya berada di pelosok desa. Mungkin perlu 1 jam untuk akses ke tempat nya yang berjarak kurang lebih 10 km. Sekolah ini secara fisik sudah lama dan sebaiknya ada renovasi agar lebih layak dan memadai", "BCC", R.drawable.img_sd_mulyorejo);
        postDonations.add(postDonation1);
        postDonations.add(postDonation2);
        postDonations.add(postDonation3);
        rvDonate.setHasFixedSize(true);
        rvDonate.setLayoutManager(new LinearLayoutManager(this.getContext()));
        PostDonateAdapter postDonateAdapter = new PostDonateAdapter(postDonations);
        postDonateAdapter.notifyDataSetChanged();
        rvDonate.setAdapter(postDonateAdapter);
        postDonateAdapter.setOnItemClickCallback(this::showSelectedData);
    }

    private void showSelectedData(PostDonation postDonation) {
        Intent intent = new Intent(getActivity(), DetailDonationActivity.class);
        intent.putExtra(EXTRA_DONATION, postDonation);
        startActivity(intent);
    }
}
