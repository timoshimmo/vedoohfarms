package app.android.vedoohfarms.signups.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.login.activity.LoginActivity;
import app.android.vedoohfarms.signups.activity.SignUpActivity;

public class FragmentTermsPolicy extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;

    Context context;

    private FirebaseAuth mAuth;

    String getUserEmail;
    String getUserPass;

    public FragmentTermsPolicy() {
        // Required empty public constructor
    }

    public static FragmentTermsPolicy newInstance(String param) {
        FragmentTermsPolicy fragment = new FragmentTermsPolicy();
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

        View view = inflater.inflate(R.layout.fragment_terms_policy, container, false);

        context = getActivity();
        ((SignUpActivity) context).getSupportActionBar().setTitle(mAppbarTitle);

        Button btnFinish = (Button) view.findViewById(R.id.btn_complete_signup);

        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mAuth = FirebaseAuth.getInstance();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefEmail = getActivity().getSharedPreferences("PREFEMAIL", Context.MODE_PRIVATE);
                getUserEmail = prefEmail.getString("userEmail", "");

                SharedPreferences getFirstLogged = getActivity().getSharedPreferences("PREFPASSWORD", Context.MODE_PRIVATE);
                getUserPass = getFirstLogged.getString("userPass", "");

               // Toast.makeText(getActivity(), "Username : " + getUserEmail + "\n" + "Password : " + getUserPass, Toast.LENGTH_LONG).show();

                mAuth.createUserWithEmailAndPassword(getUserEmail, getUserPass)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()) {

                                    FirebaseUser user = mAuth.getCurrentUser();
                                   // updateUI(user);

                                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                                    startActivity(intent);

                                }

                                else {
                                    Toast.makeText(getActivity(), "Sign Up failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                            }
                        });

            }
        });

        return view;
    }

}
