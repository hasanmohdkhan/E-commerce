package com.example.hasanzian.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseUserCategoryActivity extends AppCompatActivity {

    @BindView(R.id.buyer_image)
    ImageView buyer;

    @BindView(R.id.seller_image)
    ImageView seller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user_category);
        ButterKnife.bind(this);

        seller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sellerIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(sellerIntent);
            }
        });

        buyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent buyerIntent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(buyerIntent);
            }
        });


        }

    }
