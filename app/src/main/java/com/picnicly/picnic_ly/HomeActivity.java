package com.picnicly.picnic_ly;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.text.Html;
import android.text.Spanned;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.BitmapDescriptor;

import android.widget.TextView;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.zip.Inflater;

import android.widget.TextView;




/**
 * Created by Giovy on 28/01/2017.
 */


import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.picnicly.picnic_ly.dummy.DummyContent;

import static android.R.attr.data;
import static android.R.attr.drawable;
import static com.picnicly.picnic_ly.MainActivity.PROFILE_IMAGE_URL;


public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener{
    GoogleMap m;
    Marker marker;
    Marker prevmark;
    private SupportMapFragment map;
    public static final String TAG = "HomeActivity";
    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView mDrawer;
    private DrawerLayout mDrawerLayout;
    SupportMapFragment mMapFragment;
    private ActionBarDrawerToggle mDrawerToggle;
    private Button search;
    int PLACE_PICKER_REQUEST = 1;
    String placeName, address;
    private static final float ALPHA_DIM_VALUE = 0.1f;
    private GoogleApiClient mGoogleApiClient;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Inflater inflater = null;

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_home);
        mGoogleApiClient = ((MyApplication) getApplication()).getGoogleApiClient(HomeActivity.this, this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView user = (TextView) findViewById(R.id.user);
        TextView mail = (TextView) findViewById(R.id.textView);

        if(mGoogleApiClient!=null && mGoogleApiClient.isConnected()){
            /*String profileDisplayName = returnValueFromBundles(MainActivity.PROFILE_DISPLAY_NAME);
            String profileUserEmail = returnValueFromBundles(MainActivity.PROFILE_USER_EMAIL);
            String profileImageLink = returnValueFromBundles(MainActivity.PROFILE_IMAGE_URL);
            Picasso.with(HomeActivity.this).load(profileImageLink).into(img);
            assert user != null;
            user.setText(profileDisplayName);
            assert mail != null;
            mail.setText(profileUserEmail);*/

        }






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        android.app.FragmentManager fm = getFragmentManager();
        //fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();
        /*LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        DrawerLayout drawer = (DrawerLayout) inflater.inflate(R.layout.decor, null); // "null" is important.

        // HACK: "steal" the first child of decor view
        ViewGroup decor = (ViewGroup) getWindow().getDecorView();
        View child = decor.getChildAt(0);
        decor.removeView(child);
        FrameLayout container = (FrameLayout) drawer.findViewById(R.id.container); // This is the container we defined just now.
        container.addView(child);


        // Make the drawer replace the first child
        decor.addView(drawer);*/


        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        /*map.getMapAsync(this);*/
        initializeMap();
    }



    private void initializeMap() {
        if (m == null) {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);


            // check if map is created successfully or not
            /*if (m == null) {
                Toast.makeText(MainActivity.this,  "Creando la mappa", Toast.LENGTH_SHORT).show();
            }*/
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
        m = map;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new
                LatLng(49.39, -124.83), 20));

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }
        map.setMyLocationEnabled(true);

        /*map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(47.17, 27.5699), 16));
        map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)).anchor(0.0f, 1.0f) //
Anchors the marker on the bottom left
                .position(new LatLng(47.17, 27.5699))); //Iasi, Romania*/
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Toast.makeText(getApplicationContext(), latLng.toString(), Toast.LENGTH_LONG).show();
            }
        });

        //To setup location manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //To request location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 1, this);
        LatLng sydney = new LatLng(27.746974, 85.301582);
        LatLng tv = new LatLng(45.6662855, 12.2420720);
        LatLng quinto = new LatLng(45.6562880, 12.166667);
        m.addMarker(new MarkerOptions().position(sydney).title("Kathmandu, Nepal"));
        m.addMarker(new MarkerOptions().position(tv).title("Tv, tv"));
        //m.addMarker(new MarkerOptions().position(quinto).title("Tv, tvv"));
        //MarkerOptions marker = new MarkerOptions().position(quinto).title("Hello Maps");

// Changing marker icon
        //marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.picnic_table));
        m.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.picnic_table)).anchor(0.0f, 1.0f).position(quinto).title("Quinto"));
        m.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.forest)).anchor(0.0f, 1.0f).position(tv).title("Tv"));

// adding marker
        //m.addMarker(marker);
    }

    @Override
    public void onLocationChanged(Location location) {
        //m.clear();
        //To clear map data
        //To hold location
        if(prevmark!=null){
            prevmark.remove();
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //To create marker in map
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("My Location");
        //adding marker to the map
        prevmark = m.addMarker(markerOptions);

        //opening position with some zoom level in the map
        m.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
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

    public void onMapSearch(View view) {
        EditText locationSearch = (EditText) findViewById(R.id.editText);
        String location = locationSearch.getText().toString();
        List<Address> addressList = null;
        if(marker!=null){
            marker.remove();
        }

        if (location != null || !location.equals("")) {
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(location, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                marker = m.addMarker(new MarkerOptions().position(latLng).title("Marker"));
                m.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            } else {
                Toast toast = Toast.makeText(this, "Per favore, inserisci un altro indirizzo.",
                        Toast.LENGTH_SHORT);
                toast.show();
            }

        }


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.app.FragmentManager fm = getFragmentManager();
        EditText edtext = (EditText) findViewById(R.id.editText);
        Button sbutton = (Button) findViewById(R.id.search_button);

        int id = item.getItemId();

        if (id == R.id.nav_pref) {
            sbutton.setVisibility(View.GONE);
            map.getView().setVisibility(View.GONE);
            edtext.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.content_frame, new PreferitiFragment()).commit();

        } else if (id == R.id.nav_visit) {
            sbutton.setVisibility(View.GONE);
            map.getView().setVisibility(View.GONE);
            edtext.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.content_frame, new VisitatiFragment()).commit();

        } else if (id == R.id.nav_home) {
            map.getView().setVisibility(View.VISIBLE);
            sbutton.setVisibility(View.VISIBLE);
            edtext.setVisibility(View.VISIBLE);

                }
          else if (id == R.id.nav_help) {
            sbutton.setVisibility(View.GONE);
            map.getView().setVisibility(View.GONE);
            edtext.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.content_frame, new HelpFragment()).commit();

                }
          else if (id == R.id.footer_spacer_1) {
            sbutton.setVisibility(View.GONE);
            map.getView().setVisibility(View.GONE);
            edtext.setVisibility(View.GONE);
            fm.beginTransaction().replace(R.id.content_frame, new InfoFragment()).commit();

                }
          else if (id == R.id.nav_logout) {
            AccessToken accessToken = AccessToken.getCurrentAccessToken();
            if (accessToken != null) {
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(HomeActivity.this, HomeActivityOff.class);
                startActivity(intent);
                finish();
            }
            if(mGoogleApiClient!=null && mGoogleApiClient.isConnected()){
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Intent intent = new Intent(HomeActivity.this, HomeActivityOff.class);
                                startActivity(intent);
                                finish();
                            }
                        });
            }

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private String returnValueFromBundles(String key){
        Bundle inBundle = getIntent().getExtras();
        String returnedValue = inBundle.get(key).toString();
        return returnedValue;
    }
}