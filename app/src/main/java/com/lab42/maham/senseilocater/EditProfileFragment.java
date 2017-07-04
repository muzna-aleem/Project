package com.lab42.maham.senseilocater;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {


    ImageView teacher_image;
    EditText e;
    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        teacher_image = (ImageView) view.findViewById(R.id.img_edit_profile_activity);
        e = (EditText) view.findViewById(R.id.et_experience);
        teacher_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }

        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                //Display an error

                //   Toast.makeText(MainActivity.this, "dur fit", Toast.LENGTH_SHORT).show();
                return;
            }
            Uri selectedImage = data.getData();
            InputStream imageStream = null;
            try {
                imageStream = getActivity().getApplicationContext().getContentResolver().openInputStream(selectedImage);
            } catch (Exception e)
            {

            }
                 Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

//imp code
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOS);


            String a = Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);


            // a is the encoded pic to be sent to server

            byte[] bytarray = Base64.decode(a, Base64.DEFAULT);
            Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                    bytarray.length);

            teacher_image.setImageBitmap(bmimage);

        }
    }
}