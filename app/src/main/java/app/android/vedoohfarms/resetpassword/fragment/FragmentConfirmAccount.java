package app.android.vedoohfarms.resetpassword.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.android.vedoohfarms.R;

public class FragmentConfirmAccount extends Fragment {

    private static final String ARG_SECTION_TITLE = "page_title";
    private String mAppbarTitle;


    public FragmentConfirmAccount() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentConfirmAccount newInstance(String param) {
        FragmentConfirmAccount fragment = new FragmentConfirmAccount();
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
        View view = inflater.inflate(R.layout.fragment_confirm_account, container, false);

        return view;
    }

}
