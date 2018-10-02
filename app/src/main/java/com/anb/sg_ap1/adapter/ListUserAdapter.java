package com.anb.sg_ap1.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anb.sg_ap1.R;
import com.anb.sg_ap1.model.User;

import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserVH>
{
    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    private ArrayList<User> mData;
    private final OnItemClickListener listener;

    public ListUserAdapter(ArrayList<User> mData, OnItemClickListener listener) {
        this.mData = mData;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListUserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cv_user, parent, false);
        return new ListUserVH(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListUserVH holder, int position) {
        holder.bind(mData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

class ListUserVH extends RecyclerView.ViewHolder{

    private TextView tvNama, tvUmur, tvGender;

    ListUserVH(View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tv_nama);
        tvUmur = itemView.findViewById(R.id.tv_umur);
        tvGender = itemView.findViewById(R.id.tv_gender);
    }

    @SuppressLint("SetTextI18n")
    public void bind(final User user, final ListUserAdapter.OnItemClickListener listener){
        tvNama.setText(user.nama);
        tvUmur.setText("Umur: " + Integer.toString(user.umur));
        tvGender.setText("Gender: " + user.gender);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(user);
            }
        });
    }
}
