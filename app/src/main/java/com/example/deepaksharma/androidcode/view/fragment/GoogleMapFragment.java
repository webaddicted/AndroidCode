package com.example.deepaksharma.androidcode.view.fragment;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentGoogleMapBinding;
import com.example.deepaksharma.androidcode.global.DirectionsJSONParser;
import com.example.deepaksharma.androidcode.global.PermissionsHandler;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.example.deepaksharma.androidcode.viewModel.home.HomeViewModel;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GoogleMapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    public static final String TAG = GoogleMapFragment.class.getSimpleName();
    public static final int REQUEST_PLACE = 1;
    private FragmentGoogleMapBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private GoogleMap mGoogleMap;
    private Marker mMarker;
    private Location mLocation;
    private List<LatLng> mLatLongList = new ArrayList<>();

    public static GoogleMapFragment getInstance(Bundle bundle) {
        GoogleMapFragment fragment = new GoogleMapFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_google_map;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentGoogleMapBinding) binding;
        mHomeViewModel = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);
        init();
        initailizeMap();
        clickListener();
    }

    public void initailizeMap() {
        if (PermissionsHandler.checkPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (mGoogleMap == null) {
                SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
            }
        }
    }

    private void init() {
    }

    private void clickListener() {
        mBinding.btnGoogleMap.setOnClickListener(this);
        mBinding.btnCreatePolyline.setOnClickListener(this);
        mBinding.edtSearch.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.google_map_title));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_google_map:
                googleMap();
                break;
            case R.id.btn_create_polyline:
                createPolyLine();
                break;
            case R.id.edt_search:
                placeSearch();
                break;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mGoogleMap = googleMap;
        mGoogleMap.setOnMapClickListener(this);
    }

    private void googleMap() {
        getLocation();
        mHomeViewModel.mCurrentLocation.observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                if (location != null) {
                    mLocation = location;
                    drawMarker(location);
                }
            }
        });
//        mHomeViewModel.mCurrentAddress.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });
    }

    private void drawMarker(Location location) {
        final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        if (mMarker != null) mMarker.remove();
        mMarker = mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("This is Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void createPolyLine() {
        if (PermissionsHandler.checkPermission(getActivity(),Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (mGoogleMap != null && mLocation != null) {

            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (latLng != null) {
            Marker marker = mGoogleMap.addMarker(new MarkerOptions().position(latLng).title("This is Me").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            mLatLongList.add(latLng);
            if (mLatLongList.size() == 2) drawPolyLine();
            if (mLatLongList.size() == 3) mLatLongList.clear();
        }
    }

    private void drawPolyLine() {
        String url = getDirectionsUrl(mLatLongList.get(0), mLatLongList.get(1));

        DownloadTask downloadTask = new DownloadTask();

        // Start downloading json data from Google Directions API
        downloadTask.execute(url);

    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getResources().getString(R.string.google_key);


        return url;
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList points = null;
            PolylineOptions lineOptions = null;
//            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(12);
                lineOptions.color(Color.RED);
                lineOptions.geodesic(true);

            }

// Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null)
                mGoogleMap.addPolyline(lineOptions);
        }
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private void placeSearch() {
//        this api worlking fine
//        https://stackoverflow.com/questions/31973029/how-to-use-autocomplete-for-google-places-in-android
//        https://maps.googleapis.com/maps/api/place/autocomplete/json?input="
//        + URLEncoder.encode("jaipur rajasthan",
//                "UTF-8")
//                + "&types=geocode&language=en&sensor=true&key=AIzaSyAbO1hDRhhQKHs945QBsNt0yCzBXG3X5Fk"

        try {
            Intent intent = new PlaceAutocomplete
                    .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .build(getActivity());
            startActivityForResult(intent, REQUEST_PLACE);
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.d(TAG, "placeSearch: "+e.toString());
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
            Log.d(TAG, "placeSearch: "+e.toString());
        }
    }
}
