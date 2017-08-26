package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.nougatstudio.sotd.R;

import java.util.Arrays;

import static android.content.ContentValues.TAG;
import static com.nougatstudio.sotd.R.id.loginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class signup extends Fragment implements GoogleApiClient.OnConnectionFailedListener

{
    private static final int RC_SIGN_IN = 9001;
Button facebookButton;
    Button googleButton;
    SignInButton googleSignIn;
     LoginButton loginButton;
    CallbackManager callbackManager;
GoogleApiClient mGoogleApiClient;
    View root;
    public signup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_signup, container, false);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
// options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        facebookButton = (Button) root.findViewById(R.id.facebooLogin);
        loginButton = (LoginButton) root.findViewById(R.id.facebook_sign_in);
        googleSignIn = (SignInButton)root.findViewById(R.id.google_sign_in);
        googleButton = (Button)root.findViewById(R.id.googleLogin);
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        // If using in a fragment
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT);
                toast.show();
                AccessToken.setCurrentAccessToken(null);
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

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast toast = Toast.makeText(getActivity(), "Sign In Success "+acct.getDisplayName(), Toast.LENGTH_SHORT);
            toast.show();

        } else {
            // Signed out, show unauthenticated UI.
            Toast toast = Toast.makeText(getActivity(), "Sign In Error", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
