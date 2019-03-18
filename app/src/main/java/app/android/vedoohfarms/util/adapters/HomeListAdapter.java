package app.android.vedoohfarms.util.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.homepages.Activity.FarmWebActivity;
import app.android.vedoohfarms.homepages.Activity.MainActivity;
import app.android.vedoohfarms.util.Dialogs.ItemDetailsDialogFragment;
import app.android.vedoohfarms.util.Model.HomeListModel;


/**
 * Created by freshfuturesmy on 06/10/17.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ArrayList<HomeListModel> mHomeList;
    private Activity mActivity;


    public HomeListAdapter(Activity atvt, ArrayList<HomeListModel> hlm) {

        this.mHomeList = hlm;
        this.mActivity = atvt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View itemView = inflater.inflate(R.layout.company_list_row_body, parent, false);

        return new HomeListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HomeListModel model = mHomeList.get(position);

        DecimalFormat df = new DecimalFormat("0");
        int likesStatus;

        ImageView imgItem = holder.imgItem;
        TextView itemNameTextView = holder.itemNameTextView;
        TextView itemDescTextView = holder.itemDescTextView;
        TextView companyLikesCountTextView = holder.companyLikesCountTextView;
        Button btnReadMore = holder.btnReadMore;
        ImageButton btnHomeShare = holder.btnHomeShare;
        final ImageButton btnHomeFav = holder.btnHomeFav;

        Glide.with(mActivity).load(model.getItemImage()).into(imgItem);

        String likesCount = df.format(model.getCompCount());

        itemNameTextView.setText(model.getItemName());
        itemDescTextView.setText(model.getAboutDetail());
        companyLikesCountTextView.setText(likesCount);
        likesStatus = model.getLikedStatus();


        if(likesStatus >= 1) {
            btnHomeFav.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_clicked));
            btnHomeFav.setTag("CLICKED");
        }

        else {
            btnHomeFav.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_favorite_small));
            btnHomeFav.setTag("UNCLICKED");
        }

        btnHomeFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnHomeFav.getTag() == "UNCLICKED") {

                    btnHomeFav.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_clicked));
                    btnHomeFav.setTag("CLICKED");
                }

                else {
                    btnHomeFav.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_favorite_small));
                    btnHomeFav.setTag("UNCLICKED");
                }
            }
        });

        btnHomeShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share data");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share farm products with friends!");
                mActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        btnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((MainActivity) mActivity).getSupportFragmentManager();
                ItemDetailsDialogFragment qtyFragments = ItemDetailsDialogFragment.newInstance(model.getAboutDetail(), model.getNutritionDetail());

                FragmentTransaction transactions = fragmentManager.beginTransaction();

                qtyFragments.show(transactions, "itemDetailsDialog");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView itemNameTextView;
        TextView itemDescTextView;
        TextView companyLikesCountTextView;
        Button btnReadMore;
        ImageButton btnHomeShare;
        ImageButton btnHomeFav;

        ViewHolder(View itemView) {
            super(itemView);

            imgItem = (ImageView) itemView.findViewById(R.id.img_home_item);
            itemNameTextView = (TextView) itemView.findViewById(R.id.txtHomeItemName);
            itemDescTextView = (TextView) itemView.findViewById(R.id.txtHomeItemDetails);
            companyLikesCountTextView = (TextView) itemView.findViewById(R.id.txt_small_favourites_count);
            btnReadMore = (Button) itemView.findViewById(R.id.btn_read_more);
            btnHomeShare = (ImageButton) itemView.findViewById(R.id.img_btn_share_small);
            btnHomeFav = (ImageButton) itemView.findViewById(R.id.img_btn_fav_small);
        }
    }
}
