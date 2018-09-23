package com.hasanzian.farmer.ecom.demo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hasanzian.farmer.ecom.demo.R;
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
    @BindView(R.id.empty_item)TextView emptyView;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CartAdapter adapter = new CartAdapter(Utils.cartList,getActivity());
        mRecyclerView.setAdapter(adapter);

        if(adapter.getItemCount() == 0){
            emptyView.setVisibility(View.VISIBLE);
        }
        else {

            emptyView.setVisibility(View.GONE);
        }


        return view;
    }


}
