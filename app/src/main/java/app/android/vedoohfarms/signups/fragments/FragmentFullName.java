package app.android.vedoohfarms.signups.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class FragmentFullName extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    Context context;

    Fragment fragmentBday;
    FragmentManager fm;
    FragmentTransaction ft;

    EditText mFirstName;
    EditText mSurname;
    View mFirstNameUnderline;
    View mLastNameUnderline;


    public FragmentFullName() {
        // Required empty public constructor
    }

    public static FragmentFullName newInstance(String param) {
        FragmentFullName fragment = new FragmentFullName();
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

        final View view = inflater.inflate(R.layout.fragment_full_name, container, false);

        context = getActivity();

        assert context != null;
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentBday = FragmentBirthday.newInstance("Date of Birth");
        Button btnNext = (Button) view.findViewById(R.id.btn_fullname_next);

        mFirstName = (EditText) view.findViewById(R.id.txtFirstName);
        mSurname = (EditText) view.findViewById(R.id.txtLastName);

        mFirstNameUnderline = view.findViewById(R.id.first_name_underline);
        mLastNameUnderline = view.findViewById(R.id.last_name_underline);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                attemptFullname();

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

        return view;
    }

    public void attemptFullname() {

        // Reset errors.
        mFirstName.setError(null);
        mSurname.setError(null);

        // Store values at the time of the login attempt.
        String firstName = mFirstName.getText().toString();
        String surname = mSurname.getText().toString();

        View focusView;

        if (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(surname)) {
            mFirstName.setError(getString(R.string.field_required_error));
            mSurname.setError(getString(R.string.field_required_error));
            focusView = mFirstName;

            focusView.requestFocus();
        }

        else {

            if (TextUtils.isEmpty(firstName)) {
                mFirstName.setError(getString(R.string.field_required_error));
                focusView = mFirstName;

                focusView.requestFocus();
            }

            else if (TextUtils.isEmpty(surname)) {
                mSurname.setError(getString(R.string.field_required_error));
                focusView = mSurname;

                focusView.requestFocus();
            }

            else {

                fm = ((SignUpActivity) context).getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.signup_container, fragmentBday, "BDAY_FRAGMENT");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }

     /*   getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);  */

    }
}
