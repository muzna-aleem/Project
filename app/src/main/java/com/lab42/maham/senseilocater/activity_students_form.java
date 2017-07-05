package com.lab42.maham.senseilocater;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import Model.ResponseBO;
import Model.Student;
import Model.weather;

public class activity_students_form extends AppCompatActivity {

    EditText t1, t2, t3, t4, t5, t6;
    String abc = "";
    String LOG_TAG="";
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_form);

        Button bu = (Button) findViewById(R.id.btn_std_profile_signup);

        t1 = (EditText) findViewById(R.id.et_std_profile_fname);
        t2 = (EditText) findViewById(R.id.et_std_profile_lname);
        t3 = (EditText) findViewById(R.id.et_std_profile_rollNo);
        t4 = (EditText) findViewById(R.id.et_std_profile_email);
        t5 = (EditText) findViewById(R.id.et_std_profile_password);
        t6 = (EditText) findViewById(R.id.et_std_profile_password_reEnter);
        l = (ListView)findViewById(R.id.lst);
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeachersListActivity.class);
                startActivity(intent);
            }
        });

        new GetServerData().execute();
    }

    private class GetServerData extends AsyncTask<Void, Void, ResponseBO> {

        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(activity_students_form.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected ResponseBO doInBackground(Void... params) {
            try {


                ResponseBO ress = getDataFromApi();
                return ress;

            }catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(ResponseBO responseBO) {
            super.onPostExecute(responseBO);

            if (pDialog.isShowing())
                pDialog.dismiss();


            if(responseBO != null && responseBO.getStudentArrayList() != null && responseBO.getStudentArrayList().size() > 0){
                Toast.makeText(getApplicationContext(), "The record has been successfully added", Toast.LENGTH_LONG).show();

                ArrayList<Student> ll = responseBO.getStudentArrayList();

                int i = 0;
                String st = null;
                for(Student o : ll) {
                    st = o.Name+" "+o.Password+" "+o.Email+" "+o.RollNumber;
                    Toast.makeText(getApplicationContext(),st,Toast.LENGTH_SHORT).show();
                }


            }else{
                Toast.makeText(getApplicationContext(), "Alert: Something went wrong."+abc, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    public void saveStudent(){

    }
    public ResponseBO getDataFromApi()
    {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JsonStr = null;

        abc += "1";
        ResponseBO res = new ResponseBO();
        try {
            abc += "2";
            URL url = new URL("http://senseilocatorwebservices.apphb.com/api/Student");
            urlConnection = (HttpURLConnection) url.openConnection();
            abc += "3";
            urlConnection.setRequestMethod("GET");
            abc += "3.1";
            urlConnection.connect();
            abc += "4";
            StringBuffer buffer = new StringBuffer();
            abc += "5";
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            abc += "6";
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                abc += "0"+line;
            }
            if (buffer.length() == 0) {
                abc += "7";
            }
            else
                abc += "8";
            JsonStr = buffer.toString();
            abc += "9";
            res.setStudentArrayList(getDataFromJson(JsonStr));
            abc += "10";

            return res;
        } catch (IOException e) {
            abc += "exception";
            Log.e("Data not found", "Error ", e);
            Toast.makeText(getApplicationContext(),"Data not found",Toast.LENGTH_SHORT).show();

        } finally{
            abc += "a";
            if (urlConnection != null) {
                urlConnection.disconnect();
                abc += "b";
            }
            if (reader != null) {
                try {
                    reader.close();
                    abc += "c";
                } catch (final IOException e) {
                    Log.e("Placeholder", "Error closing stream", e);
                    Toast.makeText(getApplicationContext(),"it is working",Toast.LENGTH_SHORT).show();
                    abc += "d";
                }
                abc += "e";
            }
            abc += "f";
        }
        return null;
    }
    ArrayList<Student> getDataFromJson(String JsonStr)
    {
        ArrayList<Student> lst = new ArrayList<>();
        Student obj = new Student();
        //weather obj = new weather();
        if(JsonStr != null){
            try {
                JSONArray res = new JSONArray(JsonStr);
                for(int i = 0;i < res.length();i++){
                    obj = new Student();
                    JSONObject o=res.getJSONObject(i);
                    obj.RollNumber =o.getString("RollNumber");
                    obj.Name =o.getString("Name");
                    obj.Email =o.getString("Email");
                    obj.Password =o.getString("Password");
                    lst.add(obj);
                }

                return lst;


            }catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        return lst;
    }
}