package com.nougatstudio.sotd.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.nougatstudio.sotd.R;
import com.nougatstudio.sotd.adapters.SliderAdaptor;

import java.util.ArrayList;

public class FullScreen extends AppCompatActivity {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] SlideImages= {R.drawable.b,R.drawable.e,R.drawable.b,R.drawable.f,R.drawable.e,R.drawable.b};
    private ArrayList<Integer> SlideImageArray = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_full_screen);
        init();
    }

    private void init() {
        for(int i=0;i<SlideImages.length;i++)
            SlideImageArray.add(SlideImages[i]);

        mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(new SliderAdaptor(FullScreen.this,SlideImageArray));
//        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
//        indicator.setViewPager(mPager);

        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == SlideImages.length) {
//                    currentPage = 0;
//                }
//                mPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 2500, 2500);
    }
}
