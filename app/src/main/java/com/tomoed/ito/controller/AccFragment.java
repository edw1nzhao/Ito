package com.tomoed.ito.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tomoed.ito.R;

public class AccFragment extends Fragment {

    private static final String TAG = "Acc_Fragment";

    // Required empty public constructor.
    public AccFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_acc, container, false);

        return root;
    }

}
