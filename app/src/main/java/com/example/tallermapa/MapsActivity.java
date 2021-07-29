package com.example.tallermapa;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    int REQ_PERMISSION = 0;
    double longitud;
    double latitud;
    boolean isInfoWindowShow = false;
    AeropuertoController ec;
    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ec = new AeropuertoController(this);
        String ID = getIntent().getStringExtra("id");
        c = ec.allAeropuerto();
        if(ID == null){
            c = ec.allAeropuerto();
        }else {
            c = ec.getAeropuertoByID(ID);
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Toast.makeText(getApplicationContext(), location.getLatitude() + "," + location.getLongitude(),
                                    Toast.LENGTH_LONG).show();

                        }
                    }
                });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION },
                    1);
        }


        while (c.moveToNext()){

            Double lat = Double.parseDouble(c.getString(5));
            Double longitud = Double.parseDouble(c.getString(6));
            LatLng mark = new LatLng(lat,longitud);
            MarkerOptions marker = new MarkerOptions()
                    .position(mark)
                    .title(c.getString(1))
                    .snippet(c.getString(2));
            Marker bq = mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mark, 20f));

        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                LatLng marcador = marker.getPosition();
                if (!isInfoWindowShow){
                    Toast.makeText(getApplicationContext(), marker.getTitle() + "Ubicacion :"+
                            marcador.latitude + "," + marcador.longitude, Toast.LENGTH_LONG).show();
                    marker.showInfoWindow();
                    isInfoWindowShow = true;
                }
                else{
                    marker.hideInfoWindow();
                    isInfoWindowShow = false;
                }
                return true;
            }
        });

    }

}