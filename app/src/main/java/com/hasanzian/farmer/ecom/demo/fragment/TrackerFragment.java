package com.hasanzian.farmer.ecom.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hasanzian.farmer.ecom.demo.R;

import butterknife.ButterKnife;


public class TrackerFragment extends Fragment {
//    @BindView(R.id.recycler) RecyclerView mRecyclerView;
//    private List<DataModels> list;
//
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
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        list = new ArrayList<>();
//        list.add(new DataModels("Hybrid T16", "$ 94", R.drawable.ic_plant));
//
//
//        ShopAdapter adapter = new ShopAdapter(list);
//        mRecyclerView.setAdapter(adapter);

        return  view;
    }

}
