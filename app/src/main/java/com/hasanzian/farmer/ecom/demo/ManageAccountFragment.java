package com.hasanzian.farmer.ecom.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class ManageAccountFragment extends Fragment {

    TextInputEditText mNameInputEditText = null, mEmailInputEditText = null, mPhoneInputEditText = null;
    FirebaseAuth mFirebaseAuth = null;
    FirebaseUser mFirebaseUser = null;
    FirebaseDatabase mFirebaseDatabase = null;
    DatabaseReference mDatabaseReference = null;
    Button detailsBtn,passBtn;


    public ManageAccountFragment() {
        // Required empty public constructor
    }

    public static ManageAccountFragment newInstance(String param1, String param2) {
        ManageAccountFragment fragment = new ManageAccountFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage_account, container, false);

        mNameInputEditText = view.findViewById(R.id.account_update_full_name);
        mEmailInputEditText = view.findViewById(R.id.account_update_email);
        mPhoneInputEditText = view.findViewById(R.id.account_update_phone);
        detailsBtn = view.findViewById(R.id.update_button);
        passBtn = view.findViewById(R.id.change_password_button);

        detailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Update Complete ",Toast.LENGTH_SHORT).show();
            }
        });


        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Update Complete ",Toast.LENGTH_SHORT).show();
            }
        });


//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseUser = mFirebaseAuth.getCurrentUser();
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mDatabaseReference = mFirebaseDatabase.getReference().child("users");
//
//        mDatabaseReference.child(mFirebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                UserData userData = dataSnapshot.getValue(UserData.class);
//                if (userData != null) {
//                    mNameInputEditText.setText(userData.getName());
//                    mEmailInputEditText.setText(userData.getEmail());
//                    mPhoneInputEditText.setText(userData.getNumber());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        return view;
    }

    public void updateAccount(View view) {
        OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), "Account Update Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Update Failed", task.getException() + "");
                    Toast.makeText(getContext(), "Account Update Failed", Toast.LENGTH_SHORT).show();
                }
            }
        };
        String name = ((EditText) (view.findViewById(R.id.account_update_full_name))).getText().toString();
        String email = ((EditText) (view.findViewById(R.id.account_update_email))).getText().toString();
        String phone = ((EditText) (view.findViewById(R.id.account_update_phone))).getText().toString();
        String UID = mFirebaseUser.getUid();
        DatabaseReference reference = mDatabaseReference.child(UID);
        Map<String, Object> updates = new HashMap<>();
        if (!name.isEmpty()) {
            updates.put("/name", name);
        }
        if (!email.isEmpty()) {
            mFirebaseUser.updateEmail(email).addOnCompleteListener(getActivity(), listener);
            updates.put("/email", email);
        }
        if (!phone.isEmpty()) {
            updates.put("/number", phone);
        }
        reference.updateChildren(updates).addOnCompleteListener(getActivity(), listener);
        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(intent);
    }

    public void changePassword(View view) {
        String password = ((EditText) (view.findViewById(R.id.account_update_password))).getText().toString();
        String confirmPassword = ((EditText) (view.findViewById(R.id.account_update_confirm_password))).getText().toString();

        if (password.isEmpty() && confirmPassword.isEmpty()) {
            Toast.makeText(getContext(), "New password & Confirm password can't be empty!", Toast.LENGTH_SHORT).show();
        } else {
            if (!password.equals(confirmPassword)) {
                Toast.makeText(getContext(), "New password & Confirm password should be same!", Toast.LENGTH_SHORT).show();
            } else {

                mFirebaseUser.updatePassword(password).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Password updated successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("Password Update", task.getException() + "");
                            Toast.makeText(getContext(), "Password update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


}