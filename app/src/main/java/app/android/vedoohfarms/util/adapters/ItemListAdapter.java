package app.android.vedoohfarms.util.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.homepages.Activity.MainActivity;
import app.android.vedoohfarms.util.Dialogs.QtyDialogFragment;
import app.android.vedoohfarms.util.Model.ItemListModel;

/**
 * Created by freshfuturesmy on 21/09/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ArrayList<ItemListModel> mItemList;
    private Activity mActivity;

    public ItemListAdapter(Activity actvty, ArrayList<ItemListModel> ilm) {

        this.mItemList = ilm;
        this.mActivity = actvty;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_list_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ItemListModel model = mItemList.get(position);

        DecimalFormat dFormat = new DecimalFormat("00.00");
        DecimalFormat df = new DecimalFormat("0");

        int likedStatus = model.getLikedStatus();

        ImageButton btnInfo = holder.btnInfo;
        ImageView itemImg = holder.imgItem;
        TextView itemName = holder.itemNameTextView;
        TextView itemPrice = holder.itemPriceTextView;
        TextView itemFavNo = holder.itemFavNoTextView;
        TextView itemPurNo = holder.itemPurNoTextView;
        FloatingActionButton btnAddItemToBag = holder.btnOpenOrder;
        final ImageButton btnLikeItem = holder.btnLikeItem;
        ImageButton btnShareItem = holder.btnShareItem;

        final RelativeLayout descLayout = holder.itemDescriptionBody;
        TextView itemDesc = holder.itemDescTextView;
        ImageButton btnCloseDesc = holder.btnCloseDesc;

        if(likedStatus >= 1) {

            if(btnLikeItem.getVisibility() == View.VISIBLE) {
                btnLikeItem.setVisibility(View.GONE);
            }
          /*  btnLikeItem.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_clicked));
            btnLikeItem.setTag("CLICKED"); */
        }
        else {
            btnLikeItem.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_default));
            btnLikeItem.setTag("UNCLICKED");
        }

        String itemPriceValue = dFormat.format(model.getPrice());
        String favs = df.format(model.getItemFavorites());
        String purs = df.format(model.getItemPurchasese());

        Glide.with(mActivity).load(model.getItemImage()).into(itemImg);

        itemName.setText(model.getItemName());
        itemPrice.setText(itemPriceValue + " Naira per " + model.getItemUnit());
        itemDesc.setText(model.getItemDesc());
        itemFavNo.setText(favs + " " + mActivity.getResources().getString(R.string.favorites_string));
        itemPurNo.setText(purs + " " + mActivity.getResources().getString(R.string.purchases_string));

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (descLayout.getVisibility() == View.GONE) {
                    descLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        btnCloseDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (descLayout.getVisibility() == View.VISIBLE) {
                    descLayout.setVisibility(View.GONE);
                }
            }
        });

        btnLikeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnLikeItem.getTag() == "UNCLICKED") {

                    btnLikeItem.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_clicked));
                    btnLikeItem.setTag("CLICKED");
                }

                else {
                    btnLikeItem.setImageDrawable(ContextCompat.getDrawable(mActivity, R.drawable.ic_like_default));
                    btnLikeItem.setTag("UNCLICKED");
                }
            }
        });

        btnAddItemToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = ((MainActivity) mActivity).getSupportFragmentManager();
                QtyDialogFragment qtyFragments = QtyDialogFragment.newInstance(model.getItemUnit());

                FragmentTransaction transactions = fragmentManager.beginTransaction();

                qtyFragments.show(transactions, "orderDialog");

            }
        });

        btnShareItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share data");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Share farm products with friends!");
                mActivity.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
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

        ImageButton btnInfo;
        ImageView imgItem;
        TextView itemNameTextView;
        TextView itemPriceTextView;
        FloatingActionButton btnOpenOrder;
        ImageButton btnLikeItem;
        ImageButton btnShareItem;

        RelativeLayout itemDescriptionBody;
        TextView itemDescTextView;
        ImageButton btnCloseDesc;
        TextView itemFavNoTextView;
        TextView itemPurNoTextView;

        ViewHolder(View itemView) {
            super(itemView);

            btnInfo = (ImageButton) itemView.findViewById(R.id.imgBtnItemDesc);
            itemNameTextView = (TextView) itemView.findViewById(R.id.txtItemName);
            itemPriceTextView = (TextView) itemView.findViewById(R.id.txtItemPrice);
            imgItem = (ImageView) itemView.findViewById(R.id.imgItemImage);
            btnOpenOrder = (FloatingActionButton) itemView.findViewById(R.id.fab_open_order);
            btnLikeItem = (ImageButton) itemView.findViewById(R.id.btn_like_item);

            itemDescriptionBody = (RelativeLayout) itemView.findViewById(R.id.itemDesc);
            itemDescTextView = (TextView) itemView.findViewById(R.id.txtItemDesc);
            itemFavNoTextView = (TextView) itemView.findViewById(R.id.txt_no_of_likes);
            itemPurNoTextView = (TextView) itemView.findViewById(R.id.txt_no_items_sold);
            btnCloseDesc = (ImageButton) itemView.findViewById(R.id.btnCancelDesc);
            btnShareItem = (ImageButton) itemView.findViewById(R.id.imgBtnItemShare);

        }
    }

}
