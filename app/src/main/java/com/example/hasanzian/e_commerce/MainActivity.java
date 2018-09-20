package com.example.hasanzian.e_commerce;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hasanzian.e_commerce.fragment.CartFragment;
import com.example.hasanzian.e_commerce.fragment.OrderFragment;
import com.example.hasanzian.e_commerce.fragment.ProfileFragment;
import com.example.hasanzian.e_commerce.fragment.StoreFragment;
import com.example.hasanzian.e_commerce.fragment.TrackerFragment;

/*
*  MainActivity Used to initialized bottom navigation
*  it set default layout to shopFragment
*
*  TODO rename it
*
* */

public class MainActivity extends AppCompatActivity {
   private ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {

                case R.id.navigation_home:
                    actionBar.setTitle(R.string.title_shop);
                    fragment = new StoreFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_cart:
                    actionBar.setTitle(R.string.title_cart);
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    actionBar.setTitle(R.string.title_profile);
                   fragment = new ProfileFragment();
                   loadFragment(fragment);
                    return true;

                case  R.id.navigation_map_maker:
                    actionBar.setTitle(R.string.title_map);
                    fragment =new TrackerFragment();
                    loadFragment(fragment);
                    return true;
                case  R.id.navigation_dashboard:
                    actionBar.setTitle(R.string.title_order);
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
     FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // load the store fragment by default
        actionBar.setTitle("Shop");
        loadFragment(new StoreFragment());
    }

}
