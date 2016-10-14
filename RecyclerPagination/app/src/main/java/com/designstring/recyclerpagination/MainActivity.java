package com.designstring.recyclerpagination;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List<CardItem> cardItem,tempCardItem = new ArrayList<>();
    boolean isLoading;
    private int visibleThreshold = 5;
    int totalItemCount,lastVisibleItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        cardItem = new ArrayList<>();
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        for (int i = 1; i <= 50 ; i++) {
            cardItem.add(new CardItem("Card " + i, "Data " + i + ""));
        }

        for (int i =0 ; i<20 ;i++) {
            tempCardItem.add(cardItem.get(i));
        }

        mAdapter = new CardAdapter(tempCardItem);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = mLayoutManager.getItemCount();
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    loadData();
                    isLoading = true;
                }

            }
        });
    }

    private void loadData() {
        tempCardItem.add(null);
        mAdapter.notifyItemInserted(tempCardItem.size() - 1);
        Log.e("SIZE", "cardItem " + cardItem.size() + "; cardTotal " + tempCardItem.size());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tempCardItem.remove(tempCardItem.size() - 1);
                mAdapter.notifyItemRemoved(tempCardItem.size());
                int Total = cardItem.size();
                int start = tempCardItem.size();
                int end = start + 20;
                int size = (Total > end) ? end : Total;

                for (int i = start; i < size; i++) {
                    tempCardItem.add(cardItem.get(i));
                }
                isLoading = (Total == size);
                mAdapter.refreshAdapter(isLoading,tempCardItem);
            }
        }, 2000);

    }

}
