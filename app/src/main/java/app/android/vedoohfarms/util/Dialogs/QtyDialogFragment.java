package app.android.vedoohfarms.util.Dialogs;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.signups.fragments.FragmentSignUp;

/**
 * A simple {@link Fragment} subclass.
 */
public class QtyDialogFragment extends DialogFragment {

    private static final String ARG_QTY_TYPE = "qty_type";
    final int[] qtyResult = new int[1];
    Dialog d;
    Context ctx;

    private String mQtyType;
    OrderDialogListener mListener;

    public QtyDialogFragment() {
        // Required empty public constructor
    }

    public static QtyDialogFragment newInstance(String param) {
        QtyDialogFragment fragment = new QtyDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_QTY_TYPE, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);

        if (getArguments() != null) {
            mQtyType = getArguments().getString(ARG_QTY_TYPE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View dialogView = inflater.inflate(R.layout.order_layout, container, false);

        ctx = getActivity();

        TextView unitValue = (TextView) dialogView.findViewById(R.id.txtQtyUnit);
        final TextView qtyValue = (TextView) dialogView.findViewById(R.id.txtQuantity);
        ImageButton btnReduce = (ImageButton) dialogView.findViewById(R.id.btnLess);
        ImageButton btnIncrease = (ImageButton) dialogView.findViewById(R.id.btnMore);
        ImageButton btnCloseDialog = (ImageButton) dialogView.findViewById(R.id.btn_cancel_order);
        RelativeLayout btnAddToBag = (RelativeLayout) dialogView.findViewById(R.id.btn_add_item_to_bag);

        unitValue.setText(mQtyType);
        qtyResult[0] = Integer.valueOf(qtyValue.getText().toString());

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                d.cancel();
            }
        });


        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(qtyResult[0] > -1) {
                    qtyResult[0]++;
                    qtyValue.setText(String.valueOf(qtyResult[0]));
                }
            }
        });

        btnReduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(qtyResult[0] > 0) {
                    qtyResult[0]--;
                    qtyValue.setText(String.valueOf(qtyResult[0]));
                }
            }
        });

        btnAddToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx, "Item Added to cart", Toast.LENGTH_LONG).show();
                Dismiss();
            }
        });

        return dialogView;
    }

    public void Dismiss() {
        DialogFragment dialogFragment = (DialogFragment)getFragmentManager().findFragmentByTag("orderDialog");
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    public interface OrderDialogListener {
        public void onItemSelected();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OrderDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OrderDialogListener");
        }
    }

}
