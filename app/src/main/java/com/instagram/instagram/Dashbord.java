package com.instagram.instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashbord extends AppCompatActivity {

    AppCompatButton log_outBTN;

    FirebaseAuth firebaseAuth;
    FrameLayout Framl_Lay;
    ImageView home_menu,reels;
    CircleImageView profile;
    MaterialToolbar materialToolbar;

    NavigationView navigation_view;
    DrawerLayout drawer_layout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);

        Framl_Lay = findViewById(R.id.Framl_Lay);
        profile = findViewById(R.id.profile);
        home_menu = findViewById(R.id.home_menu);
        reels = findViewById(R.id.reels);


        drawer_layout = findViewById(R.id.drawer_layout);
        materialToolbar = findViewById(R.id.materialToolbar);
        navigation_view = findViewById(R.id.navigation_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                Dashbord.this, drawer_layout, materialToolbar, R.string.closeDrawer, R.string.openDrawer
        );
        drawer_layout.addDrawerListener(toggle);

        firebaseAuth = FirebaseAuth.getInstance();

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.log_out){

                    firebaseAuth.signOut();
                    Intent intent = new Intent(Dashbord.this, MainActivity.class);
                    startActivity(intent);
                }

                return false;
            }
        });

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.Framl_Lay, new HomeFragment());
        transaction.commit();
        ((FrameLayout) findViewById(R.id.Framl_Lay)).removeAllViews();


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.Framl_Lay, new Profile_Fragment());
                transaction.commit();
                ((FrameLayout) findViewById(R.id.Framl_Lay)).removeAllViews();



            }
        });

        home_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.Framl_Lay, new HomeFragment());
                transaction.commit();
                ((FrameLayout) findViewById(R.id.Framl_Lay)).removeAllViews();


            }
        });

        reels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.Framl_Lay, new Fragment_reels());
                transaction.commit();

            }
        });





    }
}