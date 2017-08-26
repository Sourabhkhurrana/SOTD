package com.nougatstudio.sotd.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.nougatstudio.sotd.R;
import com.nougatstudio.sotd.nougatstudio.Blur;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import layout.Registration;
import layout.signup;

public class SignIn extends AppCompatActivity {
    private ImageView backgroundImage;
    int width;
    int height;
    private ViewPager viewPager;
    ImageView imv, userHome, userProfile;
    FrameLayout frameLayout;
InkPageIndicator inkPageIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        frameLayout = (FrameLayout) findViewById(R.id.frame_container);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        load();
        initializeObjects();
        if(AccessToken.getCurrentAccessToken() != null)
        {

            Profile profile = Profile.getCurrentProfile();
            Toast toast = Toast.makeText(this, "Logged In Success "+profile.getFirstName(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    private void initializeObjects(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;
        backgroundImage = (ImageView) findViewById(R.id.backgroundImage);
       // setImageBlur();
    }

    private void setImageBlur(){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scene1);
//        Bitmap blurredBitmap = Blur.blur(this, bitmap);
//        backgroundImage.setImageBitmap(blurredBitmap);
//        backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        backgroundImage.setMaxHeight(height);
//        backgroundImage.setMaxWidth(width);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    public void load() {
        frameLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.VISIBLE);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        inkPageIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        inkPageIndicator.setViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {

            switch (pos) {

                case 0:
                    return new signup();
                case 1:
                    return new Registration();
                default:
                    return new signup();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
