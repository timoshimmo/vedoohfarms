package app.android.vedoohfarms.signups.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class FragmentGender extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    RadioGroup genderGroup;
    RadioButton rdMale, rdFemale;

    Fragment fragmentMobile;
    FragmentManager fm;
    FragmentTransaction ft;

    AlertDialog alertDialog;

    Context context;

    public FragmentGender() {
        // Required empty public constructor
    }


    public static FragmentGender newInstance(String param) {
        FragmentGender fragment = new FragmentGender();
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

        final View view = inflater.inflate(R.layout.fragment_gender, container, false);

        context = getActivity();
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentMobile = FragmentMobile.newInstance("Mobile Number");

        Button btnNext = (Button) view.findViewById(R.id.btn_gender_next);
        genderGroup = (RadioGroup) view.findViewById(R.id.radioGenderGroup);

        rdMale = (RadioButton) view.findViewById(R.id.rbMale);
        rdFemale = (RadioButton) view.findViewById(R.id.rbFemale);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                if (genderGroup.getCheckedRadioButtonId() == -1) {

                    cancelDialog();
                }

                else {

                    fm = ((SignUpActivity) context).getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.signup_container, fragmentMobile, "MOBILE_FRAGMENT");
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();
                }


            }
        });

        return view;
    }

    public void cancelDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflaters = (getActivity()).getLayoutInflater();

        View dialogView = inflaters.inflate(R.layout.gender_selection_alert, null);
        builder.setView(dialogView);

        ImageButton btnCancel = (ImageButton) dialogView.findViewById(R.id.btn_close_gender_alert);

        alertDialog = builder.create();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

}
