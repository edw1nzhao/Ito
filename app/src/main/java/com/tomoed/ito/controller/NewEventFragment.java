package com.tomoed.ito.controller;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.tomoed.ito.R;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventFragment extends Fragment implements View.OnClickListener{
    public NewEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_newEvent_close) {
            Log.d(TAG, "PENSIDFHLSKDJFSDKFJLJS");
            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
        }
    }
}
