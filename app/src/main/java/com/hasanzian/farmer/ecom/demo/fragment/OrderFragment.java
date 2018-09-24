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
import com.hasanzian.farmer.ecom.demo.adaptor.OrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_item)
    TextView itemEmptyView;

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

        OrderAdapter adapter = new OrderAdapter(Utils.orderedList, getActivity());
        mRecyclerView.setAdapter(adapter);

        if (adapter.getItemCount() == 0) {
            itemEmptyView.setVisibility(View.VISIBLE);
        } else {

            itemEmptyView.setVisibility(View.GONE);
        }


        return view;
    }

}
