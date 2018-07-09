package com.tomoed.ito.controller;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.tomoed.ito.R;
import com.tomoed.ito.model.Event;

import java.util.ArrayList;
import java.util.List;

public class NewEventFragment extends Fragment implements View.OnClickListener {
    EditText nameField, descriptionField;
    Spinner eventCategorySpinner;
    List<String> categories = new ArrayList<>();

    private static final String TAG = "New_Event_Fragment";

    // Required empty public constructor.
    public NewEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);

        nameField = (EditText) root.findViewById(R.id.text_event_name);
        descriptionField = (EditText) root.findViewById(R.id.text_description);

        root.findViewById(R.id.button_newEvent_close).setOnClickListener(this);
        root.findViewById(R.id.button_event_submit).setOnClickListener(this);

        eventCategorySpinner = (Spinner) root.findViewById(R.id.spinner_category);
        //Hard-coded for now.
        categories.add("Academics");
        categories.add("Dining");
        categories.add("Entertainment");
        categories.add("Physical Activity");
        categories.add("Shopping");
        SpinnerAdapter eventCategoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);

        eventCategorySpinner.setAdapter(eventCategoryAdapter);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_newEvent_close) {
            getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
        } else if (i == R.id.button_event_submit) {
            postNewEvent(compileEvent());
            getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
        }
    }

    public Event compileEvent() {
        Event event = new Event();
        event.setName(nameField.getText().toString());
        event.setDescription(descriptionField.getText().toString());
        return event;
    }

    public void postNewEvent(Event event) {
        //Add Event to Database.
        FirebaseDatabase.getInstance().getReference()
            .child("events")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .setValue(event)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d(TAG, "addOnComplete: Event created.");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "addOnFailure: Event not created.");
                }
            });
    }
}
