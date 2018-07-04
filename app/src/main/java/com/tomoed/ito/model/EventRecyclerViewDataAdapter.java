package com.tomoed.ito.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.tomoed.ito.R;

public class EventRecyclerViewDataAdapter extends RecyclerView.Adapter<EventRecyclerViewItemHolder> {

    private List<EventRecyclerViewItem> eventItemList;

    public EventRecyclerViewDataAdapter(List<EventRecyclerViewItem> eventItemList) {
        this.eventItemList = eventItemList;
    }

    @Override
    public EventRecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View eventItemView = layoutInflater.inflate(R.layout.activity_card_view_item, parent, false);

        final TextView eventTitleView = eventItemView.findViewById(R.id.card_view_image_title);
        final ImageView eventImageView = eventItemView.findViewById(R.id.card_view_image);

        eventImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = eventTitleView.getText().toString();
            }
        });

        EventRecyclerViewItemHolder ret = new EventRecyclerViewItemHolder(eventItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(EventRecyclerViewItemHolder holder, int position) {
        if(eventItemList!=null) {
            EventRecyclerViewItem eventItem = eventItemList.get(position);

            if(eventItem != null) {
                holder.getEventTitleText().setText(eventItem.getEventName());
                holder.getEventImageView().setImageResource(eventItem.getEventImageId());
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(eventItemList!=null)
        {
            ret = eventItemList.size();
        }
        return ret;
    }
}