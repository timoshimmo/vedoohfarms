package app.android.vedoohfarms.util.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.android.vedoohfarms.homepages.Fragments.FragmentFavourite;
import app.android.vedoohfarms.homepages.Fragments.FragmentHome;
import app.android.vedoohfarms.homepages.Fragments.FragmentSearch;

/**
 * Created by freshfuturesmy on 13/09/17.
 */

public class HomeFragmentPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public HomeFragmentPageAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FragmentHome();
        } else if (position == 1){
            return new FragmentSearch();
        }
        else {
            return new FragmentFavourite();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
