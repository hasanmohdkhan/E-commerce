package com.hasanzian.farmer.ecom.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.hasanzian.farmer.ecom.demo.R;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    @BindView(R.id.sign_in_button)
    Button button;

    @BindView(R.id.sign_in_email_tv)
    TextInputEditText mEmailText;

    @BindView(R.id.sign_in_password_tv)
    TextInputEditText mPasswordText;

    private static final int RC_SIGN_IN = 1712;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;

    private static final String TAG = LoginActivity.class.getSimpleName();
    private Helpers mHelpers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mHelpers = new Helpers(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mFirebaseAuth.getCurrentUser();
        if (user != null) {
            navigateToHome();

        }

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mEmailText = findViewById(R.id.sign_in_email_tv);
        mPasswordText = findViewById(R.id.sign_in_password_tv);


    }

    @OnClick (R.id.sign_in_button)
    public void submit(){
        doSignIn();
    }

    @OnClick(R.id.sign_in_register_tv)
    public  void register(){
        showSignUp();
    }


    private void showForgotPassword() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    private void showSignUp() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



    private void doSignIn() {
        String email = mEmailText.getText().toString();
        String password = mPasswordText.getText().toString();

        if (email.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.email_required_msg), getString(R.string.enter_email_msg)).show();
            return;
        }

        if (password.isEmpty()) {
            mHelpers.showAlertDialog(getString(R.string.password_required_msg), getString(R.string.enter_password_msg)).show();
            return;
        }
        loginWithFirebaseEmailPassword(email, password);
    }

    private void navigateToHome() {
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        int code = bundle.getInt(getString(R.string.KEY));
        if(code== 2){
            Intent intent = new Intent(this, SellerActivity.class);
            startActivity(intent);
            finish();
        }

        if(code ==1 ){
            Intent intent = new Intent(this, BuyerActivity.class);
            startActivity(intent);
            finish();
        }


    }


    private void loginWithFirebaseEmailPassword(String email, String password) {
        mHelpers.showProgressDialog(getString(R.string.loading_msg));
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mHelpers.hideProgressDialog();
                        if (task.isSuccessful()) {
                            mHelpers.showToast(getString(R.string.login_success));
                            onSignInSuccess();
                        } else {
                            Log.w("Sign in failed", task.getException().toString());
                            mHelpers.showAlertDialog(getString(R.string.error_message), task.getException().getMessage()).show();
                        }
                    }
                });
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential mCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mFirebaseAuth.signInWithCredential(mCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String email = account.getEmail();
                            String name = account.getDisplayName();
                            String userID = mFirebaseAuth.getCurrentUser().getUid();
                            boolean newUser = task.getResult().getAdditionalUserInfo().isNewUser();
                            addNewUser(email, name, "", userID, newUser);
                        } else {
                            Log.w(TAG, task.getException().toString());
                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }

    private void onSignInSuccess() {
        if (mFirebaseAuth.getCurrentUser() != null) {
            Log.v(TAG, "User successfully logged in.");
            Toast.makeText(this, "You are successfully signed in.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, SellerActivity.class);
            startActivity(intent);
        } else {
            Log.v(TAG, "User log-in failed.");
            Toast.makeText(this, "Invalid email or password!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addNewUser(String email, String name, String number, String userID, boolean newUser) {
        if (newUser) {
            UserData user = new UserData(name, email, number);
            DatabaseReference mDatabaseReference = mFirebaseDatabase.getReference();
            mDatabaseReference.child("users").child(userID).setValue(user)
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.v("Write Data", "New User Data stored successfully");
                                onSignInSuccess();
                            } else {
                                Log.w("FireBase Error", task.getException().toString());
                                Toast.makeText(LoginActivity.this, task.getException().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            onSignInSuccess();
        }
    }


    @OnClick(R.id.sign_in_forgot_pass_tv)
    public void resetPassword(){
        showForgotPassword();
    }

}
