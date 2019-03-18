package app.android.vedoohfarms.util.Model;

/**
 * Created by freshfuturesmy on 18/10/17.
 */

public class FavouritesModel {

    private String mItemName;
    private String mFarmName;
    private double mItemPrice;
    private String mUnitScale;
    private int mItemImg;
    private int mCountPurchased;
    private int mCountLikes;

    public FavouritesModel(String itn, String fmn, double itpr, String usc, int imgs, int pct, int clk) {

        mItemName = itn;
        mFarmName = fmn;
        mItemPrice = itpr;
        mUnitScale = usc;
        mItemImg = imgs;
        mCountPurchased = pct;
        mCountLikes = clk;
    }

    public FavouritesModel() {
        super();
    }

    public String getItemName() {
        return mItemName;
    }

    public String getFarmName() {
        return mFarmName;
    }

    public double getItemPrice() {
        return mItemPrice;
    }

    public String getUnitScale() {
        return mUnitScale;
    }

    public int getItemImg() {
        return mItemImg;
    }

    public int getPurchaseCount() {
        return mCountPurchased;
    }

    public int getLikesCount() {
        return mCountLikes;
    }

}
