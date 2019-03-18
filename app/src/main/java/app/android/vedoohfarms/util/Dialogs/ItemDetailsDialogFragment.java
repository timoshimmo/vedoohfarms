package app.android.vedoohfarms.util.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import app.android.vedoohfarms.R;

/**
 * Created by freshfuturesmy on 03/11/17.
 */

public class ItemDetailsDialogFragment extends DialogFragment {

    private static final String ARG_ITEM_DETAILS = "item_details";
    private static final String ARG_NUTRITION_DETAIL = "nutrition_details";
    Dialog d;
    Context ctx;

    private String mItemDetails;
    private String mNutritionDetails;


    ItemsDetailDialogListener mListener;

    public ItemDetailsDialogFragment() {
        // Required empty public constructor
    }

    public static ItemDetailsDialogFragment newInstance(String itmParams, String itmNut) {
        ItemDetailsDialogFragment fragment = new ItemDetailsDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_ITEM_DETAILS, itmParams);
        args.putString(ARG_NUTRITION_DETAIL, itmNut);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);

        if (getArguments() != null) {
            mItemDetails = getArguments().getString(ARG_ITEM_DETAILS);
            mNutritionDetails = getArguments().getString(ARG_NUTRITION_DETAIL);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        d = getDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View dialogView = inflater.inflate(R.layout.dialog_item_more_details, container, false);

        ctx = getActivity();

        TextView itemDetails = (TextView) dialogView.findViewById(R.id.txtAboutDetails);
        TextView nutritionDetails = (TextView) dialogView.findViewById(R.id.txtNutritionDetails);
        ImageButton btnCloseDetails = (ImageButton) dialogView.findViewById(R.id.btnCloseDetails);

        itemDetails.setText(mItemDetails);
        nutritionDetails.setText(mNutritionDetails);

        btnCloseDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.cancel();
            }
        });

        return dialogView;
    }

   /* public void Dismiss() {
        DialogFragment dialogFragment = (DialogFragment)getFragmentManager().findFragmentByTag("itemDetailsDialog");
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    } */

    public interface ItemsDetailDialogListener {
        public void onItemSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (ItemDetailsDialogFragment.ItemsDetailDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ItemsDetailDialogListener");
        }
    }


}
