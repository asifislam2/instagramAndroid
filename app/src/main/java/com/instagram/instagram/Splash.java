package com.instagram.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuth.getInstance();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                if (firebaseAuth !=null){
                    Intent intent = new Intent(Splash.this, Dashbord.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);

    }
}