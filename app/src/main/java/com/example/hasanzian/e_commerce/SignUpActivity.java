package com.example.hasanzian.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.sign_up_email_tv)
    TextInputEditText mEmailText;
    @BindView(R.id.sign_up_name_tv)
    TextInputEditText mNameText;
    @BindView(R.id.sign_up_phone_tv)
    TextInputEditText mPhoneText;
    @BindView(R.id.sign_up_password_tv)
    TextInputEditText mPasswordText;

    private Helpers mHelpers;
    private FirebaseAuth mFirebaseAuth;

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        mHelpers = new Helpers(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    @OnClick(R.id.sign_up_button)
    public void butttonOne() {
        doSignUp();
    }

    @OnClick(R.id.sign_up_login_tv)
    public void butttonTwo() {
        showSignIn();
    }

    private void showSignIn() {
        finish();
    }

    private void doSignUp() {
        String name = mNameText.getText().toString();
        String email = mEmailText.getText().toString();
        String phone = mPhoneText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (name.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.name_required_msg), getString(R.string.enter_name_msg)).show();
            return;
        }
        if (email.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.email_required_msg), getString(R.string.enter_email_msg)).show();
            return;
        }
        if (phone.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.phone_required_msg), getString(R.string.enter_phone_msg)).show();
            if (phone.length() != 10) {
                mHelpers.showAlertDialog(getString(R.string.valid_phone_required_msg), getString(R.string.enter_valid_phone_msg)).show();
            }
            return;
        }
        if (password.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.password_required_msg), getString(R.string.enter_password_msg)).show();
            return;
        }

        signUp(email, password, name, phone);
    }

    private void signUp(final String email, final String password, final String name, final String number) {
        mHelpers.showProgressDialog(getString(R.string.creating_account));
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mHelpers.hideProgressDialog();
                if (task.isSuccessful()) {
                    Log.v(TAG, "New User Crated successfully");
                    String userID = mFirebaseAuth.getCurrentUser().getUid();
                    addNewUser(email, name, number, userID);
                } else {
                    Log.w(TAG, task.getException().toString());
                    mHelpers.showAlertDialog(getString(R.string.error_message), task.getException().getMessage()).show();
                }
            }
        });
    }

    private void addNewUser(String email, String name, String number, String userID) {
        UserData user = new UserData(name, email, number);
        DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
        mDatabaseReference.child("users").child(userID).setValue(user).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.v(TAG, "New User Data stored successfully");
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    mHelpers.showAlertDialog(getString(R.string.error_message), task.getException().getMessage()).show();
                    Log.w(TAG, task.getException().toString());
                }
            }
        });
    }
}




