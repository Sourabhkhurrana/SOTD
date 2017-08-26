package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nougatstudio.sotd.R;

import java.util.Arrays;

import static com.nougatstudio.sotd.R.id.loginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class signup extends Fragment
{

Button facebookButton;
     LoginButton loginButton;
    CallbackManager callbackManager;
    View root;
    public signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_signup, container, false);
        facebookButton = (Button) root.findViewById(R.id.facebooLogin);
        loginButton = (LoginButton) root.findViewById(R.id.login_button);
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        // If using in a fragment
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT);
                toast.show();
                loginButton.performClick();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(AccessToken.getCurrentAccessToken() == null) {
    LoginManager.getInstance().logInWithReadPermissions(getActivity(), Arrays.asList("public_profile"));
}
else
{

    Profile profile = Profile.getCurrentProfile();
    Toast toast = Toast.makeText(getActivity(), "Already Logged In  "+profile.getFirstName(), Toast.LENGTH_SHORT);
    toast.show();

}

            }
            });


    // Callback registration
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            Toast toast = Toast.makeText(getActivity(), "Logged In Success "+profile.getFirstName(), Toast.LENGTH_SHORT);
            toast.show();

        }


        @Override
        public void onCancel() {
            // App code
            Toast toast = Toast.makeText(getActivity(), "Logged In Cancel", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        public void onError(FacebookException exception) {
            Toast toast = Toast.makeText(getActivity(), "Logged In Error", Toast.LENGTH_SHORT);
            toast.show();
        }
});
        return root;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
