package com.example.hasanzian.e_commerce.adaptor;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasanzian.e_commerce.model.DataModels;
import com.example.hasanzian.e_commerce.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.myViewHolder>{
    List<DataModels> mList;

    public ShopAdapter(List<DataModels> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
               holder.mProductName.setText(mList.get(i).getTitle());
               holder.mPrice.setText(mList.get(i).getPrice());
               holder.mThumbnail.setImageResource(mList.get(i).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder{
         @BindView(R.id.image_view)
        ImageView mThumbnail;
         @BindView(R.id.title)
        TextView mProductName;

         @BindView(R.id.price)
        TextView mPrice;

        public myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
