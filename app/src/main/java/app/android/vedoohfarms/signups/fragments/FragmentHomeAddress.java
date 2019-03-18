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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.activity.SignUpActivity;


public class FragmentHomeAddress extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;
    Context context;

    Fragment fragmentBirthday;
    FragmentManager fm;
    FragmentTransaction ft;

    EditText txtHomeAddress;
    private ImageButton btnClear;
    private View mAddressUnderline;

   // private FragmentHomeAddress.MyAddressFragmentCallback listener;

    public FragmentHomeAddress() {
        // Required empty public constructor
    }

    public static FragmentHomeAddress newInstance(String param) {
        FragmentHomeAddress fragment = new FragmentHomeAddress();
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

        final View view = inflater.inflate(R.layout.fragment_home_address, container, false);

        context = getActivity();

        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentBirthday = FragmentBirthday.newInstance("Birthday");

        txtHomeAddress = (EditText) view.findViewById(R.id.txtEditHomeAddress);
        btnClear = (ImageButton) view.findViewById(R.id.btn_clear_home_address);
        mAddressUnderline = view.findViewById(R.id.home_address_underline);
        Button btnNext = (Button) view.findViewById(R.id.btn_home_address_next);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        txtHomeAddress.addTextChangedListener(new TextWatcher() {
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
                txtHomeAddress.setText("");
                btnClear.setVisibility(View.INVISIBLE);
            }
        });

        txtHomeAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus){
                    mAddressUnderline.setBackgroundResource(R.color.custom_green);
                   // listener.collapseToolbar();
                }
                else {
                    mAddressUnderline.setBackgroundResource(R.color.custom_super_light_gray);
                   // listener.expandToolbar();
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                attemptSignupAddress();

            }
        });

        return view;
    }

    public void attemptSignupAddress() {

        txtHomeAddress.setError(null);

        String homeAdd = txtHomeAddress.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(homeAdd) && !isHomeAddressValid(homeAdd)) {
            txtHomeAddress.setError("Address is too short/incomplete");
            focusView = txtHomeAddress;
            cancel = true;
        }


        if (TextUtils.isEmpty(homeAdd)) {
            txtHomeAddress.setError("This field is required");
            focusView = txtHomeAddress;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            fm = ((SignUpActivity) context).getSupportFragmentManager();
            ft = fm.beginTransaction();
            ft.replace(R.id.signup_container, fragmentBirthday, "BIRTHDAY_FRAGMENT");
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    private boolean isHomeAddressValid(String homeAdd) {
        //TODO: Replace this with your own logic
        return homeAdd.length() > 7;
    }

}
