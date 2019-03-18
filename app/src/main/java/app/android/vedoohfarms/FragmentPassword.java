package app.android.vedoohfarms;


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

import app.android.vedoohfarms.signups.activity.SignUpActivity;
import app.android.vedoohfarms.signups.fragments.FragmentTermsPolicy;

public class FragmentPassword extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    private EditText txtPassword;
    private ImageButton btnClear;
    private View mPasswordUnderline;

    Fragment fragmentTermsPolicy;
    FragmentManager fm;
    FragmentTransaction ft;

    Context context;

    public FragmentPassword() {
        // Required empty public constructor
    }

    public static FragmentPassword newInstance(String param) {
        FragmentPassword fragment = new FragmentPassword();
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

        final View view = inflater.inflate(R.layout.fragment_password, container, false);

        context = getActivity();
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentTermsPolicy = FragmentTermsPolicy.newInstance("Terms & Policy");

        txtPassword = (EditText) view.findViewById(R.id.txtEditPassword);
        btnClear = (ImageButton) view.findViewById(R.id.btn_clear_password);
        mPasswordUnderline = view.findViewById(R.id.password_underline);
        Button btnNext = (Button) view.findViewById(R.id.btn_password_next);

        txtPassword.addTextChangedListener(new TextWatcher() {
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
                txtPassword.setText("");
                btnClear.setVisibility(View.INVISIBLE);
            }
        });

        txtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mPasswordUnderline.setBackgroundResource(R.color.custom_green);
                }
                else {
                    mPasswordUnderline.setBackgroundResource(R.color.custom_super_light_gray);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                attemptPasswordSignup();
            }
        });

        return view;
    }

    public void attemptPasswordSignup() {

        txtPassword.setError(null);

        String password = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPassValid(password)) {
            txtPassword.setError("Mobile No. is too short");
            focusView = txtPassword;
            cancel = true;
        }


        if (TextUtils.isEmpty(password)) {
            txtPassword.setError("This field is required");
            focusView = txtPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            fm = ((SignUpActivity) context).getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.signup_container, fragmentTermsPolicy, "TERMS_POLICY_FRAGMENT");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    private boolean isPassValid(String homeAdd) {
        //TODO: Replace this with your own logic
        return homeAdd.length() > 6;
    }

}
