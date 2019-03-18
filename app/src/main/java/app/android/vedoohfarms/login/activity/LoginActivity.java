package app.android.vedoohfarms.login.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.homepages.Activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    private ImageView imgSubIcon;
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mUnderlineUserName;
    private View mUnderlinePassword;
    private AppBarLayout mAppBarLayout;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        RelativeLayout mLoginTopBody = (RelativeLayout) findViewById(R.id.login_top_body);
        imgSubIcon = findViewById(R.id.top_icon_collapsed);

        mUsernameView = (EditText) findViewById(R.id.txtUsername);
        mPasswordView = (EditText) findViewById(R.id.txtPassword);

        mUnderlineUserName = findViewById(R.id.username_underline);
        mUnderlinePassword = findViewById(R.id.paswword_underline);

        Button btnForgotPassword = (Button) findViewById(R.id.btn_forgotten_password);

        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        app.android.vedoohfarms.util.widgets.CustomCoordinatorLayout mCoordinatorLayout = findViewById(app.android.vedoohfarms.R.id.login_layout);
      //  CollapsingToolbarLayout mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(com.android.vedoohfarms.R.id.login_collapse_toolbar);

        mUsernameView.clearFocus();
        mPasswordView.clearFocus();
        mLoginTopBody.requestFocus();

        mCoordinatorLayout.setPassScrolling(false);


        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {

                    toolbar.setTitle(" ");
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {

                    toolbar.setTitle("Log In");
                }
            }
        });

        mUsernameView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(v == mUsernameView) {

                    if(hasFocus) {

                        mUnderlineUserName.setBackgroundResource(R.color.custom_green);

                        if(imgSubIcon.getVisibility() == View.GONE) {
                            imgSubIcon.setVisibility(View.VISIBLE);
                        }

                        mAppBarLayout.setExpanded(false, true);

                    }
                    else {

                        mUnderlineUserName.setBackgroundResource(R.color.custom_super_light_gray);

                        if(imgSubIcon.getVisibility() == View.VISIBLE) {
                            imgSubIcon.setVisibility(View.GONE);
                        }

                        mAppBarLayout.setExpanded(true, true);

                    }

                }

            }
        });

        mPasswordView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(v == mPasswordView) {

                    if(hasFocus) {

                        mUnderlinePassword.setBackgroundResource(R.color.custom_green);

                        if(imgSubIcon.getVisibility() == View.GONE) {
                            imgSubIcon.setVisibility(View.VISIBLE);
                        }

                        mAppBarLayout.setExpanded(false, true);
                    }
                    else {

                        mUnderlinePassword.setBackgroundResource(R.color.custom_super_light_gray);

                        if(imgSubIcon.getVisibility() == View.VISIBLE) {
                            imgSubIcon.setVisibility(View.GONE);
                        }

                        mAppBarLayout.setExpanded(true, true);

                    }
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

                String userEmail = mUsernameView.getText().toString();
                String userPass = mPasswordView.getText().toString();

                loginUser(userEmail, userPass); */

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            }
        });

    }

    public void loginUser(String mEmail, String mPassword) {
        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                           //  startActivity(intent);
                           // Toast.makeText(LoginActivity.this, "User is logged In!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Email/Password is wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {

        mUsernameView.clearFocus();
        mPasswordView.clearFocus();
        
    }
}
