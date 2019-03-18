package app.android.vedoohfarms.signups.fragments;


import android.content.Context;
import android.content.SharedPreferences;
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

public class FragmentEmail extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    private EditText txtEmailAddress;
    private ImageButton btnEmailClear;
    private View mEmailUnderline;

    private EditText txtPassword;
    private ImageButton btnPassClear;
    private View mPasswordUnderline;

    Fragment fragmentTermsPolicy;
    FragmentManager fm;
    FragmentTransaction ft;

    Context context;

    public FragmentEmail() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentEmail newInstance(String param) {
        FragmentEmail fragment = new FragmentEmail();
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
        final View view = inflater.inflate(app.android.vedoohfarms.R.layout.fragment_email, container, false);

        context = getActivity();
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentTermsPolicy = FragmentTermsPolicy.newInstance("Terms & Policy");

        txtEmailAddress = (EditText) view.findViewById(app.android.vedoohfarms.R.id.txtEditEmail);
        btnEmailClear = (ImageButton) view.findViewById(app.android.vedoohfarms.R.id.btn_clear_email_address);
        mEmailUnderline = view.findViewById(app.android.vedoohfarms.R.id.email_underline);

        txtPassword = (EditText) view.findViewById(app.android.vedoohfarms.R.id.txtEditPassword);
        btnPassClear = (ImageButton) view.findViewById(app.android.vedoohfarms.R.id.btn_clear_password);
        mPasswordUnderline = view.findViewById(app.android.vedoohfarms.R.id.password_underline);

        Button btnNext = (Button) view.findViewById(app.android.vedoohfarms.R.id.btn_email_next);

        txtEmailAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    if(btnEmailClear.getVisibility() == View.INVISIBLE) {
                        btnEmailClear.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    if(btnEmailClear.getVisibility() == View.VISIBLE) {
                        btnEmailClear.setVisibility(View.INVISIBLE);
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    if(btnPassClear.getVisibility() == View.INVISIBLE) {
                        btnPassClear.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    if(btnPassClear.getVisibility() == View.VISIBLE) {
                        btnPassClear.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnEmailClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtEmailAddress.setText("");
                btnEmailClear.setVisibility(View.INVISIBLE);
            }
        });

        btnPassClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtPassword.setText("");
                btnPassClear.setVisibility(View.INVISIBLE);
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                attemptEmailSignup();
            }
        });

        return view;
    }

    public void attemptEmailSignup() {

        txtEmailAddress.setError(null);
        txtPassword.setError(null);

        String emailAdd = txtEmailAddress.getText().toString();
        String userPass = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(emailAdd) && TextUtils.isEmpty(userPass)) {
            txtEmailAddress.setError(getString(R.string.field_required_error));
            txtPassword.setError(getString(R.string.field_required_error));
            focusView = txtEmailAddress;

            focusView.requestFocus();
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

        if (TextUtils.isEmpty(emailAdd)) {
            txtEmailAddress.setError("This field is required");
            focusView = txtEmailAddress;
            cancel = true;
        }

        if (TextUtils.isEmpty(userPass)) {
            txtPassword.setError("This field is required");
            focusView = txtPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        }
        else {

            SharedPreferences prefEmail = getActivity().getSharedPreferences("PREFEMAIL", Context.MODE_PRIVATE);
            SharedPreferences.Editor emailEditor = prefEmail.edit();
            emailEditor.putString("userEmail", emailAdd);
            emailEditor.apply();

            SharedPreferences prefPassword = getActivity().getSharedPreferences("PREFPASSWORD", Context.MODE_PRIVATE);
            SharedPreferences.Editor passEditor = prefPassword.edit();
            passEditor.putString("userPass", userPass);
            passEditor.apply();

            fm = ((SignUpActivity) context).getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.signup_container, fragmentTermsPolicy, "TERMS_POLICY_FRAGMENT");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
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

    private boolean isPassValid(String homeAdd) {
        //TODO: Replace this with your own logic
        return homeAdd.length() > 6;
    }

}
