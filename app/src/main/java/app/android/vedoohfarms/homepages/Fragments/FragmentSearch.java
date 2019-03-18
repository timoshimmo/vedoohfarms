package app.android.vedoohfarms.homepages.Fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Model.ItemListModel;
import app.android.vedoohfarms.util.adapters.ItemListAdapter;
import app.android.vedoohfarms.util.widgets.DividerItemDecoration;


public class FragmentSearch extends Fragment {

    protected ArrayList<ItemListModel> itemData;

    protected String[] itemName;
    protected String[] itemDesc;
    protected String[] itemUnit;
    protected int[] itemImgs;
    protected double[] itemPrice;
    protected String[] farmName;
    protected int[] itemFavs;
    protected int[] itemPur;
    protected int[] likedStatus;

    ItemListModel itmModel;

 //   private OnFragmentInteractionListener mListener;

    public FragmentSearch() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvSearch);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Attempting to open payment gateway", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        ItemListAdapter adapter = new ItemListAdapter(getActivity(), itemData);
        recyclerView.setAdapter(adapter);

        return view;
    }

 /*   public void onButtonPressed(Uri uri) {
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

    private void initDataset() {

        itemData = new ArrayList<>();

        itemName = new String[] {"Onion", "Chicken", "Strawberry", "Tilapia Fish",
                "Potato", "Apples", "Tomato", "Crabs", "Carrot", "Green Pepper",
                "Orange", "Cat Fish", "Frozen Mix Veggies", "Broccoli",
                "Shrimps", "Mutton", "Lemons", "Green Beans", "Sardine Fish", "Mangoes"};

        likedStatus = new int[] {1, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0};

        farmName = new String[] {"GrandBouche Farms", "Hephzibah Farms", "Fructus Farms", "Pisci Farms", "Trigger Farms", "Fructus Farms",
                "GrandBouche Farms", "Lileps Farms", "GrandBouche Farms", "GrandBouche Farms", "Kims Fruit Farm", "Pisci Farms",
                "La Gallarina Farms", "Calamus Creek Farms", "Lileps Farms", "Cooper Hill Farms", "Fructus Farms", "Woolworths Farms",
                "Pisci Farms", "Kims Fruit Farm"};

        itemUnit = new String[] {"Gram", "Kilogram", "Gram", "Gram", "Kilogram", "Carton", "Kilogram", "Piece", "Kilogram",
        "Gram", "Gram", "Kilogram", "Gram", "Gram", "Gram", "Kilogram", "Gram", "Gram", "Carton", "Kilogram"};

        itemImgs = new int[] {R.mipmap.carrot_onion_farm, R.mipmap.meat_chicken, R.mipmap.fruits_mixed, R.mipmap.fish_images, R.mipmap.corn_field_young_plants_farm,
                R.mipmap.fruits_mixed, R.mipmap.intro_cream_of_crop, R.mipmap.seafood, R.mipmap.carrot_onion_farm, R.mipmap.intro_cream_of_crop,
                R.mipmap.fruits_mixed, R.mipmap.fish_images, R.mipmap.frozen_veggies, R.mipmap.intro_cream_of_crop, R.mipmap.seafood,
                R.mipmap.meat_chicken, R.mipmap.fruits_mixed, R.mipmap.intro_cream_of_crop, R.mipmap.fish_images, R.mipmap.fruits_mixed};

        itemPrice = new double[] {50, 460, 280, 320, 600, 3500, 240, 550, 180, 200, 60, 400, 190, 120, 350, 600, 70, 120, 280, 70};

        itemDesc = new String[] {"Fresh food items from one of the best cultivators of the crop in West Africa. " +
                "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum",
                "Fresh food items from one of the best cultivators of the crop in West Africa. " +
                        "Lorem ipsum dolor sit amet conseteur, it gives vitamin B to the body and its useful to the human body. Lorem lorem lorem" +
                        "ipsum, ipsum ipsum"};

        itemFavs = new int[] {19, 12, 9, 36, 28, 41, 37, 43, 60, 23, 11, 70, 100, 119, 127, 83, 29, 72, 19, 106};

        itemPur = new int[] {7, 8, 0, 3, 18, 30, 19, 21, 45, 18, 6, 49, 32, 78, 41, 25, 0, 22, 11, 90};


        for(int i=0; i < itemName.length; i++) {
            itmModel = new ItemListModel(itemName[i], itemPrice[i], itemImgs[i], itemDesc[i], itemUnit[i],
                    itemFavs[i], itemPur[i], likedStatus[i]);

            itemData.add(i, itmModel);
        }
    }
}
