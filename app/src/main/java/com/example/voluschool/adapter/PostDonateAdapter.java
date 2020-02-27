package com.example.voluschool.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.voluschool.R;
import com.example.voluschool.model.PostDonation;

import java.util.ArrayList;

public class PostDonateAdapter extends RecyclerView.Adapter<PostDonateAdapter.DonateCardViewHolder> {
    private ArrayList<PostDonation> listPostDonation = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public PostDonateAdapter(ArrayList<PostDonation> listPostDonation) {
        this.listPostDonation = listPostDonation;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public DonateCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_donation, parent, false);
        return new DonateCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostDonateAdapter.DonateCardViewHolder holder, final int position) {
        holder.bind(listPostDonation.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPostDonation.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPostDonation.size();
    }

    public class DonateCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvSchool, tvTerkumpul, tvCost;
        ImageView ivSchool;
        public DonateCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSchool = itemView.findViewById(R.id.tv_school_donate);
            tvTerkumpul = itemView.findViewById(R.id.tv_saved);
            tvCost = itemView.findViewById(R.id.tv_cost_donate);
            ivSchool = itemView.findViewById(R.id.iv_img_donate);
        }

        void bind(PostDonation postDonation) {
            tvSchool.setText(postDonation.getSchoolName());
            tvCost.append(String.valueOf(postDonation.getDonationCost()));
            Glide.with(itemView)
                    .load(postDonation.getSchoolImage())
                    .apply(new RequestOptions().override(150, 100))
                    .into(ivSchool);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(PostDonation data);
    }
}
