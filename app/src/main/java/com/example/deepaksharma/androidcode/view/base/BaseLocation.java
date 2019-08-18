package com.example.deepaksharma.androidcode.view.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by deepaksharma
 */
public abstract class BaseLocation extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, PermissionsHandler.PermissionListener {
    private static String TAG = BaseLocation.class.getSimpleName();
    // The minimum distance to change Updates in meters
    private static long INTERVAL = 1000; // 1 sec
    // The minimum time between updates in milliseconds
    private static long FASTEST_INTERVAL = 1000; // 1 sec
    // The minimum distance to change Updates in meters
    private static long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters

    private static GoogleApiClient mGoogleApiClient;
    private boolean isUpdateLocation = false;

    private static LocationRequest mLocationRequest;
    private boolean isShowAddress = false;

    /**
     * provide user current location single time
     */
    public void getLocation() {
        stopLocationUpdates();
        checkPermission();
    }

    /**
     * provide user current location after a perticular time
     *
     * @param timeInterval - location update after time intervel in sec
     * @param fastInterval - fast time interval
     * @param displacement - location update after a perticular distance
     */
    public void getLocation(@NonNull long timeInterval, @NonNull long fastInterval, @NonNull long displacement) {
        stopLocationUpdates();
        this.isUpdateLocation = true;
        if (timeInterval > 0) INTERVAL = INTERVAL * timeInterval;
        if (fastInterval > 0) FASTEST_INTERVAL = FASTEST_INTERVAL * fastInterval;
//        if (displacement > 0)
            MIN_DISTANCE_CHANGE_FOR_UPDATES = MIN_DISTANCE_CHANGE_FOR_UPDATES * displacement;
        checkPermission();
    }

    /**
     * check Gps status & location permission
     */
    private void checkPermission() {
        try {
            List<String> locationList = new ArrayList<>();
            locationList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            locationList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            if (PermissionsHandler.requestMultiplePermission(this,locationList, this)) {
                checkGpsLocation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * check play services & enable gps
     */
    private void checkGpsLocation() {
        if (checkPlayServices()) {
            buildGoogleApiClient();
        } else {
            Toast.makeText(this, "Play service not available.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * check Google play services status.
     *
     * @return
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode, 1000).show();
            } else {
                Toast.makeText(getApplicationContext(), "This device is not supported.", Toast.LENGTH_LONG).show();
            }
            return false;
        }
        return true;
    }

    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        mLocationRequest = createLocationRequest();
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        Log.d(TAG, "onResult: SUCCESS");
//                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.d(TAG, "onResult: RESOLUTION_REQUIRED");
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(BaseLocation.this, 2000);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.d(TAG, "onResult: SETTINGS_CHANGE_UNAVAILABLE");
                        break;
                    case LocationSettingsStatusCodes.CANCELED:
                        Log.d(TAG, "onResult: CANCELED");
                        break;
                }
            }

        });
    }

    @Override
    public void onConnected(Bundle arg0) {
        // Once connected with google api, get the location
        startLocationUpdates();
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
//        Criteria criteria = new Criteria();
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(true);
//        criteria.setSpeedRequired(false);
//        criteria.setPowerRequirement(Criteria.POWER_LOW);
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        criteria.setCostAllowed(true);
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            buildGoogleApiClient();
        }
    }

    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        }
        cleanUpLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        Log.d(TAG, "onConnectionSuspended: ");
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "login  Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        mGoogleApiClient.connect();
    }


    //        [Permission Start]
    @Override
    public void onRequestPermissionsResult(@NonNull int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        PermissionsHandler.onRequestPermissionsResult(this,requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionGranted(List<String> mCustomPermission) {
        checkGpsLocation();
    }

    @Override
    public void onPermissionDenied(List<String> mCustomPermission) {

    }
//       [Permission Stop]

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        stopLocationUpdates();
//    }

    /**
     * gps is not enabled then it gives last location
     *
     * @return
     */
//    @SuppressLint("MissingPermission")
//    public android.location.Location getLastLocation() {
//        // Get last known recent location using new Google Play Services SDK (v11+)
//        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);
//
//        locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(android.location.Location location) {
//                if (location != null) {
//                    mLocation = location;
////                    Log.d(TAG, "onSuccess: not null check");
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d(TAG, "Error trying to get last GPS location");
//                e.printStackTrace();
//            }
//        });
//        return mLocation;
//    }

    /**
     * location update interval
     */
    private LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        if (isUpdateLocation) {
            mLocationRequest.setInterval(INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
            mLocationRequest.setSmallestDisplacement(MIN_DISTANCE_CHANGE_FOR_UPDATES);
        }
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    public void onLocationChanged(android.location.Location location) {
        if (isShowAddress) getAddress(location);
        else getCurrentLocation(location, null);
    }

    /**
     * provide user current address on the bases of lat long
     *
     * @param location
     */
    private void getAddress(@NonNull android.location.Location location) {
        String strAddress = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null) {
                android.location.Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAddress = strReturnedAddress.toString();
                Log.d(TAG, "Current address - " + strReturnedAddress.toString());
            } else {
                Log.d(TAG, "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "Can't get Address! - " + e.toString());
        }
        getCurrentLocation(location, strAddress);
    }

    protected abstract void getCurrentLocation(@NonNull android.location.Location location, @NonNull String address);

    // getting GPS status
    protected boolean isGpsEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    // getting network status
    protected boolean isNetworkEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    protected void isAddressEnabled(boolean showAddress) {
        isShowAddress = showAddress;
    }

    public void cleanUpLocation() {
        mGoogleApiClient = null;
        mLocationRequest = null;
        isShowAddress = false;
        isUpdateLocation = false;
        INTERVAL = 1000;
        FASTEST_INTERVAL = 1000;
        MIN_DISTANCE_CHANGE_FOR_UPDATES = 1;
    }

    /**
     * get distance between two lat long
     *
     * @param currlat
     * @param currlng
     * @param givenlat
     * @param givenlng
     * @return distane in miles
     */
    public static double checkDistance(double currlat, double currlng, double givenlat, double givenlng) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(givenlat - currlat);
        double dLng = Math.toRadians(givenlng - currlng);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(currlat)) * Math.cos(Math.toRadians(givenlat));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }

}
