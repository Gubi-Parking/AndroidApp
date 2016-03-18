package com.mobiles.gubi;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by FernandoVezVera on 16/03/16.
 */
public class ZoneAdapter extends BaseAdapter {
    private ArrayList<Zone> zones;
    private Activity activity;

    public ZoneAdapter(Activity activity, ArrayList<Zone> zones){
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
            convertView = activity.getLayoutInflater().inflate(R.layout.row,null);
        }
        TextView zone = (TextView)convertView.findViewById(R.id.textView4);
        TextView ESpace = (TextView)convertView.findViewById(R.id.textView6);
        TextView WCspace = (TextView)convertView.findViewById(R.id.textView);
        TextView CPSpace = (TextView)convertView.findViewById(R.id.textView3);
        ImageView WCLogo = (ImageView)convertView.findViewById(R.id.imageView);
        ImageView CPLogo = (ImageView)convertView.findViewById(R.id.imageView2);

        Zone z = zones.get(position);
        zone.setText(z.getName());
        if(z.getTotal() - z.getBusy() == 0 ){
            ESpace.setText("Max capacity!");
        } else{
            ESpace.setText("" + ((z.getTotal() - z.getBusy())));
        }
        if(z.getCPool()==0){
            CPSpace.setVisibility(View.INVISIBLE);
            CPLogo.setVisibility(View.INVISIBLE);
        }else{
            CPLogo.setVisibility(View.VISIBLE);
            CPSpace.setVisibility(View.VISIBLE);
            CPSpace.setText("" +(z.getCPool()+""));
        }

        if(z.getWChair()==0){
            WCspace.setVisibility(View.GONE);
            WCLogo.setVisibility(View.INVISIBLE);
        }else{
            WCspace.setVisibility(View.VISIBLE);
            WCLogo.setVisibility(View.VISIBLE);
            WCspace.setText("" + (z.getWChair() + ""));
        }

        return convertView;
    }
}
