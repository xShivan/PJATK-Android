package net.xshivan.excercise4;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class AddPlaceActivity extends AppCompatActivity {

    private LocationListener locationListener;

    private LocationManager locationManager;

    public void addPlace(View view) {
        Location location = null;
        // TODO: Handle no permission set
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            location = getLocation(LocationManager.GPS_PROVIDER);
        else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
            location = getLocation(LocationManager.NETWORK_PROVIDER);

        if (location == null) {
            // TODO: Error
            return;
        }
        else {
            EditText editTextName = (EditText)findViewById(R.id.editTextPlaceName);
            EditText editTextDescription = (EditText)findViewById(R.id.editTextPlaceDescription);

            String name = editTextName.getText().toString();
            if (name == null || name.isEmpty()) {
                // TODO: Cancel/Error
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
        locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        return locationManager.getLastKnownLocation(provider);
    }
}
