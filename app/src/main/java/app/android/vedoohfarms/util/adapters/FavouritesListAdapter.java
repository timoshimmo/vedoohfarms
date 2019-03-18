package app.android.vedoohfarms.util.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.ArrayList;

import app.android.vedoohfarms.R;
import app.android.vedoohfarms.homepages.Activity.MainActivity;
import app.android.vedoohfarms.util.Dialogs.QtyDialogFragment;
import app.android.vedoohfarms.util.Model.FavouritesModel;

/**
 * Created by freshfuturesmy on 18/10/17.
 */

public class FavouritesListAdapter extends RecyclerView.Adapter<FavouritesListAdapter.ViewHolder> {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ArrayList<FavouritesModel> mFavList;
    private Activity mActivity;

    public FavouritesListAdapter(Activity actvty, ArrayList<FavouritesModel> fm) {

        this.mFavList = fm;
        this.mActivity = actvty;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.favorite_list_layout, parent, false);

        return new FavouritesListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final FavouritesModel model = mFavList.get(position);

        DecimalFormat df = new DecimalFormat("0");

        ImageButton btnUnlike = holder.btnUnlikeItem;
        ImageView itemImg = holder.imgItem;
        TextView itemName = holder.itemNameTextView;
        TextView itemPrice = holder.itemPriceTextView;
        TextView farmName = holder.farmNameTextView;
        TextView scalesUnit = holder.scaleUnitTextView;
        TextView itemPurNo = holder.purCountTextView;
        TextView itemFavNo = holder.likesCountTextView;
        FloatingActionButton btnAddItemToBag = holder.btnOrder;

        String itemPriceValue = df.format(model.getItemPrice());
        String purchaseCount = df.format(model.getPurchaseCount());
        String favCount = df.format(model.getLikesCount());

        Glide.with(mActivity).load(model.getItemImg()).into(itemImg);

        itemName.setText(model.getItemName());
        itemPrice.setText(itemPriceValue + " Naira");
        scalesUnit.setText("per " + model.getUnitScale());
        farmName.setText(model.getFarmName());
        itemPurNo.setText(purchaseCount);
        itemFavNo.setText(favCount);

        btnAddItemToBag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((MainActivity) mActivity).getSupportFragmentManager();
                QtyDialogFragment qtyFragments = QtyDialogFragment.newInstance(model.getUnitScale());

                FragmentTransaction transactions = fragmentManager.beginTransaction();

                qtyFragments.show(transactions, "orderDialog");
            }
        });

        btnUnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFavList.remove(holder.getAdapterPosition());
                // this line animates what happens after delete
                notifyItemRemoved(holder.getAdapterPosition());
                Snackbar.make(v, "delete successful", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mFavList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton btnUnlikeItem;
        FloatingActionButton btnOrder;
        ImageView imgItem;
        TextView itemNameTextView;
        TextView itemPriceTextView;
        TextView farmNameTextView;
        TextView scaleUnitTextView;
        TextView likesCountTextView;
        TextView purCountTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            btnUnlikeItem = (ImageButton) itemView.findViewById(R.id.img_btn_fav_likes);
            imgItem = (ImageView) itemView.findViewById(R.id.img_fav_item);
            itemNameTextView = (TextView) itemView.findViewById(R.id.txtFavItemName);
            itemPriceTextView = (TextView) itemView.findViewById(R.id.txtFavItemPrice);
            farmNameTextView = (TextView) itemView.findViewById(R.id.txtFavFarmName);

            scaleUnitTextView = (TextView) itemView.findViewById(R.id.txtFavItemPriceUnit);
            purCountTextView = (TextView) itemView.findViewById(R.id.txtFavSoldCount);
            likesCountTextView = (TextView) itemView.findViewById(R.id.txtFavLikesCount);

            btnOrder = (FloatingActionButton) itemView.findViewById(R.id.fab_fav_open_order);

        }

    }
}
