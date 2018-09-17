package com.example.hasanzian.e_commerce.fragment;

import android.os.Bundle;
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


public class TrackerFragment extends Fragment {
    @BindView(R.id.recycler) RecyclerView mRecyclerView;
    private List<DataModels> list;

    public TrackerFragment() {
        // Required empty public constructor
    }

    public static TrackerFragment newInstance(String param1, String param2) {
        TrackerFragment fragment = new TrackerFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new DataModels("Hybrid T16", "$ 94", R.drawable.ic_plant));


        ShopAdapter adapter = new ShopAdapter(list);
        mRecyclerView.setAdapter(adapter);

        return  view;
    }

}
