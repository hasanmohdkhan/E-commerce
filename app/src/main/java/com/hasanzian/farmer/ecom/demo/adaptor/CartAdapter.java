package com.hasanzian.farmer.ecom.demo.adaptor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hasanzian.farmer.ecom.demo.R;
import com.hasanzian.farmer.ecom.demo.model.CartModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.myViewHolder> {
    private List<CartModel> mList;
    private Context mContext;

    public CartAdapter(List<CartModel> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int i) {
        holder.mProductName.setText(mList.get(i).getTitle());
        holder.mPrice.setText(mList.get(i).getPrice());
        Glide.with(mContext).load(mList.get(i).getDownloadUrl()).into(holder.mThumbnail);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view)
        ImageView mThumbnail;
        @BindView(R.id.title)
        TextView mProductName;

        @BindView(R.id.price)
        TextView mPrice;

        myViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

