package com.nougatstudio.sotd.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nougatstudio.sotd.R;
import com.nougatstudio.sotd.nougatstudio.Blur;

public class SignIn extends AppCompatActivity {
ImageView backgroundimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }
}
