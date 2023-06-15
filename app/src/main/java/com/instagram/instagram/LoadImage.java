package com.instagram.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoadImage extends AppCompatActivity {

    ImageView myimage;

    CircleImageView profile;

    TextView day_title_name;
    public static Bitmap MY_BITMAP = null;
    public static Bitmap MY_BITMAPONE = null;

    public static  String TITLE_NAME = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_image);

        myimage = findViewById(R.id.myimage);
        profile = findViewById(R.id.profile);


        day_title_name = findViewById(R.id.day_title_name);
        day_title_name.setText(TITLE_NAME);


        if (MY_BITMAP !=null) myimage.setImageBitmap(MY_BITMAP);
        if (MY_BITMAPONE !=null) profile.setImageBitmap(MY_BITMAPONE);









        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(LoadImage.this, R.color.black));
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setNavigationBarColor(ContextCompat.getColor(LoadImage.this, R.color.navigation_bar_color));
        }


    }
}