package com.tomoed.ito.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tomoed.ito.R;
import com.tomoed.ito.model.Event;
import com.tomoed.ito.model.EventRecyclerViewDataAdapter;
import com.tomoed.ito.model.EventRecyclerViewItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListFragment extends Fragment {
    private List<EventRecyclerViewItem> eventItemList = null;
    private Map<String, Integer> categoryImageMap = new HashMap<>();

    private static final String TAG = "List_Fragment";

    @Override @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, null);

        createImageMapping();
        initializeEventList(root);
        return root;
    }

    private void initializeEventList(final View root) {
        if (eventItemList == null) {
            eventItemList = new ArrayList<>();
        }
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("events");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot templateSnapshot : dataSnapshot.getChildren()){
                    for (DataSnapshot snap: templateSnapshot.getChildren()) {
                        Event event = (Event) snap.getValue(Event.class);
                        eventItemList.add(new EventRecyclerViewItem(event.getName(), categoryImageMap.get(event.getCategory())));
                    }
                }
                RecyclerView eventRV = root.findViewById(R.id.card_view_recycler_list);
                GridLayoutManager gridLM = new GridLayoutManager(getActivity(), 2);
                eventRV.setLayoutManager(gridLM);

                EventRecyclerViewDataAdapter eventDA = new EventRecyclerViewDataAdapter(eventItemList);
                eventRV.setAdapter(eventDA);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.toString());
            }
        });
    }

    public void createImageMapping() {
        categoryImageMap.put("Academics", R.drawable.splash_study_xhdpi);
        categoryImageMap.put("Dining", R.drawable.splash_dinner_xhdpi);
        categoryImageMap.put("Entertainment", R.drawable.splash_board_xhdpi);
        categoryImageMap.put("Physical Activity", R.drawable.splash_sports_xhdpi);
        categoryImageMap.put("Shopping", R.drawable.splash_shopping_xhdpi);
        categoryImageMap.put("Other", R.drawable.splash_other_xhdpi);
    }
}