package com.instagram.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Registation extends AppCompatActivity {

    TextView create_acc_withemail,alredyhave_ACC;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);


        create_acc_withemail = findViewById(R.id.create_acc_withemail);
        alredyhave_ACC = findViewById(R.id.alredyhave_ACC);

        create_acc_withemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registation.this, CreateAcemail.class);
                startActivity(intent);

            }
        });

        alredyhave_ACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registation.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}