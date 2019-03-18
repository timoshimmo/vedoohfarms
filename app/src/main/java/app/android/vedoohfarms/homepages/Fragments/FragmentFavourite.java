package app.android.vedoohfarms.homepages.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Model.FavouritesModel;
import app.android.vedoohfarms.util.adapters.FavouritesListAdapter;
import app.android.vedoohfarms.util.adapters.HomeListAdapter;

public class FragmentFavourite extends Fragment {

    protected ArrayList<FavouritesModel> itemData;

    protected int[] itemImgs;
    protected String[] itemName;
    protected String[] farmName;
    protected double[] itemPrice;
    protected String[] scaleUnits;
    protected int[] itemFavs;
    protected int[] itemPur;

    FavouritesModel favsModel;

    public FragmentFavourite() {
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

        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvFavourites);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FavouritesListAdapter adapter = new FavouritesListAdapter(getActivity(), itemData);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initDataset() {

        itemData = new ArrayList<>();

        itemImgs = new int[] {R.mipmap.carrot_onion_farm, R.mipmap.corn_field_young_plants_farm,
                R.mipmap.seafood, R.mipmap.carrot_onion_farm, R.mipmap.seafood, R.mipmap.meat_chicken, R.mipmap.fruits_mixed};

        itemName = new String[] {"Onion", "Potato", "Crabs", "Carrot", "Shrimps", "Mutton", "Lemons"};

        scaleUnits = new String[] {"Gram", "Kilogram", "Gram", "Kilogram", "Gram", "Kilogram", "Piece"};

        itemPrice = new double[] {50, 750, 400, 650, 450, 800, 150};

        farmName = new String[] {"GrandBouche Farms", "Trigger Farms", "Lileps Farms",
                "GrandBouche Farms", "La Gallarina Farms", "Calamus Creek Farms", "Fructus Farms"};

        itemPur = new int[] {7, 8, 0, 3, 18, 30, 19};

        itemFavs = new int[] {19, 12, 9, 36, 28, 41, 37};

        for(int i=0; i < itemName.length; i++) {
            favsModel = new FavouritesModel(itemName[i], farmName[i], itemPrice[i], scaleUnits[i], itemImgs[i], itemPur[i], itemFavs[i]);
            itemData.add(i, favsModel);
        }
    }

}
