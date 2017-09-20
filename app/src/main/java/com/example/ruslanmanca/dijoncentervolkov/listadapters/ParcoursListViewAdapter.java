package com.example.ruslanmanca.dijoncentervolkov.listadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruslanmanca.dijoncentervolkov.R;
import com.example.ruslanmanca.dijoncentervolkov.adapters.PoiAdapter;
import com.example.ruslanmanca.dijoncentervolkov.models.Poi;
import com.example.ruslanmanca.dijoncentervolkov.models.database.Parcours;

import java.util.ArrayList;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public class ParcoursListViewAdapter extends ArrayAdapter<Parcours> implements View.OnClickListener{
    private ArrayList<Parcours> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView cinema;
        TextView restaurant;
        TextView dateRealisation;
        TextView accompagnant;
        TextView statut;
    }

    public ParcoursListViewAdapter(ArrayList<Parcours> data, Context context) {
        super(context, R.layout.parcours_listview_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Parcours dataModel=(Parcours)object;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Parcours dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ParcoursListViewAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ParcoursListViewAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.parcours_listview_item, parent, false);
            viewHolder.cinema = (TextView) convertView.findViewById(R.id.cinema);
            viewHolder.restaurant = (TextView) convertView.findViewById(R.id.restaurant);
            viewHolder.dateRealisation = (TextView) convertView.findViewById(R.id.dateRealisation);
            viewHolder.accompagnant = (TextView) convertView.findViewById(R.id.accompagnant);
            viewHolder.statut = (TextView) convertView.findViewById(R.id.statut);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ParcoursListViewAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        PoiAdapter poiAdapter = new PoiAdapter("https://my-json-server.typicode.com/lpotherat/pois/pois/" );
        Poi cinema = poiAdapter.GetById(dataModel.getIdCinema());
        Poi restaurant = poiAdapter.GetById(dataModel.getIdRestaurant());

        viewHolder.cinema.setText(cinema.getName());
        viewHolder.restaurant.setText(restaurant.getName());
        viewHolder.dateRealisation.setText(dataModel.getDateRealisation());
        viewHolder.accompagnant.setText(dataModel.getAccompagnant());
        viewHolder.statut.setText(dataModel.getStatut().getLibelle());

        //viewHolder.info.setOnClickListener(this);
        // Return the completed view to render on screen
        return convertView;
    }
}
