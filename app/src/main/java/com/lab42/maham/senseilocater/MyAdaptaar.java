package com.lab42.maham.senseilocater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import Model.weather;

/**
 * Created by Maham on 5/28/2017.
 */


public class MyAdaptaar extends ArrayAdapter {
    Context c;
    weather[] data;
    public MyAdaptaar(Context context, int resource, weather[] data) {
        super(context, R.layout.cell,data);

        this.c = context;
        this.data = data;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        placeHolder ph;
        if(convertView == null) {
            LayoutInflater i = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            view = i.inflate(R.layout.cell,null,true);
            ph = new placeHolder((TextView) view.findViewById(R.id.tv_location),(TextView)view.findViewById(R.id.tv_max),(TextView)view.findViewById(R.id.tv_min));
            view.setTag(ph);
        }
        else
        {
            view = convertView;
            ph = (placeHolder)view.getTag();
        }
        weather w = (weather)data[position];
        if(w != null)
        {
            ph.tv1.setText(w.getLocation());
            ph.tv2.setText(w.getmax());
            ph.tv3.setText(w.getmin());
        }
        return view;
    }
}

class placeHolder
{
    public TextView tv1;
    public TextView tv2;
    public TextView tv3;
    placeHolder(TextView a,TextView b,TextView c)
    {
        tv1 = a;
        tv2 = b;
        tv3 = c;
    }
}


