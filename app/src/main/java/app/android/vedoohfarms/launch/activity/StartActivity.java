package app.android.vedoohfarms.launch.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.viewpagerindicator.CirclePageIndicator;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.launch.fragment.fragStartFive;
import app.android.vedoohfarms.launch.fragment.fragStartFour;
import app.android.vedoohfarms.launch.fragment.fragStartOne;
import app.android.vedoohfarms.launch.fragment.fragStartThree;
import app.android.vedoohfarms.launch.fragment.fragStartTwo;
import app.android.vedoohfarms.welcome.activity.WelcomeActivity;

public class StartActivity extends AppCompatActivity {

    static SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    Button getStarted;
    private LinearLayout circleBack;
    Context ctx;
    ImageView imgStart;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mAuth = FirebaseAuth.getInstance();

        ctx = StartActivity.this;

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser frbUser = firebaseAuth.getCurrentUser();
                if(frbUser != null) {
                    Toast.makeText(StartActivity.this, "User Logged In", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(StartActivity.this, "User Logged Out", Toast.LENGTH_LONG).show();
                }
            }
        };

        circleBack = (LinearLayout) findViewById(R.id.start_image_layout);
        imgStart = (ImageView) findViewById(R.id.frag_one_image);

        getStarted = (Button) findViewById(R.id.get_started);

        mSectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager());

        mViewPager = (ViewPager)this.findViewById(R.id.start_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        CirclePageIndicator cp = (CirclePageIndicator)findViewById(R.id.indicator);
        if (cp != null) {
            cp.setViewPager(mViewPager);
        }

        mViewPager.setCurrentItem(0, true);

        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, WelcomeActivity.class);
                startActivity(intent);
                StartActivity.this.finish();
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position == 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.green_oval_background, null));
                    }
                    else {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.green_oval_background));
                    }

                    imgStart.setImageResource(R.drawable.ic_corn_icon);
                }

                if(position == 1) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.blue_oval_background, null));
                    }
                    else {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.blue_oval_background));
                    }

                    imgStart.setImageResource(R.drawable.ic_paper_bill);

                }

                if(position == 2) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.red_oval_background, null));
                    }
                    else {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.red_oval_background));
                    }

                    imgStart.setImageResource(R.drawable.ic_bean_seeds);

                }

                if(position == 3) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.orange_oval_background, null));
                    }
                    else {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.orange_oval_background));
                    }

                    imgStart.setImageResource(R.drawable.ic_corn_3d);

                }

                if(position == 4) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.purple_oval_background, null));
                    }
                    else {
                        circleBack.setBackground(ctx.getResources().getDrawable(R.drawable.purple_oval_background));
                    }

                    imgStart.setImageResource(R.drawable.ic_corn_icon);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        SectionsPagerAdapter(FragmentManager fragmentManager) {

            super(fragmentManager);
        }

        public int getCount() {
            return 5;
        }

        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new fragStartOne();

                case 1:
                    return new fragStartTwo();

                case 2:
                    return new fragStartThree();

                case 3:
                    return new fragStartFour();

                case 4:
                    return new fragStartFive();

                default:
                    return null;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

       // FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
