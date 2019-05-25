package com.example.tirapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager   myLocManager;
    LocationListener  myLocListener;

    Spinner   mySpinner;

     ArrayList<Abladestelle> ListOfAbladestellen= new ArrayList<Abladestelle>();

     //Funktion für Referenz auf bestimmtes Ojekt
    // IN  -> String Name
    // OUT -> Abladestelle [Objekt]
    public Abladestelle findAbladestelleByName(String name) {
        for(Abladestelle ablObj : ListOfAbladestellen) {
            if(ablObj.getName().equals(name)) {
                return ablObj;
            }
        }
        return null;
    }

    // Test....





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                myLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, myLocListener);
                mMap.setMyLocationEnabled(true);
            }
        }
    }


        @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);


            Abladestelle ablTor3 = new Abladestelle("Tor 3", new LatLng(48.648352, 12.470683));
            Abladestelle ablTor4 = new Abladestelle("Tor 4", new LatLng(48.652777, 12.469011));

            mySpinner = (Spinner) findViewById(R.id.spinner);

            List<String> list = new ArrayList<String>();
            list.add(ablTor4.Name);
            list.add(ablTor3.Name);

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(adapter);
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                    Object item = adapterView.getItemAtPosition(position);
                    Toast.makeText(MainActivity.this, " Ausgewählt: >> "+item.toString(), Toast.LENGTH_SHORT).show();

                    Abladestelle selectedAbladestelle = findAbladestelleByName(item.toString());
                    LatLng ablMarker = selectedAbladestelle.Koordinaten;
                    mMap.addMarker(new MarkerOptions().position(ablMarker).title(selectedAbladestelle.Name));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ablMarker,15));

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // TODO Auto-generated method stub
                }
            });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.i("Durchlauf Main Activity", "oncreate Mainactivity");

        myLocManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
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
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1 );
        } else {
            myLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, myLocListener);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.i("Marker", "Marker Erzeugen");
        mMap = googleMap;
        LatLng Marker1 = new LatLng(48,12);
        mMap.addMarker(new MarkerOptions().position(Marker1).title("Andi & Dani sind super"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marker1,15));

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }



    }

    public class Abladestelle {
       private Integer ID;
       public String Name;
       public LatLng Koordinaten;
       //public List ListOfAbladestellen = new ArrayList<Abladestelle>();

        public Abladestelle (String cName, LatLng cKoordinaten) {
            this.Name=cName;
            this.Koordinaten = cKoordinaten;
            this.ID = ListOfAbladestellen.size();
            ListOfAbladestellen.add(this);
        }

        //Getter
        public String getName() {
            return Name;
        }
        public LatLng getKoordinaten() {
            return Koordinaten;
        }


    }

}
