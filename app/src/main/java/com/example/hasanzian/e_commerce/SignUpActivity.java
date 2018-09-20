package com.example.hasanzian.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.email_sign_up)
    EditText email;

    @BindView(R.id.password_new_sign_up)
    EditText passwordNew;

    @BindView(R.id.password_reenter_sign_up)
    EditText passwordReEnter;

    String emailStr;
    String password, passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //initialized Firebase Authorization
        mAuth = FirebaseAuth.getInstance();
        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);

    }

    @OnClick(R.id.create_account_sign_up)
    public void signUp() {
        emailStr = email.getText().toString();
        password = passwordNew.getText().toString();
        passwordConfirm = passwordReEnter.getText().toString();

        if (password.equals(passwordConfirm)) {
            Utils.createAccount(mAuth, this, emailStr, password);
            Toast.makeText(this, "Password match", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Password is not match", Toast.LENGTH_SHORT).show();
        }
    }
}



