package com.example.user.myapplication;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.CollationElementIterator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private CollationElementIterator mLatitudeText;
    private CollationElementIterator mLongitudeText;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {
        //set to google earth
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //get current location
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        //add marker to the buildings Per Buildings (One Per QR Scan)
        map.addMarker(new MarkerOptions().position(new LatLng(14.565448, 120.993215)).title("Lambda"));
        map.addMarker(new MarkerOptions().position(new LatLng(14.565637, 120.992596)).title("Mu"));
        map.addMarker(new MarkerOptions().position(new LatLng(14.566283, 120.993055)).title("Xi"));
        map.addMarker(new MarkerOptions().position(new LatLng(14.567004, 120.992645)).title("Rho"));
        map.addMarker(new MarkerOptions().position(new LatLng(14.566351, 120.992207)).title("Omicron"));

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        final Location location = locationManager.getLastKnownLocation(provider);

        // Zoom to initial position
        if (location != null) {

            LatLng target = new LatLng(location.getLatitude(), location.getLongitude());
            map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("You are here!").snippet("This is Your Starting Outdoor Position "));
        }

        //DLSU Map
        if (location != null) {

            LatLng target = new LatLng(14.565472, 120.992894);
            CameraPosition position = map.getCameraPosition();

            CameraPosition.Builder builder = new CameraPosition.Builder();
            builder.zoom(17);
            builder.tilt(0);
            builder.bearing(245);
            builder.target(target);
            map.animateCamera(CameraUpdateFactory.newCameraPosition(builder.build()));
        }

    }

}