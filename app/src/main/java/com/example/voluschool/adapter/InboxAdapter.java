package com.example.voluschool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voluschool.R;
import com.example.voluschool.model.Inbox;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxCardViewHolder> {
    private ArrayList<Inbox> listInbox;

    public InboxAdapter(ArrayList<Inbox> list) {
        this.listInbox = list;
    }

    @NonNull
    @Override
    public InboxCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_inbox, parent, false);
        return new InboxCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxCardViewHolder holder, int position) {
        holder.bind(listInbox.get(position));
    }

    @Override
    public int getItemCount() {
        return listInbox.size();
    }

    class InboxCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;

        InboxCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_inbox_message);
        }

        void bind(Inbox inbox) {
            tvMessage.setText(inbox.getMessage());
        }
    }
}
