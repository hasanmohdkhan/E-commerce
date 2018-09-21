package com.example.hasanzian.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceOrderActivity extends AppCompatActivity {

    @BindView(R.id.order_image)
    ImageView orderImage;

    @BindView(R.id.order_title)
    TextView orderTitle;

    @BindView(R.id.order_price)
    TextView orderPrice;

    @BindView(R.id.order_confirm_btn)
    Button orderConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String imageURL = intent.getStringExtra(getString(R.string.image_key));
        String title = intent.getStringExtra(getString(R.string.title_key));
        String price = intent.getStringExtra(getString(R.string.price_key));

        orderTitle.setText(title);
        orderPrice.setText(price);

        Glide.with(this)
                .load(imageURL).into(orderImage);

        orderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Order Place",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
