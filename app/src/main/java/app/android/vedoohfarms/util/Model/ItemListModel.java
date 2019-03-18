package app.android.vedoohfarms.util.Model;

/**
 * Created by freshfuturesmy on 21/09/17.
 */

public class ItemListModel {

    private String mItemName;
    private double mItemPrice;
    private int mItemImage;
    private int mQuantity;
    private String mItemDescription;
    private String mItemUnit;
    private int mItemFavorites;
    private int mItemPurchases;
    private int mLikedStatus;


    public ItemListModel(String name, double price, int image, String dsc, String ut, int itmFav, int itmPur, int lks) {

        mItemName = name;
        mItemPrice = price;
        mItemImage = image;
        mItemDescription = dsc;
        mItemUnit = ut;
        mItemFavorites = itmFav;
        mItemPurchases = itmPur;
        mLikedStatus = lks;
    }

    public ItemListModel() {

        super();
    }

    public String getItemName() {
        return mItemName;
    }

    public String getItemDesc() {
        return mItemDescription;
    }

    public double getPrice() {
        return mItemPrice;
    }

    public int getItemImage() {
        return mItemImage;
    }

    public String getItemUnit() {
        return mItemUnit;
    }

    public int getItemFavorites() {
        return mItemFavorites;
    }

    public int getItemPurchasese() {
        return mItemPurchases;
    }

    public int getLikedStatus() {
        return mLikedStatus;
    }

 /*   public void setItemName(String name) {
        this.mName = name;
    }

    public void setPrice(double price) {
        this.mPrice = price;
    }

    public void setItemImage(int image) {
        this.mImage = image;
    }
    public void setItemDesc(String desc) {
        this.mDescription = desc;
    }

*/


}
