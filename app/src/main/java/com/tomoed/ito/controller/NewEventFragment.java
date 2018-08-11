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
import android.widget.TimePicker;
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
import java.util.UUID;

public class NewEventFragment extends Fragment implements View.OnClickListener {
    EditText nameField, descriptionField;
    Spinner eventCategorySpinner;
    TimePicker eventStartTimePicker;
    List<String> categories = new ArrayList<>();

    private static final String TAG = "New_Event_Fragment";

    public NewEventFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new_event, container, false);

        nameField = root.findViewById(R.id.text_event_name);
        descriptionField = root.findViewById(R.id.text_description);
        eventStartTimePicker = root.findViewById(R.id.tp_timepicker);

        root.findViewById(R.id.button_newEvent_close).setOnClickListener(this);
        root.findViewById(R.id.button_event_submit).setOnClickListener(this);

        categorySpinnerSetup(root);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_newEvent_close) {
            getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
        } else if (i == R.id.button_event_submit) {
            if(!isEmpty(nameField.getText().toString()) && !isEmpty(descriptionField.getText().toString())) {
                postNewEvent(compileEvent());
                getFragmentManager().beginTransaction().remove(NewEventFragment.this).commit();
            } else{
                Toast.makeText(getContext(), "You must fill out all the fields.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void categorySpinnerSetup(View root) {
        eventCategorySpinner = (Spinner) root.findViewById(R.id.spinner_category);

        categories.add("Academics");
        categories.add("Dining");
        categories.add("Entertainment");
        categories.add("Physical Activity");
        categories.add("Shopping");
        categories.add("Other");

        SpinnerAdapter eventCategoryAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        eventCategorySpinner.setAdapter(eventCategoryAdapter);
        eventCategorySpinner.setSelection(0);
    }

    public Event compileEvent() {
        Event event = new Event();
        event.setName(nameField.getText().toString());
        event.setDescription(descriptionField.getText().toString());
        event.setCategory((String) eventCategorySpinner.getSelectedItem());

        return event;
    }

    public void postNewEvent(Event event) {
        FirebaseDatabase.getInstance().getReference()
            .child("events")
            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
            .child(generateString())
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

    private boolean isEmpty(String string){
        return string.equals("");
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
}
