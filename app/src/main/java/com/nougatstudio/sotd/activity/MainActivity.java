package com.nougatstudio.sotd.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.nougatstudio.sotd.R;
import com.nougatstudio.sotd.nougatstudio.BottomNavigationHelper;

import layout.Profile;
import layout.Registration;
import layout.signup;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_upload:

                    return true;
                case R.id.navigation_search:
                        fragmentTransaction.replace(R.id.fragmentContainerLayout, new Registration());
                        fragmentTransaction.commit();
                                       return true;
                case R.id.navigation_home:
                    fragmentTransaction.replace(R.id.fragmentContainerLayout, new Profile());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_leaderboard:
                    item.setIcon(R.drawable.leaderboard_icon);
                    fragmentTransaction.replace(R.id.fragmentContainerLayout, new Profile());
                    fragmentTransaction.commit();
                    return true;
                case R.id.navigation_profile:
                    fragmentTransaction.replace(R.id.fragmentContainerLayout, new Profile());
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationHelper.disableShiftMode(navigation);
        navigation.getMenu().getItem(2).setChecked(true);
        if(AccessToken.getCurrentAccessToken() != null)
        {
            Log.d("Token","Enter");
            com.facebook.Profile profile = com.facebook.Profile.getCurrentProfile();
            if(profile != null)
            {
                Toast toast = Toast.makeText(this, "Welcome "+profile.getName(), Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

}
