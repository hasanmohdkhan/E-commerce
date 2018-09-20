package com.example.hasanzian.e_commerce;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

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
                    Toast.makeText( mContext, "createUserWithEmail:success " +user.getEmail() , Toast.LENGTH_SHORT).show();
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
}
