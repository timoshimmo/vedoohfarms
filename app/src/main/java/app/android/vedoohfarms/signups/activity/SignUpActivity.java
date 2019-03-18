package app.android.vedoohfarms.signups.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.login.activity.LoginActivity;


public class SignUpActivity extends AppCompatActivity {

    private EditText mFirstName;
    private EditText mSurname;
    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText txtMobile;

    private View mFirstNameUnderline;
    private View mLastNameUnderline;
    private View mEmailUnderline;
    private View mPasswordUnderline;
    private View mMobileUnderline;

    private FirebaseAuth mAuth;

   // AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFirstName = (EditText) findViewById(R.id.txtFirstName);
        mSurname = (EditText) findViewById(R.id.txtLastName);
        txtEmailAddress = (EditText) findViewById(app.android.vedoohfarms.R.id.txtEditEmail);
        txtPassword = (EditText) findViewById(app.android.vedoohfarms.R.id.txtEditPassword);
        txtMobile = (EditText) findViewById(R.id.txtEditMobile);

        Button btnSignUp = (Button) findViewById(R.id.btn_complete_signup);

        mFirstNameUnderline = findViewById(R.id.first_name_underline);
        mLastNameUnderline = findViewById(R.id.last_name_underline);
        mEmailUnderline = findViewById(app.android.vedoohfarms.R.id.email_underline);
        mPasswordUnderline = findViewById(app.android.vedoohfarms.R.id.password_underline);
        mMobileUnderline = findViewById(R.id.mobile_underline);

        mAuth = FirebaseAuth.getInstance();

      //  FragmentSignUp fragmentSignUp = FragmentSignUp.newInstance("Create account");

      //  getSupportFragmentManager().beginTransaction()
     //           .add(R.id.signup_container, fragmentSignUp, "SIGN_UP_FRAGMENT").commit();


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

             /*   if(currentFragment() instanceof FragmentSignUp) {

                    onBackPressed();
                }
                else {
                    cancelDialog();
                }  */


            }
        });

        mFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus) {
                    mFirstNameUnderline.setBackgroundResource(R.color.custom_green);
                }

                else {
                    mFirstNameUnderline.setBackgroundResource(R.color.custom_super_light_gray);
                }
            }
        });

        mSurname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus) {
                    mLastNameUnderline.setBackgroundResource(R.color.custom_green);
                }
                else {
                    mLastNameUnderline.setBackgroundResource(R.color.custom_super_light_gray);
                }
            }
        });

        txtEmailAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    mEmailUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_green);
                }
                else {
                    mEmailUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_super_light_gray);
                }
            }
        });

        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    mPasswordUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_green);
                }
                else {
                    mPasswordUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_super_light_gray);
                }
            }
        });

        txtMobile.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    mMobileUnderline.setBackgroundResource(R.color.custom_green);
                    // listener.collapseToolbar();
                }
                else {
                    mMobileUnderline.setBackgroundResource(R.color.custom_super_light_gray);
                    // listener.expandToolbar();
                }

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptSignUp();
            }
        });

    }

  /*  public Fragment currentFragment(){
        return getSupportFragmentManager().findFragmentByTag("SIGN_UP_FRAGMENT");
    }

    public void cancelDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        LayoutInflater inflaters = (SignUpActivity.this).getLayoutInflater();

        View dialogView = inflaters.inflate(R.layout.cancel_signup_layout, null);
        builder.setView(dialogView);

        Button btnConfirmYes = (Button) dialogView.findViewById(R.id.btnYes);
        Button btnConfirmCancel = (Button) dialogView.findViewById(R.id.btnCancel);

        alertDialog = builder.create();

        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });

        btnConfirmYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                onBackPressed();
            }
        });

        alertDialog.show();

    }

    */

    public void attemptSignUp() {

        // Reset errors.
        mFirstName.setError(null);
        mSurname.setError(null);
        txtEmailAddress.setError(null);
        txtPassword.setError(null);
        txtMobile.setError(null);

        // Store values at the time of the login attempt.
        String firstName = mFirstName.getText().toString();
        String surname = mSurname.getText().toString();
        String emailAdd = txtEmailAddress.getText().toString();
        String userPass = txtPassword.getText().toString();
        String userMobile = txtMobile.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(surname)) {
            mFirstName.setError(getString(R.string.field_required_error));
            mSurname.setError(getString(R.string.field_required_error));
            focusView = mFirstName;

            focusView.requestFocus();
        }

        if (TextUtils.isEmpty(firstName)) {
            mFirstName.setError(getString(R.string.field_required_error));
            focusView = mFirstName;

            cancel = true;

        }

        if (TextUtils.isEmpty(surname)) {
            mSurname.setError(getString(R.string.field_required_error));
            focusView = mSurname;

            cancel = true;

        }

        if (TextUtils.isEmpty(emailAdd)) {
            txtEmailAddress.setError(getString(R.string.field_required_error));
            focusView = txtEmailAddress;

            cancel = true;

        }

        if (TextUtils.isEmpty(userPass)) {
            txtPassword.setError(getString(R.string.field_required_error));
            focusView = txtPassword;

            cancel = true;

        }

        if (TextUtils.isEmpty(userMobile)) {
            txtMobile.setError(getString(R.string.field_required_error));
            focusView = txtMobile;

            cancel = true;

        }

        if (!TextUtils.isEmpty(userMobile) && !isMobileValid(userMobile)) {
            txtMobile.setError("Mobile No. is too short");
            focusView = txtMobile;
            cancel = true;
        }

        if (!TextUtils.isEmpty(emailAdd) && !isEmailLengthValid(emailAdd)) {
            txtEmailAddress.setError("Email is too short");
            focusView = txtEmailAddress;

            cancel = true;
        }

        if (!TextUtils.isEmpty(emailAdd) && !isEmailValid(emailAdd)) {
            txtEmailAddress.setError("Must contain @");
            focusView = txtEmailAddress;
            cancel = true;
        }

        if (!TextUtils.isEmpty(userPass) && !isPassValid(userPass)) {
            txtPassword.setError("Password must be more than 6 characters!");
            focusView = txtPassword;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();
        }
        else {

            mAuth.createUserWithEmailAndPassword(emailAdd, userPass)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                // updateUI(user);

                                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }

                            else {
                                Toast.makeText(SignUpActivity.this, "Sign Up failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isEmailLengthValid(String email) {
        //TODO: Replace this with your own logic
        return email.length() > 6;
    }

    private boolean isPassValid(String pass) {
        //TODO: Replace this with your own logic
        return pass.length() > 6;
    }

    private boolean isMobileValid(String mobileNo) {
        //TODO: Replace this with your own logic
        return mobileNo.length() > 10;
    }

}
