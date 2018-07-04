package com.tomoed.ito.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tomoed.ito.R;
import com.tomoed.ito.model.EventRecyclerViewDataAdapter;
import com.tomoed.ito.model.EventRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    private static final String TAG = "List_Fragment";
    private List<EventRecyclerViewItem> eventItemList = null;

    @Override @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, null);

        initializeEventList();

        RecyclerView eventRV = root.findViewById(R.id.card_view_recycler_list);
        GridLayoutManager gridLM = new GridLayoutManager(getActivity(), 2);
        eventRV.setLayoutManager(gridLM);

        EventRecyclerViewDataAdapter eventDA = new EventRecyclerViewDataAdapter(eventItemList);
        eventRV.setAdapter(eventDA);
        return root;
    }

    /**
     * TODO: Implement all events currently on server
     * */
    private void initializeEventList() {
        if (eventItemList == null) {
            eventItemList = new ArrayList<>();
        }

        eventItemList.add(new EventRecyclerViewItem("Anime", R.drawable.splash_anime_xhdpi));
        eventItemList.add(new EventRecyclerViewItem("Anime", R.drawable.splash_anime_xhdpi));
    }
}