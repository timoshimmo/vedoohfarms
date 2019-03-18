package app.android.vedoohfarms.util.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.util.Model.NotificationModel;

/**
 * Created by freshfuturesmy on 26/09/17.
 */

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    Context mContext;
    private ArrayList<NotificationModel> mNotitficationList;

    public NotificationListAdapter(Context context, ArrayList<NotificationModel> nl) {
        this.mContext = context;
        this.mNotitficationList = nl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.notification_layout, parent, false);

        return new NotificationListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final NotificationModel model = mNotitficationList.get(position);

        ImageView notifyTitle = holder.notificationTitleImg;
        TextView notifyTimeAdded = holder.notificationTimeAddedTextView;
        TextView notifyDetails = holder.notificationDetailTextView;
        final LinearLayout notifyRow = holder.notificationRow;

        if(model.getNotificationTitle().equals("Activity")) {
            notifyTitle.setImageResource(android.R.drawable.ic_popup_reminder);
        }

        if(model.getNotificationTitle().equals("Update")) {
            notifyTitle.setImageResource(android.R.drawable.ic_dialog_alert);
        }
        if(model.getNotificationTitle().equals("Purchase")) {
            notifyTitle.setImageResource(android.R.drawable.ic_dialog_map);
        }
        if(model.getNotificationTitle().equals("Information")) {
            notifyTitle.setImageResource(android.R.drawable.ic_dialog_info);
        }

        if(model.getNotificationRead().equals("1")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notifyRow.setBackground(mContext.getResources().getDrawable(R.drawable.notification_row_read_background, null));
            }

            else {
                notifyRow.setBackground(mContext.getResources().getDrawable(R.drawable.notification_row_read_background));
            }

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                notifyRow.setBackground(mContext.getResources().getDrawable(R.drawable.notification_row_unread_background, null));
            }
            else {
                notifyRow.setBackground(mContext.getResources().getDrawable(R.drawable.notification_row_unread_background));
            }
        }

        notifyRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(notifyRow.getBackground() == mContext.getResources().getDrawable(R.color.custom_green_gray)) {
                    notifyRow.setBackground(mContext.getResources().getDrawable(R.drawable.notification_row_unread_background));
                }
            }
        });

        notifyTimeAdded.setText(model.getNotificationTimeAdded());
        notifyDetails.setText(model.getNotificationDetail());

    }

    @Override
    public int getItemCount() {
        return mNotitficationList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout notificationRow;
        ImageView notificationTitleImg;
        TextView notificationTimeAddedTextView;
        TextView notificationDetailTextView;

        ViewHolder(View itemView) {
            super(itemView);

            notificationRow = (LinearLayout) itemView.findViewById(R.id.notification_row);
            notificationTitleImg = (ImageView) itemView.findViewById(R.id.notification_title_icon);
            notificationTimeAddedTextView = (TextView) itemView.findViewById(R.id.txtTimeAdded);
            notificationDetailTextView = (TextView) itemView.findViewById(R.id.txtNotificationDetail);
        }
    }
}
