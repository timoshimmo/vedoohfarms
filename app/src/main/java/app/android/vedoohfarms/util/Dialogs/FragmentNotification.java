package app.android.vedoohfarms.util.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Model.NotificationModel;
import app.android.vedoohfarms.util.adapters.NotificationListAdapter;
import app.android.vedoohfarms.util.widgets.DividerItemDecoration;


public class FragmentNotification extends DialogFragment {

    protected ArrayList<NotificationModel> notitfyData;

    protected String[] notificationTitle;
    protected String[] notificationTimeAdded;
    protected String[] notificationDetails;
    protected String[] notificationRead;

    NotificationModel notifyModel;

    Dialog d;
    Context ctx;

    public FragmentNotification() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
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

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_notification);
        ImageButton btnBackButton = (ImageButton) view.findViewById(R.id.btn_back_arrow);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        NotificationListAdapter adapter = new NotificationListAdapter(getActivity(), notitfyData);
        recyclerView.setAdapter(adapter);

        btnBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dismiss();
            }
        });

        return view;
    }

    private void initDataset() {

        notitfyData = new ArrayList<>();

        notificationTitle = new String[] {"Activity", "Update", "Purchase", "Information",
                "Activity", "Information", "Update", "Activity", "Purchase", "Purchase",
                "Information", "Information", "Information", "Purchase",
                "Activity", "Activity", "Update", "Information", "Activity", "Update"};

        notificationTimeAdded = new String[] {"10 sec ago", "36 sec ago", "2 min ago", "3 min ago",
                "4 min ago", "7 min ago", "17 min ago", "33  min ago", "35 min ago", "37 min ago",
                "48 min ago", "1 hr ago", "1 hr ago", "2 hr ago", "4 hr ago", "12 hr ago",
                "13 hr ago", "15 hr ago", "17 hr ago", "20 hr ago"};

        notificationDetails = new String[] {"You viewed the details of Hephzebah Poultry Farm Products and opened their website.",
                "A new item has been added to the list. It's a Fibre Farm Product called sweet potatoes and it cost as low as 600 Naira per kg.",
                "You purchased 200 grams of Carrots from an anonymous farm at the cost of 30 Naira per grams",
                "100 people liked and 37 people purchased Irish Potatoes from abundant farm products in the last week.",
                "You and 25 others liked Chicken farm products from All Birds Farm Products Limited.",
                "10 people liked and 2 people purchased Cat Fish from Pisci Farms in the last week.",
                "A new item has been added to the list. It's a vegetable called broccoli and it cost as low as 90 Naira per kg.",
                "You viewed the details of Oribuzo Farm Products, viewed their website and shared their products with your friends.",
                "You purchased 3 crates of Eggs from an anonymous farm at an available cost of 900 Naira per crate.",
                "You purchased 3 cartons of Dried Fish from Mallam Sambo Fish Farm at an available cost of 26,000 Naira per carton.",
                "40 people liked and 6 people purchased Chicken from Laser Poultry Farm Products in the last week.",
                "74 people liked and 60 people purchased Dried Fish from Mallam Sambo Fish Farm in the last week.",
                "12 people liked and 2 people purchased Fresh Milk from Sarah Razak Cattle Farm in the last week.",
                "You purchased 200 grams of Honey from an Adekunle Bee Farm at the cost of 350 Naira per 200 grams",
                "You viewed the details of Rwang Potato Farm Products, viewed their website and shared their products with your friends.",
                "You and 25 others liked Cat Fish from The Largest Fish Farm Limited.",
                "A new item has been added to the list. It's a Sea Food Product called Lobsters and it cost as low as 3,000 Naira per kg.",
                "30 people liked and 21 people purchased Fresh Green Beans from an Anonymous Farm in the last week.",
                "You and 50 others liked Chicken from Family Farms.",
                "A new item has been added to the list. It's a Fish Farm Product called Shrimps and it cost as low as 720 Naira per kg."};

        notificationRead = new String[] {"0", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1",
                "0", "0", "1", "1", "1", "1", "1"};

        for(int i=0; i < notificationTitle.length; i++) {

            notifyModel = new NotificationModel(notificationTitle[i], notificationTimeAdded[i], notificationDetails[i],
                    notificationRead[i]);
            notitfyData.add(i, notifyModel);
        }
    }

    public void Dismiss() {
        DialogFragment dialogFragment = (DialogFragment)getFragmentManager().findFragmentByTag("notifyDialog");
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }


  /*  public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }  */
}
