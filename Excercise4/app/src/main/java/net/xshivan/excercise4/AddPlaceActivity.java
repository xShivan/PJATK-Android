package net.xshivan.excercise4;

import android.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class AddPlaceActivity extends AppCompatActivity {

    private final String MSG_NO_PERMISSIONS = "Nie aktywowałeś uprawnień Internetowych oraz lokalizacji lub nie można pobrać lokalizacji.";

    private LocationListener locationListener;

    private LocationManager locationManager;

    public void addPlace(View view) {
        Location location = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            location = getLocation(LocationManager.GPS_PROVIDER);
        else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            location = getLocation(LocationManager.NETWORK_PROVIDER);

        if (location == null) {
            displayAlert(MSG_NO_PERMISSIONS);
            return;
        } else {
            EditText editTextName = (EditText) findViewById(R.id.editTextPlaceName);
            EditText editTextDescription = (EditText) findViewById(R.id.editTextPlaceDescription);

            String name = editTextName.getText().toString();
            if (name == null || name.isEmpty()) {
                displayAlert("Należy podać nazwę miejsca");
                return;
            }

            CoordinateManager.store(new Coordinates(location, name, editTextDescription.getText().toString()), getApplicationContext());
            Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
            startActivity(intent);
        }
    }

    public void cancelAddPlace(View view) {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

    }

    private Location getLocation(String provider) {
        Boolean accessFineLocation = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Boolean accessCoarseLocation = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        Boolean accessInternet = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;

        if (!accessFineLocation || !accessCoarseLocation || !accessInternet) {
            return null;
        }

        locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        return locationManager.getLastKnownLocation(provider);
    }

    private void displayAlert(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }
}
