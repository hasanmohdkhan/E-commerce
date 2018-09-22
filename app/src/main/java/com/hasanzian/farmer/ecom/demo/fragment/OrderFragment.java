package com.hasanzian.farmer.ecom.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hasanzian.farmer.ecom.demo.R;
import com.hasanzian.farmer.ecom.demo.adaptor.ShopAdapter;
import com.hasanzian.farmer.ecom.demo.model.DataModels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<DataModels> list;

    public OrderFragment() {
        // Required empty public constructor
    }

    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new DataModels("Hybrid T13", "$ 21", R.drawable.ic_dashboard_black_24dp));
        list.add(new DataModels("Hybrid T16", "$ 94", R.drawable.ic_plant));


        ShopAdapter adapter = new ShopAdapter(list);
        mRecyclerView.setAdapter(adapter);

        return view;
    }

}
