package com.hasanzian.farmer.ecom.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.hasanzian.farmer.ecom.demo.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hasanzian.farmer.ecom.demo.fragment.CartFragment;
import com.hasanzian.farmer.ecom.demo.fragment.OrderFragment;
import com.hasanzian.farmer.ecom.demo.fragment.StoreFragment;
import com.hasanzian.farmer.ecom.demo.fragment.TrackerFragment;

public class BuyerActivity extends AppCompatActivity {

    private ActionBar actionBar;
    FirebaseAuth mFirebaseAuth = null;
    FirebaseUser mFirebaseUser = null;

    private TextView mTextMessage;

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
                    actionBar.setTitle(R.string.title_upload);
                    fragment = new CartFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
//                    actionBar.setTitle(R.string.title_add_product);
//                    fragment = new ProfileFragment();
//                    loadFragment(fragment);
                    startActivity(new Intent(getApplicationContext(),ManageAccountActivity.class));
                    return true;

                case R.id.navigation_map_maker:
                    actionBar.setTitle(R.string.title_map);
                    fragment = new TrackerFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    actionBar.setTitle(R.string.title_order);
                    fragment = new OrderFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // load the store fragment by default
        actionBar.setTitle("Shop");
        loadFragment(new StoreFragment());
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sign_out, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.log_out:
                if (mFirebaseUser != null) {
                    mFirebaseAuth.signOut();
                }
                finish();
               // startActivity(new Intent(this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
