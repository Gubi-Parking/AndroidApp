package com.mobiles.gubi;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.*;
import com.firebase.client.core.SyncPoint;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity implements ValueEventListener,AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener {

    private Button boton1, boton2, boton3;
    public Firebase myFirebaseRef;
    private ArrayList<Zone> zonelist;
    ListView listView;
    public String zonevalue;
    private PriorityType pt=PriorityType.AVAILABLE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        this.boton1 = (Button) findViewById(R.id.button);
        this.boton2 = (Button) findViewById(R.id.button2);
        this.boton3 = (Button) findViewById(R.id.button3);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(getString(R.string.FBUrl));
        setContentView(R.layout.activity_home_screen);
        zonevalue = getIntent().getStringExtra("zonevalue");
        zonevalue=zonevalue+"/";
        myFirebaseRef.child(zonevalue).addValueEventListener(this);

        listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
    }

    public void clickOnAvailable(View v) {
        PrioritySort.setPriority(zonelist, PriorityType.AVAILABLE);
        PrioritySort.sortP(zonelist);
        ZoneAdapter newAdapter = new ZoneAdapter(this,zonelist);
        listView.setAdapter(newAdapter);
        pt=PriorityType.AVAILABLE;
    }

    public void clickOnWChair(View v) {
        PrioritySort.setPriority(zonelist, PriorityType.WHEELCHAIR);
        PrioritySort.sortP(zonelist);
        ZoneAdapter newAdapter = new ZoneAdapter(this,zonelist);
        listView.setAdapter(newAdapter);
        pt=PriorityType.WHEELCHAIR;
    }

    public void clickOnCarPool(View v) {
        PrioritySort.setPriority(zonelist, PriorityType.CARPOOL);
        PrioritySort.sortP(zonelist);
        ZoneAdapter newAdapter = new ZoneAdapter(this,zonelist);
        listView.setAdapter(newAdapter);
        pt=PriorityType.CARPOOL;
    }

    @Override
    public void onDataChange(DataSnapshot snapshot) {
        System.out.println("El valor de los lugares es: " + snapshot.child("Zone1").getValue());
        String zona = "Zone";
        zonelist = new ArrayList<Zone>();
        int busy,total,wchair,cpool;
        String localization;
        for(DataSnapshot zone:snapshot.getChildren()) {
            zona = zone.child("Name").getValue().toString();
            if (zone.child("Busy").getValue() == null) {
                busy = 0;
            } else {
                busy = ((Long) zone.child("Busy").getValue()).intValue();
            }
            if (zone.child("Total").getValue() == null) {
                total = 0;
            } else {
                total = ((Long) zone.child("Total").getValue()).intValue();
            }
            if (zone.child("WChair").getValue() == null) {
                wchair = 0;
            } else {
                wchair = ((Long) zone.child("WChair").getValue()).intValue();
            }
            if (zone.child("CPool").getValue() == null) {
                cpool = 0;
            } else {
                cpool = ((Long) zone.child("CPool").getValue()).intValue();
            }
            if (zone.child("Localization").getValue() == null) {
                localization = "";
            } else {
                localization = (zone.child("Localization").getValue()+"");
            }
            zonelist.add(new Zone(busy, total, wchair, cpool, zona,localization));

        }
        PrioritySort.setPriority(zonelist,pt);
        PrioritySort.sortP(zonelist);
        ZoneAdapter newAdapter = new ZoneAdapter(this,zonelist);
        listView.setAdapter(newAdapter);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
        System.out.println("The read failed: " + firebaseError.getMessage());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Mapa.class);
        String zone = zonelist.get(position).getLocalization();
        if(zone == ""){
            zone = null;
        }
        intent.putExtra("Localization", zone);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
