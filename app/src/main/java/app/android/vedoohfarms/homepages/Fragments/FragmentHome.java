package app.android.vedoohfarms.homepages.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Model.HomeListModel;
import app.android.vedoohfarms.util.adapters.HomeListAdapter;

public class FragmentHome extends Fragment {

    protected ArrayList<HomeListModel> homeData;

    protected String[] itemName;
    protected String[] aboutDetails;
    protected String[] nutritionDetails;
    protected int[] hmLikesCount;
    protected int[] hmLikedStatus;
    protected int[] mImages;

    HomeListModel hmModel;

    public FragmentHome() {
        // Required empty public constructor
    }

  /*  public static FragmentHome newInstance(String param) {
        FragmentHome fragment = new FragmentHome();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, param);
        fragment.setArguments(args);
        return fragment;
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvHome);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        HomeListAdapter adapter = new HomeListAdapter(getActivity(), homeData);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initDataset() {

        homeData = new ArrayList<>();

        mImages = new int[]{R.mipmap.images_apple, R.mipmap.images_banana, R.mipmap.images_broccoli, R.mipmap.images_cabbage,
                R.mipmap.images_chicken, R.mipmap.images_fish, R.mipmap.images_ginger, R.mipmap.images_goat, R.mipmap.images_grape,
        R.mipmap.images_lamb, R.mipmap.images_lemon, R.mipmap.images_onion, R.mipmap.images_orange, R.mipmap.images_pawpaw,
        R.mipmap.images_pear, R.mipmap.images_pepper, R.mipmap.images_pork, R.mipmap.images_sweet_potato, R.mipmap.images_tomatoes};

        itemName = new String[] {"Apple", "Banana", "Broccoli", "Cabbage",
                "Chicken", "Fish", "Ginger", "Goat", "Grape",
                "Lamb", "Lemon", "Onion", "Orange",
                "Paw Paw", "Pear", "Pepper", "Pork", "Sweet Potato", "Tomatoes"};

        aboutDetails = new String[] {"Apple is a fruit that is very sweet and crunchy. It is rarely grown in Nigeria but it's now readily grown in Jos. " +
                "It can only possibly grow in Jos alone. Most in the market are imported from Europe. It is currently not so affordable in the market. " +
                "It is not a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                "It is not a highly perishable fruit so it can last for some time before getting bad.",

                "Banana is a fruit that is very sweet and soft. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad.",

                "Broccoli is a fruit that is very sweet and soft. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Cabbage is a fruit that is very sweet and soft. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Chicken is bird meat that is very sweet and soft. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Fish is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Ginger is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Goat is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Grape is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Lamb is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Lemon is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Onion is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Orange is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Paw Paw is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Pear is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Pear is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Pepper is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Pork is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Sweet Potato is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company.",

                "Tomatoes is the most popular sea food. It is mostly grown in the South-East of Nigeria. " +
                        "But some in the market are still imported from Europe. It is available all round the year. " +
                        "It is a very popular fruit in Nigeria and still has potential of penetrating more people in the market. " +
                        "It is a highly perishable fruit so it can not last for a long time before getting bad." +
                        "a public liability company."};

        nutritionDetails = new String[] {"It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles.",

                "It gives Vitamins A, B, D, B1 and helps with some medical cases for healthy living. " +
                        "It helps to strengthen the white blood cells and red blood cells. Helps babies and the elderly to have strong muscles."};

        hmLikesCount = new int[] {20, 12, 76, 100, 77, 111, 93, 210, 241, 199, 190, 182, 122, 98, 105, 118, 97, 210, 204};

        hmLikedStatus = new int[] {0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0};


        for(int i=0; i < itemName.length; i++) {
            hmModel = new HomeListModel(mImages[i], itemName[i], aboutDetails[i], nutritionDetails[i], hmLikesCount[i], hmLikedStatus[i]);
            homeData.add(i, hmModel);
        }

    }

}
