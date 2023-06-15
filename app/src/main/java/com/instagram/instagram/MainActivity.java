package com.instagram.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView create_acc;
    Animation animation;

    FirebaseAuth firebaseAuth;
    AppCompatButton login_BTN;
    TextInputEditText ed_email_log,ed_pass_log;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_acc = findViewById(R.id.create_acc);
        ed_email_log = findViewById(R.id.ed_email_log);
        ed_pass_log = findViewById(R.id.ed_pass_log);
        login_BTN = findViewById(R.id.login_BTN);
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);

        create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation.getStartTime();
                Intent intent = new Intent(MainActivity.this, Registation.class);
                startActivity(intent);

            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        login_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              String EMAIL = ed_email_log.getText().toString().trim();
              String PASS = ed_pass_log.getText().toString().trim();

              if (EMAIL.length()>0 && EMAIL.length() <100 && PASS.length()>0 && PASS.length()<=10){

                  if (firebaseAuth !=null){
                      Intent intent = new Intent(MainActivity.this, Dashbord.class);
                      startActivity(intent);
                      finish();
                  }else {

                      Toast.makeText(MainActivity.this, "You Dont have an account", Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(MainActivity.this, CreateAcemail.class);
                      startActivity(intent);
                  }

              }else {

                  Toast.makeText(MainActivity.this, "Please field Your All input ", Toast.LENGTH_SHORT).show();
              }



            }
        });








    }
}