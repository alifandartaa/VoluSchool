package com.example.voluschool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluschool.R;
import com.example.voluschool.model.Donatur;
import com.example.voluschool.model.PostDonation;

import java.util.ArrayList;

public class DonaturAdapter extends RecyclerView.Adapter<DonaturAdapter.DonaturCardViewHolder> {
    private ArrayList<Donatur> listDonatur = new ArrayList<>();

    public DonaturAdapter(ArrayList<Donatur> listDonatur) {
        this.listDonatur = listDonatur;
    }

    @NonNull
    @Override
    public DonaturCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_donatur, parent, false);
        return new DonaturCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonaturAdapter.DonaturCardViewHolder holder, int position) {
        holder.bind(listDonatur.get(position));
    }

    @Override
    public int getItemCount() {
        return listDonatur.size();
    }

    public class DonaturCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvSchool, tvCost, tvDate;
        public DonaturCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSchool = itemView.findViewById(R.id.tv_donatur_name);
            tvCost = itemView.findViewById(R.id.tv_donatur_cost);
            tvDate = itemView.findViewById(R.id.tv_donatur_date);
        }

        public void bind(Donatur donatur) {
            tvSchool.setText(donatur.getNama());
            tvCost.setText(String.valueOf(donatur.getDonationCost()));
            tvDate.setText(donatur.getDate());
        }
    }
}
