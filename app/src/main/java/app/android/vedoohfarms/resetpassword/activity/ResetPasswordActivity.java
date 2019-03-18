package app.android.vedoohfarms.resetpassword.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import app.android.vedoohfarms.R;
import app.android.vedoohfarms.resetpassword.fragment.FragmentConfirmAccount;
import app.android.vedoohfarms.resetpassword.fragment.ResetPasswordActivityFragment;


public class ResetPasswordActivity extends AppCompatActivity {

    Fragment resetPasswordActivityFragment;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.notify_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ResetPasswordActivityFragment fragmentResetPass = ResetPasswordActivityFragment.newInstance("Find Your Account");

        getSupportFragmentManager().beginTransaction()
                .add(R.id.signup_container, fragmentResetPass, "RESET_PASSWORD_FRAGMENT").commit();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(currentFragment() instanceof FragmentConfirmAccount) {

                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.signup_container, fragmentResetPass, "CONFIRM_ACCOUNT_FRAGMENT");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }
            }
        });

    }

    public Fragment currentFragment(){
        return getSupportFragmentManager().findFragmentByTag("SIGN_UP_FRAGMENT");
    }

}
