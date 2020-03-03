package com.example.voluschool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluschool.R;
import com.example.voluschool.model.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryCardViewHolder> {
    private ArrayList<History> listHistory;

    public HistoryAdapter(ArrayList<History> listHistory) {
        this.listHistory = listHistory;
    }

    @NonNull
    @Override
    public HistoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_history, parent, false);
        return new HistoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryCardViewHolder holder, int position) {
        holder.bind(listHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    class HistoryCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvSchool, tvTerkumpul, tvDate;

        HistoryCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSchool = itemView.findViewById(R.id.tv_history_donation);
            tvTerkumpul = itemView.findViewById(R.id.tv_history_cost);
            tvDate = itemView.findViewById(R.id.tv_history_date);
        }

        void bind(History history) {
            tvSchool.setText(history.getSchoolName());
            tvTerkumpul.append(String.valueOf(history.getCost()));
            tvDate.setText(history.getDate());
        }
    }
}
