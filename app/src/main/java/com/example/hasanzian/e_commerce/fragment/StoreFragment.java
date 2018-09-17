package com.example.hasanzian.e_commerce.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hasanzian.e_commerce.DataModels;
import com.example.hasanzian.e_commerce.R;
import com.example.hasanzian.e_commerce.adaptor.ShopAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StoreFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<DataModels> list;

    public StoreFragment() {
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
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new DataModels("Hybrid T12", "$ 24", R.drawable.ic_home_black_24dp));
        list.add(new DataModels("Hybrid T13", "$ 21", R.drawable.ic_dashboard_black_24dp));
        list.add(new DataModels("Hybrid T14", "$ 26", R.drawable.ic_home_black_24dp));
        list.add(new DataModels("Hybrid T15", "$ 64", R.drawable.account));
        list.add(new DataModels("Hybrid T16", "$ 94", R.drawable.ic_plant));


        ShopAdapter adapter = new ShopAdapter(list);
        mRecyclerView.setAdapter(adapter);
        return view;

    }


}
