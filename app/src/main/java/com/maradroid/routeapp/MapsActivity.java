package com.maradroid.routeapp;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ArrayList<String> koordinate;
    private CameraPosition cameraPosition;
    PolylineOptions rectOptions;
    Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        rectOptions = new PolylineOptions();
        koordinate = new ArrayList<String>();
        koordinate = getIntent().getStringArrayListExtra("koordinate");
        Log.e("asfsfsdfsfef",""+koordinate.get(56));
        Log.e("mapeee", "" + koordinate.get(0));
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setZoomGesturesEnabled(true);
            cameraPosition = new CameraPosition.Builder().target(new LatLng(Double.valueOf(koordinate.get(0)), Double.valueOf(koordinate.get(1)))).zoom(8).build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        for(int i = 0; i<koordinate.size();i = i+2) {
            //mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(koordinate.get(i)), Double.valueOf(koordinate.get(i+1)))).title("Marker"));
            /*Polyline line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(Double.valueOf(koordinate.get(i)), Double.valueOf(koordinate.get(i+1))))
                    .width(5)
                    .color(Color.RED));*/
            rectOptions.add(new LatLng(Double.valueOf(koordinate.get(i)), Double.valueOf(koordinate.get(i+1))));
        }
        polyline = mMap.addPolyline(rectOptions);
    }
}
