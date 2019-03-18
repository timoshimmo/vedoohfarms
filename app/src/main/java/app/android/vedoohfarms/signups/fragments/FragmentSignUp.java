package app.android.vedoohfarms.signups.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class FragmentSignUp extends Fragment {
    private static final String ARG_SECTION_TITLE = "page_title";


    Fragment fragmentFullName;
    FragmentManager fm;
    FragmentTransaction ft;

    Context context;
    private String mAppbarTitle;

    public FragmentSignUp() {
        // Required empty public constructor
    }

    public static FragmentSignUp newInstance(String param) {
        FragmentSignUp fragment = new FragmentSignUp();
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

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        context = getActivity();

        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentFullName = FragmentFullName.newInstance("Name");
        Button btnNext = (Button) view.findViewById(R.id.btn_page_one_next);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fm = ((SignUpActivity) context).getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.signup_container, fragmentFullName, "FULL_NAME_FRAGMENT");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        return view;
    }
}
