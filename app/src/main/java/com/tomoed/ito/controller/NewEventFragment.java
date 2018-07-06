package com.tomoed.ito.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.tomoed.ito.R;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventFragment extends Fragment {
    private ImageButton quitFrag;
    private Button submitEvent;

    public NewEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);

        quitFrag = root.findViewById(R.id.button_newEvent_close);
        submitEvent = root.findViewById(R.id.button_new_event);

        quitFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .remove(NewEventFragment.this).commit();
            }
        });

        submitEvent.setOnClickListener(new View.OnClickListener() {

            // TODO: Logic for implementing code for uploading onto Firebase here
            @Override
            public void onClick(View view) {


                //Leave at bottom. Closes the fragment
                getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
            }
        });

        return root;
    }
}
