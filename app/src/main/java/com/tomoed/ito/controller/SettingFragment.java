package com.tomoed.ito.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tomoed.ito.R;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tomoed.ito.R;
import com.tomoed.ito.model.User;

public class SettingFragment extends Fragment implements View.OnClickListener {
    @Override @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_settings, null);

        root.findViewById(R.id.button_logout).setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.button_logout) {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            Toast.makeText(getActivity(), "Signed out.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            FirebaseAuth.getInstance().signOut();
        }
    }
}