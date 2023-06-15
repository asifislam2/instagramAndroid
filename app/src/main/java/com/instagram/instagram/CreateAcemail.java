package com.instagram.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class CreateAcemail extends AppCompatActivity {

    TextView create_acc_mobilenimber;
    TextInputEditText ed_name,ed_email,ed_pass;

    AppCompatButton signup_button;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    RelativeLayout mother_Lay;
    ProgressBar prograssbar;
    HashMap<String, String> hashMap = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acemail);

        create_acc_mobilenimber = findViewById(R.id.create_acc_mobilenimber);

        ed_name = findViewById(R.id.ed_name);
        ed_email = findViewById(R.id.ed_email);
        ed_pass = findViewById(R.id.ed_pass);

        prograssbar = findViewById(R.id.prograssbar);
        mother_Lay = findViewById(R.id.mother_Lay);
        signup_button = findViewById(R.id.signup_button);

        create_acc_mobilenimber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CreateAcemail.this, Registation.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");

            signup_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mother_Lay.setVisibility(View.GONE);

                    String NAME = ed_name.getText().toString().trim();
                    String EMAIL = ed_email.getText().toString().trim();
                    String PASS = ed_pass.getText().toString().trim();

                    if (NAME.length() >0 && NAME.length()<50 && EMAIL.length()>0 && EMAIL.length() < 100 && PASS.length()>0 && PASS.length() <=10){

                        prograssbar.setVisibility(View.VISIBLE);

                        firebaseAuth.createUserWithEmailAndPassword(EMAIL, PASS).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()){

                                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    String USER = firebaseUser.getUid();

                                    hashMap = new HashMap<>();
                                    hashMap.put("NAME", NAME);
                                    hashMap.put("EMAIL", EMAIL);
                                    hashMap.put("PASS", PASS);
                                    hashMap.put("USER", USER);
                                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            prograssbar.setVisibility(View.GONE);

                                            Intent intent = new Intent(CreateAcemail.this, Dashbord.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                                }else {

                                    Intent intent = new Intent(CreateAcemail.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

                    }else {

                        Toast.makeText(CreateAcemail.this, "Thik nai", Toast.LENGTH_SHORT).show();
                    }

                }
            });










    }
}