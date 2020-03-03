package com.example.voluschool.fragments;


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
import com.example.voluschool.adapter.DonaturAdapter;
import com.example.voluschool.model.Donatur;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonaturFragment extends Fragment {

    private ArrayList<Donatur> donaturs= new ArrayList<>();

    public DonaturFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donatur, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvDonatur = view.findViewById(R.id.rv_donatur);

        Donatur donatur1 = new Donatur("1", "Alif", 10000, "25 Februari 2020");
        Donatur donatur2 = new Donatur("2", "Eka", 20000, "26 Februari 2020");
        Donatur donatur3 = new Donatur("1", "Aldi", 30000, "27 Februari 2020");
        Donatur donatur4 = new Donatur("1", "Aldi", 30000, "27 Februari 2020");
        donaturs.add(donatur1);
        donaturs.add(donatur2);
        donaturs.add(donatur3);
        donaturs.add(donatur4);
        rvDonatur.setHasFixedSize(true);
        rvDonatur.setLayoutManager(new LinearLayoutManager(this.getContext()));
        DonaturAdapter donaturAdapter = new DonaturAdapter(donaturs);
        donaturAdapter.notifyDataSetChanged();
        rvDonatur.setAdapter(donaturAdapter);
    }
}
