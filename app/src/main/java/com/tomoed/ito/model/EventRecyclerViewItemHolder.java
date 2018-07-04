package com.tomoed.ito.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tomoed.ito.R;

public class EventRecyclerViewItemHolder extends RecyclerView.ViewHolder {

        private TextView eventTitleText = null;
        private ImageView eventImageView = null;

        public EventRecyclerViewItemHolder(View itemView) {
            super(itemView);

            if(itemView != null) {
                eventTitleText = itemView.findViewById(R.id.card_view_image_title);
                eventImageView = itemView.findViewById(R.id.card_view_image);
            }
        }

        public TextView getEventTitleText() {
            return eventTitleText;
        }

        public ImageView getEventImageView() {
            return eventImageView;
        }
    }