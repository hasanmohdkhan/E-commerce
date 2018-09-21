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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hasanzian.e_commerce.R;
import com.example.hasanzian.e_commerce.adaptor.ItemAdapter;
import com.example.hasanzian.e_commerce.model.DataModels;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StoreFragment extends Fragment {
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private List<DataModels> list;
    public DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<DataModels,ItemAdapter> mFirebaseAdapter;
    private LinearLayoutManager mLinearLayoutManager;

    //ProgressDialog progressDialog;
    @BindView(R.id.loading)
    ProgressBar progressBar;


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
        mLinearLayoutManager = new LinearLayoutManager(getContext());
      //  progressDialog = new ProgressDialog(getContext());

        // Firebase reference  here
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //Quary to narrow down data calls
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("order");

        FirebaseRecyclerOptions<DataModels> options = new FirebaseRecyclerOptions.Builder<DataModels>().setQuery(query,DataModels.class).build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<DataModels, ItemAdapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ItemAdapter holder, final int position, @NonNull final DataModels model) {

                if(model.getTitle() != null){
                    holder.mProductName.setText(model.getTitle());
                    //progressDialog.dismiss();
                    progressBar.setVisibility(View.GONE);

                }
                else {
                    holder.mProductName.setText("no data ");
                }
                if(model.getPrice() !=null){
                    holder.mPrice.setText(model.getPrice());
                }
                else {
                    holder.mPrice.setText("no Price ");
                }
                if(model.getDownloadUrl() != null){
                    Glide.with(getActivity())
                            .load(model.getDownloadUrl()).into(holder.mThumbnail);
                    }
                else {
                    Glide.with(getActivity())
                            .load(R.drawable.ic_plant).into(holder.mThumbnail);
                }
               holder.button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       Toast.makeText(getContext(),"P:" + model.getTitle() ,Toast.LENGTH_SHORT).show();
                   }
               });
                }

            @NonNull
            @Override
            public ItemAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
                 return new ItemAdapter(view);
            }
        };


        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mFirebaseAdapter);

        if(mFirebaseAdapter.getItemCount() == 0){
            progressBar.setVisibility(View.VISIBLE);
        }




        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }


}
