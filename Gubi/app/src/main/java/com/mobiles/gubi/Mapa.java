package com.mobiles.gubi;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class Mapa extends AppCompatActivity implements ValueEventListener {

    public String localization;
    public ImageView map;
    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_mapa);
        localization = getIntent().getStringExtra("Localization");
        map = (ImageView) findViewById(R.id.imageView3);
        if (localization==null){
            map.setImageResource(R.drawable.unavailable);
        }else{
            Context context = map.getContext();
            int id = context.getResources().getIdentifier(localization, "drawable", context.getPackageName());
            map.setImageResource(id);

        }
    }


    public void onCancelled(FirebaseError firebaseError) {
        System.out.println("The read failed: " + firebaseError.getMessage());
    }

    public void onDataChange(DataSnapshot snapshot) {
    }
}
