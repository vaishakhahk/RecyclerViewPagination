package com.designstring.recyclerpagination;

import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishakha on 13/10/16.
 */
public class CardAdapter extends RecyclerView.Adapter {
    private final int ITEM_VIEW_TYPE_BASIC = 0;
    private final int ITEM_VIEW_TYPE_FOOTER = 1;
    boolean value;

    private List<CardItem> cardItem;

    public CardAdapter(List<CardItem> cardItem) {
        this.cardItem = cardItem;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == ITEM_VIEW_TYPE_BASIC) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_items, parent, false);

            vh = new CardViewHolder(v);
        } else{
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.prograss_bar, parent, false);

            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    public void refreshAdapter(boolean value, List<CardItem> tempCardItem){
        this.value = value;
        this.cardItem = tempCardItem;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CardViewHolder) {

            CardItem singleCardItem = (CardItem) cardItem.get(position);

            ((CardViewHolder) holder).tvCard.setText(singleCardItem.getCard());

            ((CardViewHolder) holder).tvData.setText(singleCardItem.getData());

        } else {
            if (!value) {
                ((ProgressViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            } else ((ProgressViewHolder) holder).progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return cardItem.get(position) != null ? ITEM_VIEW_TYPE_BASIC : ITEM_VIEW_TYPE_FOOTER;
    }

    @Override
    public int getItemCount() {
        return cardItem == null ? 0: cardItem.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCard;

        public TextView tvData;

        public CardViewHolder(View v) {
            super(v);
            tvCard = (TextView) v.findViewById(R.id.tvName);

            tvData = (TextView) v.findViewById(R.id.tvEmailId);


        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        }
    }

}
