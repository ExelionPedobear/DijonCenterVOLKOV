package com.example.ruslanmanca.dijoncentervolkov.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.R;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Statut;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class StatutSpinnerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Statut> data;

    LayoutInflater layoutInflater;

    ViewSpinnerStatutHolder holder;

    public StatutSpinnerAdapter(ArrayList<Statut> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount(){
        return data.size();
    }

    public Statut getItem(int position){
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
            holder = new ViewSpinnerStatutHolder((TextView) vi.findViewById(R.id.tvLibelle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerStatutHolder)vi.getTag();
        }

        Statut l = getItem(position);

        if (l != null) {
            holder.tvLibelle.setText(l.getLibelle());
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
            holder = new ViewSpinnerStatutHolder((TextView) vi.findViewById(R.id.tvLibelle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerStatutHolder)vi.getTag();
        }

        Statut l = getItem(position);

        if (l != null) {
            holder.tvLibelle.setText(l.getLibelle());
        }

        return vi;
    }
}

class ViewSpinnerStatutHolder{
    public TextView tvLibelle;

    public ViewSpinnerStatutHolder(TextView tvLibelle){
        this.tvLibelle = tvLibelle;
    }
}
