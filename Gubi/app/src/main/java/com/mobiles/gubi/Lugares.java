package com.mobiles.gubi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Lugares extends AppCompatActivity implements ValueEventListener,AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener, View.OnClickListener  {

    public Firebase myFirebaseRef;
    private ArrayList<Zone> zonelist;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(getString(R.string.FBUrl));
        setContentView(R.layout.activity_lugares);
        myFirebaseRef.addValueEventListener(this);

        listView=(ListView)findViewById(R.id.listView2);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, HomeScreen.class);
        intent.putExtra("zonevalue", zonelist.get(position).getName());
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDataChange(DataSnapshot snapshot) {
        String zona = "Zone1";
        zonelist = new ArrayList<Zone>();
        for(DataSnapshot zone:snapshot.getChildren()){
            zona = zone.getKey();
            zonelist.add(new Zone(0,0,0,0,zona,""));
        }
        PlaceAdapter newAdapter = new PlaceAdapter(this,zonelist);
        listView.setAdapter(newAdapter);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        System.out.println("The read failed: " + firebaseError.getMessage());
    }

    @Override
    public void onClick(View v) {

    }
}
