package app.android.vedoohfarms.util.Model;

/**
 * Created by freshfuturesmy on 21/09/17.
 */

public class HomeListModel {

    private int mImages;
    private String mItemName;
    private String mAboutDetail;
    private String mNutritionDetail;
    private int mCountLikes;
    private int mLikedStatus;

    public HomeListModel(int mImg, String name, String abDtl, String nd, int cl, int lkds) {
        mImages = mImg;
        mItemName = name;
        mAboutDetail = abDtl;
        mNutritionDetail = nd;
        mCountLikes = cl;
        mLikedStatus = lkds;
    }

    public HomeListModel() {
        super();
    }

    public int getItemImage() {
        return mImages;
    }

    public String getItemName() {
        return mItemName;
    }

    public String getAboutDetail() {
        return mAboutDetail;
    }

    public String getNutritionDetail() {
        return mNutritionDetail;
    }

    public int getCompCount() {
        return mCountLikes;
    }

    public int getLikedStatus() {
        return mLikedStatus;
    }

}
