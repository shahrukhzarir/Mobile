package com.shahrukhzarir.lab_8;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
/**
 * Created by 100559792 on 11/20/2017.
 */

public class MainActivity extends Activity implements LocationListener {
    private LocationManager locationManager;

    private double latitude, longitude;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGeolocation();

    }

    private void setupGeolocation(){
        verifyGeolocationPermission();
    }

    private static final int REQUEST_GEOLOCATION_PERMS = 1;

    private void verifyGeolocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // TODO:  Show the user rationale and ask for permission
            }

            String[] perms = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            requestPermissions(perms, REQUEST_GEOLOCATION_PERMS);
        } else {
            // geolocation permission granted, so request location updates
            verifyGeolocationEnabled();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String[] perms,
                                           int[] results) {
        if (requestCode == REQUEST_GEOLOCATION_PERMS) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                // geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
            }
        }
    }

    private void verifyGeolocationEnabled() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        // check if geolocation is enabled in settings
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // TODO:  Request location updates
            requestLocationUpdates();
        } else {
            // show the settings app to let the user enable it
            String locationSettings = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGeoloc = new Intent(locationSettings);
            startActivity(enableGeoloc);

            // Note:  startActivityForResult() may be better here
        }
    }

    private void requestLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String recommendedProvider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(recommendedProvider,
                    5000, 10, this);
            Log.i("MapsDemo", "requestLocationUpdates()");
        }
    }

    public void onLocationChanged(Location location) {
        Log.i("MapsDemo", "Location changed: " + location);

        // remember the coordinates for the map activity's backup location
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        if(Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            // geocode the result - get the location name
            try {
                List<Address> details = geocoder.getFromLocation(latitude, longitude, 1);

                // show the location in the UI
                if (details.size() > 0) {
                    TextView address1 = (TextView) findViewById(R.id.addresstxt1);
                    address1.setText(details.get(0).getAddressLine(0));
                    TextView address2 = (TextView) findViewById(R.id.addresstxt2);
                    address2.setText(details.get(0).getAddressLine(1));
                    TextView city = (TextView) findViewById(R.id.citytxt);
                    city.setText(details.get(0).getLocality());
                    TextView province = (TextView) findViewById(R.id.provincetxt);
                    province.setText(details.get(0).getAdminArea());
                    TextView country = (TextView) findViewById(R.id.countrytxt);
                    country.setText(details.get(0).getCountryName());
                    TextView postalCode = (TextView) findViewById(R.id.postalcodetxt);
                    postalCode.setText(details.get(0).getPostalCode());
                    TextView phone = (TextView) findViewById(R.id.phonetxt);
                    phone.setText(details.get(0).getPhone());
                    TextView url = (TextView) findViewById(R.id.urltext);
                    url.setText(details.get(0).getUrl());
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }


    }


    public void onProviderEnabled(String provider) {
        Log.i("MapsDemo", "Provider enabled: " + provider);
    }

    public void onProviderDisabled(String provider) {
        Log.i("MapsDemo", "Provider disabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("MapsDemo", "Provider ("+provider+") status changed: " + status);
    }

}
