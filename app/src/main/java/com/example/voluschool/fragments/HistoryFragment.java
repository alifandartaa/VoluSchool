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
import com.example.voluschool.adapter.HistoryAdapter;
import com.example.voluschool.model.History;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    private ArrayList<History> histories = new ArrayList<>();

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvHistory = view.findViewById(R.id.rv_history);

        History history1 = new History("SD Mburing", "10000", "22 Februari 2020");
        History history2 = new History("SD Gadang", "20000", "23 Februari 2020");
        History history3 = new History("SD Mulyorejo", "30000", "24 Februari 2020");
        histories.add(history1);
        histories.add(history2);
        histories.add(history3);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        HistoryAdapter historyAdapter = new HistoryAdapter(histories);
        historyAdapter.notifyDataSetChanged();
        rvHistory.setAdapter(historyAdapter);
    }
}
