package com.example.tirapp;


//Imports-START: mySpinner
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//Imports-ENDE: mySpinner

public class Archiv {


    protected void onCreate() {

    //START: MySpinner

/*    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mySpinner.setAdapter(adapter);
            mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
            Object item = adapterView.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, " Ausgewählt: >> "+item.toString(), Toast.LENGTH_SHORT).show();

            MainActivity.Abladestelle selectedAbladestelle = findAbladestelleByName(item.toString());
            LatLng ablMarker = selectedAbladestelle.Koordinaten;
            mMap.addMarker(new MarkerOptions().position(ablMarker).title(selectedAbladestelle.Name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ablMarker,15));

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
        }
    });*/

    //ENDE: MySpinner


}
}