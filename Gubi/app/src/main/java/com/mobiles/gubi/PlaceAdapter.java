package com.mobiles.gubi;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FernandoVezVera on 17/03/16.
 */
public class PlaceAdapter extends BaseAdapter {
    private ArrayList<Zone> zones;
    private Activity activity;

    public PlaceAdapter(Activity activity, ArrayList<Zone> zones){
        this.zones = zones;
        this.activity = activity;

    }
    @Override
    public int getCount() {
        return zones.size();
    }

    @Override
    public Object getItem(int position) {
        return zones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = activity.getLayoutInflater().inflate(R.layout.place,null);
        }
        TextView zone = (TextView)convertView.findViewById(R.id.textView6);


        Zone z = zones.get(position);
        zone.setText(z.getName());


        return convertView;
    }
}
