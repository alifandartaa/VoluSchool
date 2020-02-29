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
import com.example.voluschool.model.PostVolunteer;

import java.util.ArrayList;

public class PostVolunteerAdapter extends RecyclerView.Adapter<PostVolunteerAdapter.VolunteerCardViewHolder> {
    private ArrayList<PostVolunteer> listPostVolunteer = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public PostVolunteerAdapter(ArrayList<PostVolunteer> listPostVolunteer) {
        this.listPostVolunteer = listPostVolunteer;
    }

    @NonNull
    @Override
    public VolunteerCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_volunteer, parent, false);
        return new VolunteerCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PostVolunteerAdapter.VolunteerCardViewHolder holder, int position) {
        holder.bind(listPostVolunteer.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPostVolunteer.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPostVolunteer.size();
    }

    public class VolunteerCardViewHolder extends RecyclerView.ViewHolder {
        TextView tvSchool, tvPenuh;
        ImageView ivSchool;

        public VolunteerCardViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSchool = itemView.findViewById(R.id.tv_school_volunteer);
            ivSchool = itemView.findViewById(R.id.iv_img_volunteer);
            tvPenuh = itemView.findViewById(R.id.tv_volu_penuh);
        }

        public void bind(PostVolunteer postVolunteer) {
            tvSchool.setText(postVolunteer.getSchoolName());
            if(postVolunteer.getRegisteredPeople() == postVolunteer.getTotalPeople()){
                tvPenuh.setVisibility(View.VISIBLE);
            }

            Glide.with(itemView)
                    .load(postVolunteer.getSchoolImage())
                    .apply(new RequestOptions().override(150, 100))
                    .into(ivSchool);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(PostVolunteer data);
    }
}
