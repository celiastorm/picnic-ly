package com.picnicly.picnic_ly;

import android.app.Fragment;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;


public class GmapFragment extends Fragment implements OnMapReadyCallback {
	GoogleMap m;
	Marker marker;
	Marker prevmark;
	private GoogleApiClient client;
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

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_gmaps, container,false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
		fragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {

		LatLng marker = new LatLng(-33.867, 151.206);

		googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 13));

		googleMap.addMarker(new MarkerOptions().title("Hello Google Maps!").position(marker));
	}


}