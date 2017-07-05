package com.lab42.maham.senseilocater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_teacher_form extends AppCompatActivity {

    EditText t1,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_form);

        t1 = (EditText)findViewById(R.id.et_teacher_profile_fname);
        t4 = (EditText)findViewById(R.id.et_teacher_email);
        t5 = (EditText)findViewById(R.id.et_teacher_profile_password);

        Button bu = (Button)findViewById(R.id.btn_teacher_profile_signup);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp=getSharedPreferences("Preferences",MODE_PRIVATE);
                SharedPreferences.Editor e=sp.edit();
                e.putString("user","teacher");
                e.putString("userName",t1.getText().toString());
                e.putString("userEmail",t4.getText().toString());
                e.putString("userPassword",t5.getText().toString());
                e.commit();
                Intent intent = new Intent(getApplicationContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}
