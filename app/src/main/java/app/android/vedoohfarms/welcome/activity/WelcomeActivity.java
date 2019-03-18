package app.android.vedoohfarms.welcome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

//import com.facebook.AccessToken;
//import com.facebook.CallbackManager;
//import com.facebook.FacebookCallback;
//import com.facebook.FacebookException;

//import com.facebook.login.LoginManager;
//import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.TwitterAuthProvider;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;

import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;

import app.android.vedoohfarms.login.activity.LoginActivity;
import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class WelcomeActivity extends AppCompatActivity {

    LinearLayout btnFacebook;
    LinearLayout btnTwitter;
    LinearLayout btnEmail;
    Button btnLoginHere;

  //  CallbackManager callbackManager;

    private TwitterAuthClient mTwitterAuthClient;

    private FirebaseAuth mAuth;

    private static final String TAG = "Login_Attempt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Twitter.initialize(WelcomeActivity.this);
      //  callbackManager = CallbackManager.Factory.create();

      //  mAuth = FirebaseAuth.getInstance();

      /*  LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

                Toast.makeText(WelcomeActivity.this, "Facebook Login Cancelled", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

               Toast.makeText(WelcomeActivity.this, "Facebook Login Failure", Toast.LENGTH_SHORT).show();

            }
        }); */

        btnFacebook = (LinearLayout) findViewById(R.id.btn_fb_login);
        btnTwitter = (LinearLayout) findViewById(R.id.btn_tw_login);
        btnEmail = (LinearLayout) findViewById(R.id.btn_email_login);
        btnLoginHere = (Button) findViewById(R.id.btn_login_here);

        mTwitterAuthClient= new TwitterAuthClient();

        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  LoginManager.getInstance().logInWithReadPermissions(WelcomeActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

        btnTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTwitterAuthClient.authorize(WelcomeActivity.this, new Callback<TwitterSession>() {
                    @Override
                    public void success(Result<TwitterSession> result) {

                        Log.d(TAG, "twitterLogin:success" + result);
                        handleTwitterSession(result.data);

                    }

                    @Override
                    public void failure(TwitterException exception) {

                        Log.w(TAG, "twitterLogin:failure" + exception);

                    }
                });

            }
        });

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

   /* public void handleFacebookAccessToken(AccessToken loginToken) {

        AuthCredential mCredentials = FacebookAuthProvider.getCredential(loginToken.getToken());
        mAuth.signInWithCredential(mCredentials)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            Toast.makeText(WelcomeActivity.this, "Facebook Log In With Credential Successfully!", Toast.LENGTH_LONG).show();
                           // FirebaseUser user = mAuth.getCurrentUser();
                        }

                        else {

                            Toast.makeText(WelcomeActivity.this, "Facebook Log In With Credential Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

*/
    public void handleTwitterSession(TwitterSession mSession) {

        Log.d(TAG, "handleTwitterSession" + mSession);

        AuthCredential credentials = TwitterAuthProvider.getCredential(mSession.getAuthToken().token,
                mSession.getAuthToken().secret);

        mAuth.signInWithCredential(credentials)
                .addOnCompleteListener(WelcomeActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            Toast.makeText(WelcomeActivity.this, "Twitter Log In With Credential Successfully!", Toast.LENGTH_LONG).show();
                        }
                        else {

                            Toast.makeText(WelcomeActivity.this, "Twitter Log In With Credential Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  callbackManager.onActivityResult(requestCode, resultCode, data);
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

}
