package com.example.hasanzian.e_commerce.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hasanzian.e_commerce.R;
import com.example.hasanzian.e_commerce.model.DataModels;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {

    private StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 1;
    @BindView(R.id.ed_title)

    EditText title;

    @BindView(R.id.ed_price)
    EditText price;

    @BindView(R.id.ed_image)
    EditText image;

    @BindView(R.id.add_product)
    Button button;

    @BindView(R.id.preview)
    ImageView preview;

    @BindView(R.id.add_image)
    Button upload;

    //a Uri object to store file path
    private Uri filePath;

    String t, p, d;
    int i;
    String downloadUrl;

    public static final String TAG = "URI";
    public String uploadedUrl;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        //getting firebase storage reference
        storageReference = FirebaseStorage.getInstance().getReference();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                preview.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //this method will upload the file
    private void uploadFile() {
        //if there is a file to upload
        if (filePath != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            final StorageReference riversRef = storageReference.child("images/" + filePath.getLastPathSegment());

            riversRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //if the upload is successfull
                    //hiding the progress dialog
                    progressDialog.dismiss();

                    //and displaying a success toast
                    Toast.makeText(getContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    //if the upload is not success full
                    //hiding the progress dialog
                    progressDialog.dismiss();

                    //and displaying error message
                    Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    //calculating progress percentage
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                    //displaying percentage in progress dialog
                    progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                }
            }).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return riversRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        downloadUrl = downloadUri.toString();
                        Log.d("dURL", downloadUrl);

                        t = title.getText().toString();
                        // i = Integer.parseInt(image.getText().toString());
                        d = downloadUrl;
                        p = price.getText().toString();

                        DataModels dataModels = new DataModels(t, p, d);

                        // Write a message to the database
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("order");

                        myRef.push().setValue(dataModels);


                    } else {
                        // Handle failures
                        // ...
                    }
                }
            });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }


}
