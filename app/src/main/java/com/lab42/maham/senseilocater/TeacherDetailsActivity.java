package com.lab42.maham.senseilocater;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab42.maham.senseilocater.Model.Teacher;

public class TeacherDetailsActivity extends AppCompatActivity {

    TextView teacher_name , teacher_post , teacher_education ;
    ImageView teacher_img;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        teacher_name = (TextView) findViewById(R.id.tv_name_teacher_details);
        teacher_post = (TextView) findViewById(R.id.tv_position_teacher_details);
        teacher_education = (TextView) findViewById(R.id.tv_education_teacher_details);
        teacher_img = (ImageView) findViewById(R.id.img_teacher_details);

        Intent i = getIntent();
        Teacher t = (Teacher) i.getSerializableExtra("object");
        teacher_name.setText(t.Name);
        teacher_post.setText(t.Post);
        teacher_education.setText("abc");


//                String encodedImageString = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDAAEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEB" +
//                        "AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/2wBDAQEBAQEBAQEBAQEBAQEBAQEBAQEB" +
//                        "AQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQH/wAARCAcICWADASIA" +
//                        "AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA" +
//                        "AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3" +
//                        "ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm" +
//                        "p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEA" +
//                        "AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx" +
//                        "BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK" +
//                        "U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3" +
//                        "uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD+bDzD" +
//                        "/eH6UNL+4mXcCTswfo0w7Hr0I9y3PrCkZNu0shMbjpEVJJOcYyWH1+lVG+1FN3kMEPRuecE54I7g" +
//                        "jueR3INfMcr/AKa/z/4Oq7o+99hV/l7dY/3f73mvx3trdiYRW7kkYbt64J/+t9D6gmrttMkMLZI5" +
//                        "UY9Orc4z757dV555wnuFMJiYSRkEfMEY9SeMZGMhTnnr+ugiRuhEsrQ4AwvlFsj58chhgHHf2GQB" +
//                                                                                                                                                         "zy4hqPLzNL4vP+RLZu1+V7/ju7ptUG/avl5nFx63UXLmfut2spx37rd3HZDSgjpx/Mj1Ppn6Ec81" +
//                                                                                                                                                          "pSf6sfQf+hSVnQRSSzKka5Vc5kb5eNz44x35OO3HatOSKYwSMiBjHgbc4JwZTnPPcenAI6kE14tW" +
//                                                                                                                                                          "E3qldc191tzPz026vqtW0zqpyi+aSfuu9t/5vS6+f4kyrvDYI4A5/wC+xx1/U/iTkjJe32M56g49" +
//                                                                                                                                                          "ecM3P8u+cZBJwK07d5FiaSSLAwONwx/y09Rj6/jySCaYHjnzyF456nu2O4/u9PcnHyknmo6TXz/H" +
//                                                                                                                                                          "m9e99fvbZ53wXck1ZPo31mlp2u1+L1S1xTGctz/Djof70n6+36nrWNepICeD2+bknnI6Z6Htnnpn" +
//                                                                                                                                                          "nJrqJI0TcVbdn2Iz8zAHk5HQfhng4JNORROG/clemPn6/eByNvGcD3688gn14zlTi3FN3f5Npbvz" +
//                                                                                                                                                          "7XV3e6TbiGLVO8Yvm5rcytJW1fl1Vu/a2rPP762klCRqrN82egGdrHtknp9ep4JzmrfWUtyIXRH/" +
//                                                                                                                                                          "ANHVQ3H+8Mcn/ZP4sM8nJ7+Kw3TBvKDbc+hBJzg9Pz/DJyKuW2lbEuFZQfMIOeyjLkjvnGRx2yRy" +
//                                                                                                                                                          "S2c/ryjdXd1vv5eTetpPXra+qbe0I0cQnKo9WlspfzNaWXZbdbrW6bPGH0u4ukcujjGOeOxbB5Of" +
//                                                                                                                                                          "bqewySQaxZNKuizKdyBOAeMNy2eoPTA9uSeCSW+hxpELn5IVUYGe+cFs4+XPOM469QTkEmZvDdlN" +
//                                                                                                                                                          "GTJbqfLxyMfNuJA529gB+vIIYty/617/AOzPp+dv5uu7T9E09RfUodX/AOleXmu34taWufNv9j3O" +
//                                                                                                                                                          "CPMbkY7f7Xt7/wCc1HaaNLbq7GXuMkjjq3PrzjnuMHnktX0p/wAInppjMn2dfxxjqR1xz2I/nycZ" +
//                                                                                                                                                          "tz4NtZInUJsJAPC5zycHggg/KcjsO5+8c58Ue1hKkqbpymkoqzbndtXVm1pq3zaapq6WucsBz2Ub" +
//                                                                                                                                                          "u2607rvPuvOzbd9LnzndDEifvhlC2evdj798ZHtnk4ybUd2CoXzh8uBnPuR0xnnHJPAGD1PPpd18" +
//
//


    String encodedImageString = t.Education;                                                                                                                                              //    "PPMaQRZYHrhGO3lgM8nGQcevTkkms6P4cyws25mPcfu2A6v6nnrjv1A55NefRVacpVasXGLaad11";
    Log.d("edu" , t.Education);
        byte[] bytarray = Base64.decode(encodedImageString, Base64.DEFAULT);
        Bitmap bmimage = BitmapFactory.decodeByteArray(bytarray, 0,
                bytarray.length);
        Log.d("my img" , bytarray.toString());
//       if(bmimage == null)
//           teacher_img.setImageDrawable(R);
           //Toast.makeText(TeacherDetailsActivity.this, "ohooooo", Toast.LENGTH_SHORT).show();
        if(bmimage != null)
        teacher_img.setImageBitmap(bmimage);






        b = (Button)findViewById(R.id.teacher_profile_check_notif);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                Notification notif = new Notification.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.logo1_web)
                        .setContentTitle("Notification")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo1_web))
                        .setContentText("You have new Notification")
                        .build();

                notificationManager.notify(0,notif);


            }
        });
    }
}
