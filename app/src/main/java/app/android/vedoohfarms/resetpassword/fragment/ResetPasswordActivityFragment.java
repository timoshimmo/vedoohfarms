package app.android.vedoohfarms.resetpassword.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.resetpassword.activity.ResetPasswordActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResetPasswordActivityFragment extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;

    EditText txtFindAccount;
    ImageButton btnCancelEmailAdd;
    View mfindAcctUnderline;

    Fragment fragmentConfirmAccount;
    FragmentManager fm;
    FragmentTransaction ft;

    Context context;


    public ResetPasswordActivityFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static ResetPasswordActivityFragment newInstance(String param) {
        ResetPasswordActivityFragment fragment = new ResetPasswordActivityFragment();
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

        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        context = getActivity();
        ((ResetPasswordActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        fragmentConfirmAccount = FragmentConfirmAccount.newInstance("Confirm Your Account");

        txtFindAccount = (EditText)  view.findViewById(R.id.txtEditFindAcct);
        btnCancelEmailAdd = (ImageButton) view.findViewById(R.id.btn_clear_find_acct_email);
        Button btnFindAccount = (Button) view.findViewById(R.id.btn_find_account);

        mfindAcctUnderline = view.findViewById(R.id.find_acct_underline);

        btnFindAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = ((ResetPasswordActivity) context).getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.signup_container, fragmentConfirmAccount, "CONFIRM_ACCOUNT_FRAGMENT");
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        txtFindAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(count > 0) {
                    if(btnCancelEmailAdd.getVisibility() == View.INVISIBLE) {
                        btnCancelEmailAdd.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    if(btnCancelEmailAdd.getVisibility() == View.VISIBLE) {
                        btnCancelEmailAdd.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtFindAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mfindAcctUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_green);
                }
                else {
                    mfindAcctUnderline.setBackgroundResource(app.android.vedoohfarms.R.color.custom_super_light_gray);
                }
            }
        });

        return view;
    }
}
