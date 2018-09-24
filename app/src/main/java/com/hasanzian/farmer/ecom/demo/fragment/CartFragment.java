package com.hasanzian.farmer.ecom.demo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanzian.farmer.ecom.demo.PlaceOrderActivity;
import com.hasanzian.farmer.ecom.demo.R;
import com.hasanzian.farmer.ecom.demo.RecyclerTouchListener;
import com.hasanzian.farmer.ecom.demo.Utils;
import com.hasanzian.farmer.ecom.demo.adaptor.CartAdapter;
import com.hasanzian.farmer.ecom.demo.model.CartModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<CartModel> list;
    @BindView(R.id.empty_item)
    TextView emptyView;

    public CartFragment() {
        // Required empty public constructor
    }

    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CartAdapter adapter = new CartAdapter(Utils.removeDuplicate(), getActivity());
        mRecyclerView.setAdapter(adapter);


        if (adapter.getItemCount() == 0) {
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
        }

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), mRecyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // startActivity(new Intent(getActivity(), PlaceOrderActivity.class));
                Bundle orderSummary = new Bundle();
                orderSummary.putString(getString(R.string.title_key), Utils.cartList.get(position).getTitle());
                orderSummary.putString(getString(R.string.price_key),Utils.cartList.get(position).getPrice());
                orderSummary.putString(getString(R.string.image_key),Utils.cartList.get(position).getDownloadUrl());
                Intent orderIntent = new Intent(getContext(), PlaceOrderActivity.class);
                orderIntent.putExtras(orderSummary);
                startActivity(orderIntent);


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }


}
