package com.example.ruslanmanca.dijoncentervolkov.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.R;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class PoiSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Poi> data;

    LayoutInflater layoutInflater;

    ViewSpinnerHolder holder;

    public PoiSpinnerAdapter(ArrayList<Poi> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount(){
        return data.size();
    }

    public Poi getItem(int position){
        return data.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null)
        {
            vi = layoutInflater.inflate(R.layout.spinner_row, null);
            holder = new ViewSpinnerHolder((TextView) vi.findViewById(R.id.tvLibelle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerHolder)vi.getTag();
        }

        Poi l = getItem(position);

        if (l != null) {
            holder.tvLibelle.setText(l.getName());
        }

        return vi;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        View vi = convertView;
        if(convertView == null)
        {
            vi = layoutInflater.inflate(R.layout.spinner_row, null);
            holder = new ViewSpinnerHolder((TextView) vi.findViewById(R.id.tvLibelle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerHolder)vi.getTag();
        }

        Poi l = getItem(position);

        if (l != null) {
            holder.tvLibelle.setText(l.getName());
        }

        return vi;
    }
}

class ViewSpinnerHolder{
    public TextView tvLibelle;

    public ViewSpinnerHolder(TextView tvLibelle){
        this.tvLibelle = tvLibelle;
    }
}