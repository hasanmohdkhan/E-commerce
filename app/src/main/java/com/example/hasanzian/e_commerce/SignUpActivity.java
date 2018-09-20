package com.example.hasanzian.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);

    }
}
