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
import com.example.voluschool.adapter.InboxAdapter;
import com.example.voluschool.model.Inbox;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment extends Fragment {

    private ArrayList<Inbox> inboxes = new ArrayList<>();

    public InboxFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvInbox = view.findViewById(R.id.rv_inbox);

        Inbox inbox1 = new Inbox("Donasi kepada SD Mulyorejo tersalurkan pada tanggal 19 Februari 2019.");
        Inbox inbox2 = new Inbox("Pendaftaran volunteer anda pada SD Mulyorejo \n" +
                "diterima. Cek email untuk info lebih lanjut.");
        Inbox inbox3 = new Inbox("Donasi kepada SD Sidorahayu tersalurkan pada tanggal 10 Februari 2019.");
        Inbox inbox4 = new Inbox("Donasi kepada SDMburing tersalurkan pada tanggal 18 Januari 2019.");
        inboxes.add(inbox1);
        inboxes.add(inbox2);
        inboxes.add(inbox3);
        inboxes.add(inbox4);
        rvInbox.setHasFixedSize(true);
        rvInbox.setLayoutManager(new LinearLayoutManager(this.getContext()));
        InboxAdapter inboxAdapter= new InboxAdapter(inboxes);
        inboxAdapter.notifyDataSetChanged();
        rvInbox.setAdapter(inboxAdapter);
    }
}
