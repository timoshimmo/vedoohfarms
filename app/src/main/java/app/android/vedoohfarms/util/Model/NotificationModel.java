package app.android.vedoohfarms.util.Model;

/**
 * Created by freshfuturesmy on 26/09/17.
 */

public class NotificationModel {

    private String mNotifyTitle;
    private String mNotifyTimeAdded;
    private String mNotifyDetail;
    private String mNotifyRead;

    public NotificationModel(String ttl, String tad, String dsc, String nr) {

        mNotifyTitle = ttl;
        mNotifyTimeAdded = tad;
        mNotifyDetail = dsc;
        mNotifyRead = nr;

    }

    public NotificationModel() {
        super();
    }

    public String getNotificationTitle() {
        return mNotifyTitle;
    }

    public String getNotificationTimeAdded() {
        return mNotifyTimeAdded;
    }

    public String getNotificationDetail() {
        return mNotifyDetail;
    }

    public String getNotificationRead() {
        return mNotifyRead;
    }

     /*   public void setNotificationTitle(String title) {
        this.mName = name;
    }

    public void setNotificationTimeAdded(String timeAdded) {
        this.mPrice = price;
    }

    public void setNotificationDetail(String detail) {
        this.mImage = image;
    }


*/
}
