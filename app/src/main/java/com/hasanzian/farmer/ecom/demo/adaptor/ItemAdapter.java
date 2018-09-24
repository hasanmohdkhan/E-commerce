package com.hasanzian.farmer.ecom.demo.adaptor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hasanzian.farmer.ecom.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public  class ItemAdapter extends RecyclerView.ViewHolder
{
    @BindView(R.id.image_view)
    public ImageView mThumbnail;

    @BindView(R.id.title)
    public TextView mProductName;

    @BindView(R.id.order_total_price)
    public TextView mPrice;
    @BindView(R.id.btn)
    public Button button;

    public ItemAdapter(View view) {
        super(view);
        ButterKnife.bind(this, view);



    }





    }



