package com.example.tirapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, android.widget.AdapterView.OnItemSelectedListener {

    private GoogleMap                       myMap;
    private RecyclerView                    myRecyclerView;
    private ExampleAdapter                  myAdapter;
    private RecyclerView.LayoutManager      myLayoutManager;
    private LocationManager                 myLocManager;
    private LocationListener                myLocListener;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // TODO Auto-generated method stub
        Toast.makeText(this, "YOUR SELECTION IS : " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();

//      Abladestelle selectedAbladestelle = findAbladestelleByName(parent.getItemAtPosition(position).toString());

//        LatLng ablMarker = selectedAbladestelle.Koordinaten;
//        mMap.addMarker(new MarkerOptions().position(ablMarker).title(selectedAbladestelle.Name));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ablMarker,15));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                myLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocListener);
                myMap.setMyLocationEnabled(true);
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // On Create Methode
    // =============================================================================================
    // Input:       |
    // Output:      |
    // Last Change: |
    // Comment:     |
    //              |
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<tir_main_rvs_items> lc_tir_main_rvs_items = new ArrayList<>();
        lc_tir_main_rvs_items.add(new tir_main_rvs_items(R.drawable.ic_android, "Test1", "Test 1"));
        lc_tir_main_rvs_items.add(new tir_main_rvs_items(R.drawable.ic_android, "Test2", "Test 2"));
        lc_tir_main_rvs_items.add(new tir_main_rvs_items(R.drawable.ic_android, "Test3", "Test 3"));

        myLayoutManager = new LinearLayoutManager(this);
        myAdapter       = new ExampleAdapter(lc_tir_main_rvs_items);
        //------------------------------------------------------------------------------------------
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //------------------------------------------------------------------------------------------
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //------------------------------------------------------------------------------------------
        myLocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //------------------------------------------------------------------------------------------

        myLocListener = new LocationListener() {
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
                Log.i("GPS", "GPS ausgeschaltet du Honki");
            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            myLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocListener);
        }
        //------------------------------------------------------------------------------------------

        myRecyclerView = findViewById(R.id.tir_main_rvs);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myRecyclerView.setAdapter(myAdapter);
        //------------------------------------------------------------------------------------------
        // Modify intent between each cardview
        //------------------------------------------------------------------------------------------
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(2);
        myRecyclerView.addItemDecoration(itemDecoration);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tir_menu, menu);

        MenuItem searchItem  = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("Marker", "Marker Erzeugen");
        myMap = googleMap;
        LatLng Marker1 = new LatLng(48, 12);
        myMap.addMarker(new MarkerOptions().position(Marker1).title("Andi & Dani sind super"));
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marker1, 15));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            myMap.setMyLocationEnabled(true);
        }
    }
}

