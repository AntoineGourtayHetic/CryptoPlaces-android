package com.antoinegourtay.mob_e16_android.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.antoinegourtay.mob_e16_android.CryptoPlaceApplication;
import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.activities.MainActivity;
import com.antoinegourtay.mob_e16_android.response.PlacesResponse;
import com.antoinegourtay.mob_e16_android.response.SinglePlaceResponse;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.neopixl.spitfire.listener.RequestListener;
import com.neopixl.spitfire.request.BaseRequest;

import static android.content.Context.LOCATION_SERVICE;


public class MapFragment extends Fragment implements OnMapReadyCallback {


    private static final String APP_NAME = "CryptoPlaces";
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 102;

    private GoogleMap mMap;

    private LocationManager locationManager;
    private LocationListener locationListener;
    private static double latitude;
    private static double longitude;
    private LatLng currentPosition;

    private boolean onLaunch = true;
    private MapView mMapView;
    private MainActivity mainActivity;

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance() {

        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity) getActivity();
        mainActivity.getSupportActionBar().setTitle("Lieux");


        /**
         * Location management
         */

        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);

        //When user location changes
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.clear();
                    }
                });

                Log.d(APP_NAME, "location : " + location);

                //Getting the position from the LocationListener
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.d(APP_NAME, "latitude : " + latitude + " - longitude : " + longitude);

                currentPosition = new LatLng(latitude, longitude);

                //Animating the camera to the current position
                if (onLaunch) {
                    mMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 10));
                        }
                    });
                    onLaunch = false;
                }

                mMapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        //Request to get all the places from the API
                        BaseRequest<PlacesResponse> request =
                                new BaseRequest.Builder<>(Request.Method.GET
                                        , "https://api.myjson.com/bins/wfh57"
                                        , PlacesResponse.class)
                                        .listener(new RequestListener<PlacesResponse>() {
                                            @Override
                                            public void onSuccess(Request request, NetworkResponse response, PlacesResponse result) {
                                                for (SinglePlaceResponse singlePlaceResponse : result.getResults()) {
                                                    LatLng markerLatLng = new LatLng(
                                                            Double.parseDouble(singlePlaceResponse.getPosition().getLatitude()),
                                                            Double.parseDouble(singlePlaceResponse.getPosition().getLongitude())
                                                    );

                                                    mMap.addMarker(
                                                            new MarkerOptions()
                                                                    .title(singlePlaceResponse.getName())
                                                                    .position(markerLatLng)
                                                    );
                                                }
                                            }

                                            @Override
                                            public void onFailure(Request request, NetworkResponse response, VolleyError error) {
                                                Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .build();


                        CryptoPlaceApplication cryptoPlaceApplication =
                                (CryptoPlaceApplication) getActivity().getApplication();
                        cryptoPlaceApplication.getRequestQueue().add(request);
                    }
                });

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                provider.toString();
            }

            @Override
            public void onProviderEnabled(String provider) {
                provider.toString();
            }

            @Override
            public void onProviderDisabled(String provider) {
                provider.toString();
            }
        };

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        } else {
            long minTime = 10;
            float minDistance = 10.0f;

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, locationListener);

        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                // For dropping a marker at a point on the Map
                LatLng sydney = new LatLng(-34, 151);
                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

            }
            // Inflate the layout for this fragment

        });
        return rootView;

    }
}

