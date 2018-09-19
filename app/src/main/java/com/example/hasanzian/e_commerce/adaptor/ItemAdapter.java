package com.example.hasanzian.e_commerce.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hasanzian.e_commerce.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public  class ItemAdapter extends RecyclerView.ViewHolder
{
    @BindView(R.id.image_view)
    public ImageView mThumbnail;

    @BindView(R.id.title)
    public TextView mProductName;

    @BindView(R.id.price)
    public TextView mPrice;

    public ItemAdapter(View v) {
        super(v);
        ButterKnife.bind(this, v);

    }
}


