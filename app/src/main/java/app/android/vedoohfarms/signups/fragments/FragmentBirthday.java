package app.android.vedoohfarms.signups.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class FragmentBirthday extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    private Context context;

    Fragment fragmentGender;
    FragmentManager fm;
    FragmentTransaction ft;

    public FragmentBirthday() {
        // Required empty public constructor
    }

    public static FragmentBirthday newInstance(String param) {
        FragmentBirthday fragment = new FragmentBirthday();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAppbarTitle = getArguments().getString(ARG_SECTION_TITLE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(app.android.vedoohfarms.R.layout.fragment_birthday, container, false);

        context = getActivity();
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentGender = FragmentGender.newInstance("Gender");
        Button btnNext = (Button) view.findViewById(app.android.vedoohfarms.R.id.btn_bday_next);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                fm = ((SignUpActivity) context).getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(app.android.vedoohfarms.R.id.signup_container, fragmentGender, "GENDER_FRAGMENT");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();

            }
        });

        return view;
    }

}
