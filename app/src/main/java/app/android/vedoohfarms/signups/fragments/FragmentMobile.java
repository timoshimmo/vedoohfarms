package app.android.vedoohfarms.signups.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;


public class FragmentMobile extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;

    private EditText txtMobile;
    private ImageButton btnClear;
    private View mMobileUnderline;

    Fragment fragmentEmail;
    FragmentManager fm;
    FragmentTransaction ft;

    Context context;


    public FragmentMobile() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentMobile newInstance(String param) {
        FragmentMobile fragment = new FragmentMobile();
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

        final View view = inflater.inflate(R.layout.fragment_mobile, container, false);

        context = getActivity();

        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentEmail = FragmentEmail.newInstance("Email");

        txtMobile = (EditText) view.findViewById(R.id.txtEditMobile);
        btnClear = (ImageButton) view.findViewById(R.id.btn_clear_mobile);
        mMobileUnderline = view.findViewById(R.id.mobile_underline);
        Button btnNext = (Button) view.findViewById(R.id.btn_mobile_next);


        txtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    if(btnClear.getVisibility() == View.INVISIBLE) {
                        btnClear.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    if(btnClear.getVisibility() == View.VISIBLE) {
                        btnClear.setVisibility(View.INVISIBLE);
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtMobile.setText("");
                btnClear.setVisibility(View.INVISIBLE);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                attemptMobileSignup();

            }
        });

        return view;
    }

    public void attemptMobileSignup() {

        txtMobile.setError(null);

        String mobileNo = txtMobile.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(mobileNo) && !isMobileValid(mobileNo)) {
            txtMobile.setError("Mobile No. is too short");
            focusView = txtMobile;
            cancel = true;
        }


        if (TextUtils.isEmpty(mobileNo)) {
            txtMobile.setError("This field is required");
            focusView = txtMobile;
            cancel = true;
        }

        if (cancel) {

            focusView.requestFocus();

        }
        else {

            fm = ((SignUpActivity) context).getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.signup_container, fragmentEmail, "EMAIL_FRAGMENT");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();

        }

    }

    private boolean isMobileValid(String homeAdd) {
        //TODO: Replace this with your own logic
        return homeAdd.length() > 10;
    }

}
