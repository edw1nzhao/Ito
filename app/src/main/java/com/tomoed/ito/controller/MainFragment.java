package com.tomoed.ito.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.tomoed.ito.R;

public class MainFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap googleMap;
    private LocationRequest mLocationRequest;

    private long UPDATE_INTERVAL = 10 * 1000;  // 10 seconds
    private long FASTEST_INTERVAL = 2000; // 2 seconds

    private static final String TAG = "Main_Fragment";

    @Override @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, null);

        root.findViewById(R.id.button_new_event).setOnClickListener(this);

        startLocationUpdates();
        mapSetup(root, savedInstanceState);

        return root;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.button_new_event) {
            Fragment f = null;
            try {
                Class c = NewEventFragment.class;
                f = (Fragment) c.newInstance();
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.flContent, f).commit();
        }
    }

    // Methods related to Google Maps API.
    @Override
    public void onMapReady(GoogleMap mMap) {
        googleMap = mMap;
    }

    public void mapSetup(View root, Bundle savedInstanceState) {
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);
    }

    // Methods relating to LocationServices API.
    protected void startLocationUpdates() {
        // Create the location request to start receiving updates.
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);

        // Create LocationSettingsRequest object using location request.
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied.
        SettingsClient settingsClient = LocationServices.getSettingsClient(getActivity());
        settingsClient.checkLocationSettings(locationSettingsRequest);

        LocationServices.getFusedLocationProviderClient(getActivity()).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {

                    // Logs on location change.
                    onLocationChanged(locationResult.getLastLocation());

                    // Retrieves last device location.
                    Location lastLocation = locationResult.getLastLocation();

                    // Places Google Maps pin at the last device location.
                    String snippet = "Lat: " + Double.toString(lastLocation.getLatitude()) + ", Long: " + Double.toString(lastLocation.getLongitude());
                    LatLng location = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    googleMap.addMarker(new MarkerOptions()
                            .position(location)
                            .title("Current Location")
                            .snippet(snippet));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(location).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            },
            Looper.myLooper());
    }

    public void onLocationChanged(Location location) {
        String msg = "Updated Location: " + Double.toString(location.getLatitude()) + "," + Double.toString(location.getLongitude());
        Log.d(TAG, msg);
    }

    public void getLastLocation() {
        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        locationClient.getLastLocation()
            .addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // GPS location can be null if GPS is switched off.
                    if (location != null) {
                        onLocationChanged(location);
                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "Error trying to get last GPS location.");
                    e.printStackTrace();
                }
            });
    }

    private boolean checkPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions();
            return false;
        }
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
    }
}