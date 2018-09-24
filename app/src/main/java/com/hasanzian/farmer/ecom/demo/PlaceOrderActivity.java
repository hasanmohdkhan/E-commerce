package com.hasanzian.farmer.ecom.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hasanzian.farmer.ecom.demo.model.CartModel;

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

    @BindView(R.id.plus)
    Button plus;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id.current_total)
    TextView currentTotalTextView;
    @BindView(R.id.current_quantity)
    TextView currentQuantityTextView;

    int quantityCounter = 1;
    int totalPriceCounter;
    String price;
    String imageURL;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        ButterKnife.bind(this);

        Intent intent = getIntent();
         imageURL = intent.getStringExtra(getString(R.string.image_key));
         title = intent.getStringExtra(getString(R.string.title_key));
         price = intent.getStringExtra(getString(R.string.price_key));

        totalPriceCounter = Integer.parseInt(price);

        orderTitle.setText(title);
        orderPrice.setText(getString(R.string.rupee_sign) + " " + price);
        currentQuantityTextView.setText("" + quantityCounter);
        currentTotalTextView.setText(getString(R.string.rupee_sign_total)+" " + totalPriceCounter);

        Glide.with(this).load(imageURL).into(orderImage);

        orderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t,q,p;
                t = orderTitle.getText().toString();
                p = currentTotalTextView.getText().toString();
                q = currentQuantityTextView.getText().toString();
                Toast.makeText(getApplicationContext(), "Order Place", Toast.LENGTH_SHORT).show();
                Utils.orderedList.add(new CartModel(t,p,"Total Quantity: "+q,imageURL));
                }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantityCounter += 1;
                totalPriceCounter=totalPriceCounter*quantityCounter;
                currentQuantityTextView.setText("" + quantityCounter);
                currentTotalTextView.setText(getString(R.string.rupee_sign_total)+ " " +  calculatePrice(quantityCounter,Integer.parseInt(price)));
                Toast.makeText(getApplicationContext(), "" + quantityCounter, Toast.LENGTH_SHORT).show();
                }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantityCounter > 1) {
                    quantityCounter -= 1;
                    currentTotalTextView.setText(getString(R.string.rupee_sign_total)+ " " +  calculatePrice(quantityCounter,Integer.parseInt(price)));
                    currentQuantityTextView.setText("" + quantityCounter);
                    Toast.makeText(getApplicationContext(), "" + quantityCounter, Toast.LENGTH_SHORT).show();

                }
                }
        });


    }


    /**
     * Calculates the price of the order.
     *  @param quantity is the number of cups of coffee ordered
     * @param pricePerItem one item cost
     * @return price
     */
    private int calculatePrice(int quantity, int pricePerItem) {
        return quantity * pricePerItem;
    }
}
