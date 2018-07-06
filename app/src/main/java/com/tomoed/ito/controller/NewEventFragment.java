package com.tomoed.ito.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomoed.ito.R;

import static android.support.constraint.Constraints.TAG;

public class NewEventFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "New_Event_Fragment";

    // Required empty public constructor.
    public NewEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);

        root.findViewById(R.id.button_newEvent_close).setOnClickListener(this);
        root.findViewById(R.id.button_event_submit).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_newEvent_close) {
            getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
        } else if (i == R.id.button_event_submit) {
            getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
        }
    }
}
