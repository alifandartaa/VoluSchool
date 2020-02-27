package com.example.voluschool.adapter;

import android.graphics.Movie;
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
    private ArrayList<Inbox> listInbox = new ArrayList<>();
//    private OnItemClickCallback onItemClickCallback;

    public InboxAdapter(ArrayList<Inbox> list) {
        this.listInbox = list;
    }
//    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback;
//    }

//    public void setData(ArrayList<Inbox> items) {
//        listInbox.clear();
//        listInbox.addAll(items);
//        notifyDataSetChanged();
//    }

//    public void clearData(ArrayList<Movie> items){
//        listMovie.clear();
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public InboxCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_inbox, parent, false);
        return new InboxCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxAdapter.InboxCardViewHolder holder, int position) {
        holder.bind(listInbox.get(position));

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listInbox.size();
    }

    class InboxCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvMessage;
        public InboxCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.tv_inbox_message);
        }

        void bind(Inbox inbox) {
            tvMessage.setText(inbox.getMessage());
        }
    }
}
