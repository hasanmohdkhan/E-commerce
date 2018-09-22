package com.hasanzian.farmer.ecom.demo;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.hasanzian.farmer.ecom.demo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public final class Utils {

 public static final String TAG = "Utils";

    /**
     * Create a private constructor because no one should ever create a {@link Utils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name Utils (and an object instance of Utils is not needed).
     */
    private Utils() {
    }


    public static void createAccount(final FirebaseAuth mAuth, final Context mContext,final String email, final String password) {

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Activity) mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText( mContext, "createUserWithEmail:success " +user.getEmail().toString() , Toast.LENGTH_SHORT).show();
                   // updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(mContext, "Authentication failed.", Toast.LENGTH_SHORT).show();
                  //  updateUI(null);
                }

                // [START_EXCLUDE]
                //hideProgressDialog();
                // [END_EXCLUDE]
            }
        });
        // [END create_user_with_email]



    }

    /**
     * To get the custom font PProduct Sans Bold
     *
     * @param mContext context of app
     * @return custom font location of Product Sans Bold
     */
    public static Typeface headingFont(Context mContext) {
        AssetManager assetManager = mContext.getApplicationContext().getAssets();
        return Typeface.createFromAsset(assetManager, "fonts/Product Sans Bold.ttf");
    }

    /**
     * Glide place holder setup
     *
     * @return RequestOptions
     */
    public static RequestOptions requestOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_launcher_foreground);
        requestOptions.error(R.color.colorAccent);
        return requestOptions;
    }

}
